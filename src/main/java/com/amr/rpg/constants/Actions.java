package com.amr.rpg.constants;

/**
 * @author aeldemerdash
 *
 */
public enum Actions {
	Attack(1), defend(2);

	int value;

	Actions(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	

}