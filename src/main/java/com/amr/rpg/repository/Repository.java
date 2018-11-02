package com.amr.rpg.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author aeldemerdash
 *
 * @param <T>
 */
public interface Repository <T>{
	public T readSavedObject() throws FileNotFoundException, IOException, ClassNotFoundException ;
	public void save(T object) ;


}
