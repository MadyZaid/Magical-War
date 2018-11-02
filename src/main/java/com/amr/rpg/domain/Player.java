package com.amr.rpg.domain;

import java.io.Serializable;

/**
 * @author aeldemerdash
 *
 */
public class Player implements Serializable {

	private static final long serialVersionUID = 7482249314893895522L;
	private Experience experience;
	private Character character;
	private String name;

	public Player() {
	}

	public Player(Experience experience, Character character, String name) {
		this.experience = experience;
		this.character = character;
		this.name = name;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Player [name =" + name + ", experience=" + experience + ", character=" + character + "]";
	}

}
