package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveScheduleRequest implements Serializable {

	private static final long serialVersionUID = 8610937902125243335L;

	@JsonProperty("week_day")
	private final String weekDay;
	
	@JsonProperty("schedule_start")
	private final String scheduleStart;

	public RemoveScheduleRequest(String weekDay, String scheduleStart) {
		this.weekDay = weekDay;
		this.scheduleStart = scheduleStart;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public String getScheduleStart() {
		return scheduleStart;
	}

	
}
