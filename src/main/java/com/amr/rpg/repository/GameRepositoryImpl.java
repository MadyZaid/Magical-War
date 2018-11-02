package com.amr.rpg.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.amr.rpg.domain.Game;

/**
 * @author aeldemerdash
 *
 */
public class GameRepositoryImpl implements GameRepository {

	@Override
	public Game readSavedObject() throws FileNotFoundException, IOException, ClassNotFoundException {
		Game game = null;
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("game_save_file")))) {
			Object readObject = objectInputStream.readObject();
			if (readObject instanceof Game){
				return (Game) readObject;
			}
		}	
		
		return game; 
	
	}


	@Override
	public void save(Game game) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("game_save_file")))) {
			 objectOutputStream.writeObject(game);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
