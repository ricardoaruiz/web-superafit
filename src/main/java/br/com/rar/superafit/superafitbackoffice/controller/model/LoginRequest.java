package br.com.rar.superafit.superafitbackoffice.controller.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class LoginRequest implements Serializable {

	private static final long serialVersionUID = 9071023882528175454L;

	@NotBlank(message = "O login é obrigatório.")
	private String login;
	
	@NotBlank(message = "A senha é obrigatória.")
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
