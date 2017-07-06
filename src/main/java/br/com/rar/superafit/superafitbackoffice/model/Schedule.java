package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.rar.superafit.superafitbackoffice.enumeration.WeekDayEnum;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Schedule implements Serializable {

	private static final long serialVersionUID = 8203892470770015973L;

	@JsonProperty("week_day")
	private String weekDay;
	
	@JsonProperty("day_schedules")
	private List<DaySchedule> daySchedules;
	
	private int indexWeekDay;
	
	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
		switch (weekDay) {
		case WeekDayEnum.WeekDay.SEGUNDA_FEIRA:
			this.indexWeekDay = 1;
			break;
		case WeekDayEnum.WeekDay.TERCA_FEIRA:
			this.indexWeekDay = 2;
			break;
		case WeekDayEnum.WeekDay.QUARTA_FEIRA:
			this.indexWeekDay = 3;
			break;
		case WeekDayEnum.WeekDay.QUINTA_FEIRA:
			this.indexWeekDay = 4;
			break;
		case WeekDayEnum.WeekDay.SEXTA_FEIRA:
			this.indexWeekDay = 5;
			break;
		default:
			this.indexWeekDay = 6;
			break;
		}
	}

	public List<DaySchedule> getDaySchedules() {
		return daySchedules;
	}

	public void setDaySchedules(List<DaySchedule> daySchedules) {
		this.daySchedules = daySchedules;
	}

	public int getIndexWeekDay() {
		return indexWeekDay;
	}	
	
}
