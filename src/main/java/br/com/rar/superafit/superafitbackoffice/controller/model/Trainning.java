package br.com.rar.superafit.superafitbackoffice.controller.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class Trainning implements Serializable {

	private static final long serialVersionUID = -5724512993614729322L;

	@NotBlank(message = "O dia do treino é obrigatório")
	private String date;
	
	@NotBlank(message = "O tipo de treino é obrigatório")
	private String type;
	
	@NotBlank(message = "O número de rounds obrigatório")
	private String round;
	
	@NotBlank(message = "É preciso informar pelo menos um exercício")
	private String movementId;
	
	private String movementRepetition;
	
	private String description;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMovementId() {
		return movementId;
	}

	public void setMovementId(String movementId) {
		this.movementId = movementId;
	}

	public String getMovementRepetition() {
		return movementRepetition;
	}

	public void setMovementRepetition(String movementRepetition) {
		this.movementRepetition = movementRepetition;
	}
		
}
