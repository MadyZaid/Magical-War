package com.amr.rpg.domain;

import java.io.Serializable;

/**
 * @author aeldemerdash
 *
 */
public class Experience implements Serializable {
	

	private static final long serialVersionUID = -7527046304420603413L;
	private int value;
	
	
	public Experience() {
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public Experience(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Experience [value=" + value + "]";
	}


}
