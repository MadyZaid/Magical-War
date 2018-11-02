package com.amr.rpg.service;

import com.amr.rpg.domain.Game;
import com.amr.rpg.repository.GameRepository;
import com.amr.rpg.repository.GameRepositoryImpl;

/**
 * @author aeldemerdash
 *
 */
public class DefaultGamerService implements GameService {
	
	public Game resumeGame() {
		GameRepository repository = new GameRepositoryImpl();
		Game savedGame = null;
		try {
			savedGame = repository.readSavedObject();
		} catch (Exception e) {
			System.out.println("Error while Resuming the Game");
		} 
		return savedGame;
	}
	public void  saveGame(Game game) {
		GameRepository gamerService = new GameRepositoryImpl();
		gamerService.save(game);
		System.out.println("your Game has been saved successfully !!");
	}

	

}
