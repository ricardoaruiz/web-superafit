package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MovementResponse implements Serializable{

	private static final long serialVersionUID = 8820488516302539321L;

	private String id;
	
	private String name;
	
	private String translate;
	
	private String description;
	
	private String qtRep;

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

	public String getQtRep() {
		return qtRep;
	}

	public void setQtRep(String qtRep) {
		this.qtRep = qtRep;
	}
}
