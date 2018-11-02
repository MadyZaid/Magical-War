package com.amr.rpg.service;

import com.amr.rpg.domain.Game;

/**
 * @author aeldemerdash
 *
 */
public interface GameService {
	public void  saveGame(Game game);
	public  Game resumeGame();
}
