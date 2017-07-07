package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;
import java.util.List;

public class ListMovementResponse implements Serializable {

	private static final long serialVersionUID = 7276441770935016973L;
	
	private List<MovementResponse> movements;

	public List<MovementResponse> getMovements() {
		return movements;
	}

	public void setMovements(List<MovementResponse> movements) {
		this.movements = movements;
	}
	
}
