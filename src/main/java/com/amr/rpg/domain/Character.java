package com.amr.rpg.domain;

import java.io.Serializable;

/**
 * @author aeldemerdash
 *
 */
public class Character implements Serializable {

	private static final long serialVersionUID = 6341558765593617720L;
	private String name;
	private float health;
	private float strength;
	private float defense;

	
	public Character() {
	}

	private Character(String name, float health, float strength, float defense) {
		this.name = name;
		this.health = health;
		this.strength = strength;
		this.defense = defense;
	}

	public String getName() {
		return this.name;
	}

	public float getHealth() {
		return this.health;
	}

	public float getStrength() {
		return this.strength;
	}

	public float getDefense() {
		return this.defense;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public void setStrength(float strength) {
		this.strength = strength;
	}

	public void setDefense(float defense) {
		this.defense = defense;
	}

	public static CharacterBuilder newBuilder() {
		return new CharacterBuilder();
	}

	public static class CharacterBuilder {
		private String name;
		private float health;
		private float strength;
		private float defense;

		public CharacterBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public CharacterBuilder setHealth(float health) {
			this.health = health;
			return this;
		}

		public CharacterBuilder setStrength(float strength) {
			this.strength = strength;
			return this;
		}

		public CharacterBuilder setDefense(float defense) {
			this.defense = defense;
			return this;
		}

		public Character createCharacter() {
			return new Character(name, health, strength, defense); 
		}

	}

	@Override
	public String toString() {
		return "Character [name=" + name + ", health=" + health + ", strength=" + strength + ", defense=" + defense
				+ "]";
	}
	
	

	

}
