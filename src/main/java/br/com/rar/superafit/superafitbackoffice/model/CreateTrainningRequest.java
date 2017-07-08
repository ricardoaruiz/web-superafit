package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTrainningRequest implements Serializable {

	private static final long serialVersionUID = 2180975270694788636L;

	@JsonProperty("training_date")
	private String date;
	
	@JsonProperty("training_type")
	private String type;
	
	@JsonProperty("training_round")
	private String round;
	
	private String description;
	
	private List<MovementRequest> movements;

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

	public List<MovementRequest> getMovements() {
		return movements;
	}

	public void setMovements(List<MovementRequest> movements) {
		this.movements = movements;
	}
	
}
