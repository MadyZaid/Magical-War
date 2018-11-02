package com.amr.rpg.controller;

import static com.amr.rpg.constants.GameStatus.FINISHED;

import java.util.Scanner;

import com.amr.rpg.config.ConfigurationManager;
import com.amr.rpg.constants.Actions;
import com.amr.rpg.constants.GameStatus;
import com.amr.rpg.domain.Character;
import com.amr.rpg.domain.Enemy;
import com.amr.rpg.domain.Experience;
import com.amr.rpg.domain.Game;
import com.amr.rpg.domain.Player;
import com.amr.rpg.service.DefaultGamerService;
import com.amr.rpg.service.DefaultPlayerService;
import com.amr.rpg.service.GameService;
import com.amr.rpg.service.PlayerService;
import com.amr.rpg.util.NumbersUtil;
import com.amr.rpg.view.Menu;

/**
 * @author aeldemerdash
 *
 */
public class GameController {

	Menu mainMenu;
	Menu playerMenu;
	Menu characterMenu;
	Menu fightingMenu;
	
	Game game;
	
	private int winExpScore = Integer.parseInt(ConfigurationManager.getProperty("player.experience.win.points").get());
	private int loseExpScore = Integer.parseInt(ConfigurationManager.getProperty("player.experience.win.points").get());
	private int drawExpScore = Integer.parseInt(ConfigurationManager.getProperty("player.experience.win.points").get());

	
	GameService gamerService = new DefaultGamerService();
	PlayerService playerService = new DefaultPlayerService();  

	Scanner in = new Scanner(System.in);

	public GameController() {
		setupMenu();
		game = new Game();
	}

	private void setupMenu() {
		setupMainMenu();
		setupPlayerMenu();
		setupCharacterMenu();
		setupFightingMenu();
	}

	private void setupCharacterMenu() {
		// Character Menu
		characterMenu = new Menu("Character Menu");
		characterMenu.registerAction("Monk", () -> {
			setupCharacter("Monk");
			startBattle(setupGame());
		});

		characterMenu.registerAction("Warrior", () -> {
			setupCharacter("Warrior");
			startBattle(setupGame());
		});
		characterMenu.registerAction("Mage", () -> {
			setupCharacter("Mage");
			startBattle(setupGame());
		});
		characterMenu.registerAction("main menu", this::activateMainMenu);
		characterMenu.registerAction("quit", () -> System.exit(0));
	}

	private void activateMainMenu() {
		Menu.activate(mainMenu);
	}

	private void setupFightingMenu() {
		// Set up fighting menu
		fightingMenu = new Menu("Fighting Menu");
		fightingMenu.registerAction("Attack", this::runAttackActions);
		fightingMenu.registerAction("defend", this::runDefendActions);
		fightingMenu.registerAction("main menu",this::activateMainMenu);
		fightingMenu.registerAction("Save", this::runSaveActions);
	}
	
	private void setupPlayerMenu() {
		// Set up menu
		playerMenu = new Menu("Player Setup Menu");
		
		playerMenu.registerAction("Create a new Player", () -> {
			setupPlayer();
			Menu.activate(characterMenu);
		});
		
		playerMenu.registerAction("Play with the current Player", () -> {
			loadCurrentPlayer();
			Menu.activate(characterMenu);
		});
		
		
		
		playerMenu.registerAction("main menu", this::activateMainMenu);
		playerMenu.registerAction("quit", () -> System.exit(0));
	}
	
	
	private void runSaveActions() {
		gamerService.saveGame(game);
		activateMainMenu();
	}

	private void runAttackActions() {
		displayBattleInfo(game);
		attack(game);
		checkForWinners(game);
		gainExperince();
	}

	private void runDefendActions() {
		displayBattleInfo(game);
		defened(game);
		checkForWinners(game);
	}

	private void gainExperince() {
		Experience exp = new Experience();
		if (isThePlayerWon(game)) {
			exp.setValue(game.getPlayer().getExperience().getValue() + winExpScore);
		} else if (isThePlayerLose(game)) {
			exp.setValue(game.getPlayer().getExperience().getValue() + loseExpScore);
			System.out.println("Oppppps, You lose the game  !!!!");
		} else {
			exp.setValue(game.getPlayer().getExperience().getValue() + drawExpScore);
			System.out.println("Sorry there is no winner !!!!");
		}

		game.getPlayer().setExperience(exp);
		playerService.savePlayer(game.getPlayer());
	}

	

	private void loadCurrentPlayer() {
		Player loadPlayer = playerService.loadPlayer();
		if(loadPlayer == null ) {
			System.out.println("Oppps ther is no Players Save Please Create a new One");
			Menu.activate(playerMenu);
		}
		game.setPlayer(loadPlayer);
	}

	private void setupMainMenu() {
		// Main Menu
		mainMenu = new Menu("Main Menu");
		mainMenu.registerAction("Start a new Game", () -> Menu.activate(playerMenu));
		mainMenu.registerAction("Resume Last Saved Game", () -> resumeGame());
		mainMenu.registerAction("quit", () -> System.exit(0));
	}

	private void resumeGame() {
		Game resumeGame = gamerService.resumeGame();
		if (resumeGame != null)
			startBattle(resumeGame);
		else {
			System.out.println("there is no game saved");
			activateMainMenu();
		}
	}

	private void setupPlayer() {
		System.out.print("please enter your Name: ");
		String name = in.next();
		Player tempPlayer =new Player();
		tempPlayer.setName(name);
		tempPlayer.setExperience(new Experience());
		this.game.setPlayer(tempPlayer);
	}

	private void setupCharacter(String character) {
		this.game.getPlayer().setCharacter(Character.newBuilder()
								  .setName(character)
								  .setDefense(15)
								  .setHealth(100)
								  .setStrength(15)
								  .createCharacter());
		
		playerService.savePlayer(this.game.getPlayer());
		
	}

	private Enemy setupEnemy() {
		String[] enemyList = new String[5];
		enemyList[0] = "Goblin";
		enemyList[1] = "Wolf";
		enemyList[2] = "Orc";
		enemyList[3] = "Slime";
		enemyList[4] = "FallenKnight";

		String enemyName = enemyList[NumbersUtil.getRandomIntegerBetweenRange(0, enemyList.length-1)];

		Character enemeyCharacter = Character.newBuilder()
											.setName(enemyName)
											.setDefense(10)
											.setHealth(50)
											.setStrength(10)
											.createCharacter();
		
		return Enemy.newBuilder().setCharacter(enemeyCharacter).createEnemy();
	}

	private Game setupGame() {
		 this.game.setEnemy(setupEnemy());
		 return this.game;
	}

	public void startGame() {
		activateMainMenu();
	}

	private void displayBattleInfo(Game game) {
		System.out.println("----------------------------------------------------------");
		System.out.println("--------------------------Battle Details------------------");
		System.out.println(game.getPlayer());
		System.out.println(game.getEnemy());
		System.out.println("----------------------------------------------------------");
		System.out.println("----------------------------------------------------------");
	}

	private void attack(Game game) {
		float damage;
		Actions.Attack.name();
		switch (game.getEnemy().getDecision()) {
		case 1:
			damage = game.getPlayer().getCharacter().getStrength();
			System.out.println("You Attacked dealing " + damage + " Damage!");
			game.getEnemy().getCharacter().setHealth(game.getEnemy().getCharacter().getHealth() - damage);

			damage = game.getEnemy().getCharacter().getStrength();
			System.out.println(game.getEnemy().getCharacter().getName() + " attacked you with " + damage + " Damage! ");
			game.getPlayer().getCharacter().setHealth(game.getPlayer().getCharacter().getHealth() - damage);

			break;
		case 2:
			System.out.println("Enemy Braced for an attack");
			damage = game.getPlayer().getCharacter().getStrength() - game.getEnemy().getCharacter().getDefense();
			System.out.println("You Attacked dealing" + damage + " Damage!");
			game.getEnemy().getCharacter().setHealth(game.getEnemy().getCharacter().getHealth() - damage);
			break;

		default:
			break;
		}

	}

	private void checkForWinners(Game game) {
		if (game.getPlayer().getCharacter().getHealth() <= 0 || game.getEnemy().getCharacter().getHealth() <= 0) {
			game.setStatus(FINISHED);
			gainExperince();
			announceWinner(game);
		} else {
			Menu.activate(fightingMenu);
		}
	}

	private void defened(Game game) {
		float damage;
		switch (game.getEnemy().getDecision()) {
		case 1:
			System.out.printf("You Braced for an Attack! /n/n");
			damage = game.getEnemy().getCharacter().getStrength() - game.getPlayer().getCharacter().getDefense();
			System.out.println(game.getEnemy().getCharacter().getName() + " attacked you with " + damage + " Damage!");
			game.getPlayer().getCharacter().setHealth(game.getPlayer().getCharacter().getHealth() - damage);
			break;
		case 2:
			System.out.printf("Both of you Braced for an attack !! shame on both of you !!!! /n /n");
			break;

		default:
			break;
		}

	}

	private void announceWinner(Game game) {
		System.out.println("----------------------------------------------------------");
		System.out.println("----------------------------------------------------------");
		System.out.println("----------------------------------------------------------");

		if (isThePlayerWon(game))
			System.out.println("Your are The winner, congratulations my dude ");
		else if (isThePlayerLose(game))
			System.out.println("Oppppps, You lose the game  !!!!");
		else
			System.out.println("Sorry there is no winner !!!!");

		System.out.println("----------------------------------------------------------");
		System.out.println("----------------------------------------------------------");
		System.out.println("----------------------------------------------------------");

		activateMainMenu();

	}

	private boolean isThePlayerLose(Game game) {
		return game.getPlayer().getCharacter().getHealth() < game.getEnemy().getCharacter().getHealth();
	}

	private boolean isThePlayerWon(Game game) {
		return game.getPlayer().getCharacter().getHealth() > game.getEnemy().getCharacter().getHealth();
	}

	private void startBattle(Game game) {
		this.game = game;
		game.setStatus(GameStatus.IS_ON);
		Menu.activate(fightingMenu);
	}

}
