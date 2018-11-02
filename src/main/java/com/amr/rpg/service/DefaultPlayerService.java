package com.amr.rpg.service;

import com.amr.rpg.domain.Player;
import com.amr.rpg.repository.PlayerRepository;
import com.amr.rpg.repository.PlayerRepositoryImpl;

/**
 * @author aeldemerdash
 *
 */
public class DefaultPlayerService implements PlayerService{

	@Override
	public Player loadPlayer() {
		PlayerRepository repository = new PlayerRepositoryImpl();
		Player savedPlayer = null;
		try {
			savedPlayer = repository.readSavedObject();
		} catch (Exception e) {
			System.out.println("Error while loading the player");
		}
		return savedPlayer;
	}

	@Override
	public void savePlayer(Player player) {
		PlayerRepository playerRepo = new PlayerRepositoryImpl();
		playerRepo.save(player);

	}

}
