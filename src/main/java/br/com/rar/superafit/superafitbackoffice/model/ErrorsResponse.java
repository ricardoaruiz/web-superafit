package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorsResponse implements Serializable {
	
	private static final long serialVersionUID = -6081784900354841406L;
	
	private List<ErrorResponse> errors;

	public List<ErrorResponse> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorResponse> errors) {
		this.errors = errors;
	}
	
	public void addError(String error) {
		if(errors == null) {
			errors = new ArrayList<ErrorResponse>();
		}
		errors.add(new ErrorResponse(error));
	}
	
	
}
