package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;
import java.util.List;

public class TrainningResponse implements Serializable {

	private static final long serialVersionUID = -6232555509341027191L;

	private String id;
	
	private String date;
	
	private String type;
	
	private String description;
	
	private String round;
	
	private List<MovementResponse> movements;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public List<MovementResponse> getMovements() {
		return movements;
	}

	public void setMovements(List<MovementResponse> movements) {
		this.movements = movements;
	}
	
}
