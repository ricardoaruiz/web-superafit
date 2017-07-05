package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = -4573435567293189550L;
	
	private String error;

	public ErrorResponse() {
		
	}
	
	public ErrorResponse(String error) {
		this.error = error;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String erro) {
		this.error = erro;
	}
	
}
