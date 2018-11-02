package com.amr.rpg.constants;

import java.util.stream.Stream;

/**
 * @author aeldemerdash
 *
 */
public enum CharacterType {
    WARRIOR("[1] Warrior"),
    MONK("[2] Monk"),
    MAGE("[3] Mage");
	private String characterTypeItem;
	
	CharacterType(String characterTypeItem) {
		this.characterTypeItem = characterTypeItem;
	}

	public static Stream<CharacterType> stream() {
		return Stream.of(CharacterType.values());
	}

	@Override
	public String toString() {
		return characterTypeItem;
	}

}
