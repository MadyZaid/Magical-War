package com.amr.rpg.domain;

import java.io.Serializable;
import java.util.Random;

/**
 * @author aeldemerdash
 *
 */
public class Enemy implements Serializable{
	private static final long serialVersionUID = -4831022685399446020L;
	private Character character;
	int decision;


	public Enemy() {
	}

	private Enemy(Character character) {
		this.character = character;
	}

	public static EnemyBuilder newBuilder() {
		return new EnemyBuilder();
	}

	public Character getCharacter() {
		return character;
	}

	public static class EnemyBuilder {
		private Character character;

		public EnemyBuilder setCharacter(Character character) {
			this.character = character;
			return this;
		}

		public Enemy createEnemy() {
			return new Enemy(character);
		}

	}

	public int getDecision() {
		return new Random().nextInt(2)+1;
	}


	@Override
	public String toString() {
		return "Enemy ["+ character + "]";
	}

}
