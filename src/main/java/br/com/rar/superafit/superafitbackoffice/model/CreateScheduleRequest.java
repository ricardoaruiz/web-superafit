package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateScheduleRequest implements Serializable {

	private static final long serialVersionUID = 7938453481502176108L;

	@JsonProperty("week_day")
	private String weekDay;
	
	@JsonProperty("schedule_start")
	private String scheduleStart;
	
	@JsonProperty("schedule_end")
	private String scheduleEnd;

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

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
