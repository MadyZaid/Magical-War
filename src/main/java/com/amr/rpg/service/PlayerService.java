package com.amr.rpg.service;

import com.amr.rpg.domain.Player;

/**
 * @author aeldemerdash
 *
 */
public interface PlayerService {
	public Player loadPlayer();
	public void savePlayer(Player player);

}
