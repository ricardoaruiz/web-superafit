package br.com.rar.superafit.superafitbackoffice.controller.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class Pessoa implements Serializable {

	private static final long serialVersionUID = 986387460091076699L;

	@NotBlank(message = "Nome é obrigatório")
	private String firstName;
	
	@NotBlank(message = "Sobrenome é obrigatório")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
