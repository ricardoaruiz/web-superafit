package br.com.rar.superafit.superafitbackoffice.controller.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class ExerciceRequest implements Serializable {

	private static final long serialVersionUID = 3576841608941749128L;

	private String id;
	
	@NotEmpty(message="O nome do exercício deve ser informado.")
	private String name;
	
	private String translate;
	
	private String description;
	
	private boolean active;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTranslate() {
		return translate;
	}

	public void setTranslate(String translate) {
		this.translate = translate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
