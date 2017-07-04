package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;
import java.util.List;

public class ListScheduleResponse implements Serializable {

	private static final long serialVersionUID = 137473413523502760L;

	private List<Schedule> schedules;

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	
}
