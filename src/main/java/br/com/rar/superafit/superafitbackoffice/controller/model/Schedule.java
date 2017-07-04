package br.com.rar.superafit.superafitbackoffice.controller.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class Schedule implements Serializable {

	private static final long serialVersionUID = -5724512993614729322L;

	@NotBlank(message = "O dia da semana é obrigatório")
	private String weekDay;
	
	@NotBlank(message = "A hora início é obrigatória")
	private String scheduleStart;
	
	@NotBlank(message = "A hora fim é obrigatória")
	private String scheduleEnd;

	public String getWeekDay() {
		return weekDay == null ? "" : weekDay;
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
