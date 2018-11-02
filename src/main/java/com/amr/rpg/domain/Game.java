package com.amr.rpg.domain;

import java.io.Serializable;

import com.amr.rpg.constants.GameStatus;

/**
 * @author aeldemerdash
 *
 */
public class Game implements Serializable{

	private static final long serialVersionUID = 1419722392547672579L;
	Player player;
	Enemy enemy;
	GameStatus status; 
	
	
	public Game() {
	}
	
	public Game(Player player, Enemy enemy, GameStatus status) {
		this.player = player;
		this.enemy = enemy;
		this.status = status;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Enemy getEnemy() {
		return enemy;
	}
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	public GameStatus getStatus() {
		return status;
	}
	public void setStatus(GameStatus status) {
		this.status = status;
	}
	
	
	
	
	
	
}
