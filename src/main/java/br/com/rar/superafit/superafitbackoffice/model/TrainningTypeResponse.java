package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;

public class TrainningTypeResponse implements Serializable {

	private static final long serialVersionUID = -2156003188507017935L;

	private int id;
	
	private String name;
	
	private int sequence;
	
	private String idSequence;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getIdSequence() {
		return id + "-" + sequence;
	}

	public void setIdSequence(String idSequence) {
		this.idSequence = idSequence;
	}

}
