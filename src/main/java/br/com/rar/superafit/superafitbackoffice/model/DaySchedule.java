package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DaySchedule implements Serializable {

	private static final long serialVersionUID = 6491878828084228533L;

	@JsonProperty("schedule_start")
	private String scheduleStart;
	
	@JsonProperty("schedule_end")
	private String scheduleEnd;

	public String getScheduleStart() {
		return scheduleStart;
	}

	public void setScheduleStart(String scheduleStart) {
		this.scheduleStart = scheduleStart;
	}

	public String getScheduleEnd() {
		return scheduleEnd;
	}

	public void setScheduleEnd(String scheduleEnd) {
		this.scheduleEnd = scheduleEnd;
	}
	
}
