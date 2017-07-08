package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;

public class MovementRequest implements Serializable {

	private static final long serialVersionUID = -7525290662583028409L;

	private final String movement;
	
	private final String repetition;
	
	public MovementRequest(String movement, String repetition) {
		super();
		this.movement = movement;
		this.repetition = repetition;
	}

	public String getMovement() {
		return movement;
	}

	public String getRepetition() {
		return repetition;
	}
	
}
