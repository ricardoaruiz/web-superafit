package br.com.rar.superafit.superafitbackoffice.controller.model;

import java.io.Serializable;

import br.com.rar.superafit.superafitbackoffice.model.MovementResponse;

public class TrainningResponse implements Serializable {

	private static final long serialVersionUID = -1694144447701919303L;
	
	private br.com.rar.superafit.superafitbackoffice.model.TrainningResponse trainningResponse;
	
	public TrainningResponse(br.com.rar.superafit.superafitbackoffice.model.TrainningResponse trainningResponse) {
		this.trainningResponse = trainningResponse;
	}
	
	public String getId() {
		return trainningResponse.getId();
	}

	public String getDate() {
		return trainningResponse.getDate();
	}

	public String getType() {
		return trainningResponse.getType();
	}

	public String getRound() {
		return trainningResponse.getRound();
	}

	public String getDescription() {
		return trainningResponse.getDescription();
	}

	public String getMovementId() {
		StringBuilder movementIds = new StringBuilder();
		for (MovementResponse movementResponse : trainningResponse.getMovements()) {
			movementIds.append(movementResponse.getId()).append(",");
		}
		String toReturn = movementIds != null ? movementIds.substring(0, movementIds.length()-1) : null; 
		return toReturn;
	}

	public String getMovementRepetition() {
		StringBuilder movementRepetitions = new StringBuilder();
		for (MovementResponse movementResponse : trainningResponse.getMovements()) {
			movementRepetitions.append(movementResponse.getQtRep()).append(",");
		}
		
		String toReturn = movementRepetitions != null ? movementRepetitions.toString().substring(0,movementRepetitions.length()-1) : null;		
		return toReturn;
	}
		
}
