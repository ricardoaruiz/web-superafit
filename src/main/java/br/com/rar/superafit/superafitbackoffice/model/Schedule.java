package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Schedule implements Serializable {

	private static final long serialVersionUID = 8203892470770015973L;

	@JsonProperty("week_day")
	private String weekDay;
	
	@JsonProperty("day_schedules")
	private List<DaySchedule> daySchedules;

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public List<DaySchedule> getDaySchedules() {
		return daySchedules;
	}

	public void setDaySchedules(List<DaySchedule> daySchedules) {
		this.daySchedules = daySchedules;
	}
	
}
