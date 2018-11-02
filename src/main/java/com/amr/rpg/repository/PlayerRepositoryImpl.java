package com.amr.rpg.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.amr.rpg.domain.Player;

/**
 * @author aeldemerdash
 *
 */
public class PlayerRepositoryImpl implements PlayerRepository{

	@Override
	public Player readSavedObject() throws FileNotFoundException, IOException, ClassNotFoundException {
		Player player = null;
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("player_save_file")))) {
			Object readObject = objectInputStream.readObject();
			if (readObject instanceof Player){
				return (Player) readObject;
			}
			
		} 
		return player; 
	}

	@Override
	public void save(Player player) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("player_save_file")))) {
			 objectOutputStream.writeObject(player);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
}
