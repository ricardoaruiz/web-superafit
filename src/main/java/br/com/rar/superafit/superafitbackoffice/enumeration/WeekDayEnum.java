package br.com.rar.superafit.superafitbackoffice.enumeration;

public enum WeekDayEnum {

	SEGUNDA_FEIRA(1, WeekDay.SEGUNDA_FEIRA),
	TERCA_FEIRA(2, WeekDay.TERCA_FEIRA),
	QUARTA_FEIRA(3, WeekDay.QUARTA_FEIRA),
	QUINTA_FEIRA(4, WeekDay.QUINTA_FEIRA),
	SEXTA_FEIRA(5, WeekDay.SEXTA_FEIRA),
	SABADO(6, WeekDay.SABADO);
		
	private final int index;
	
	private final String description;
	
	private WeekDayEnum(int index, String description) {
		this.index = index;
		this.description = description;
	}

	public int getIndex() {
		return index;
	}
	
	public String getDescription() {
		return description;
	}

	public interface WeekDay {		
		public static final String SEGUNDA_FEIRA = "Segunda-feira";
		public static final String TERCA_FEIRA = "Terça-feira";
		public static final String QUARTA_FEIRA = "Quarta-feira";
		public static final String QUINTA_FEIRA = "Quinta-feira";
		public static final String SEXTA_FEIRA = "Sexta-feira";
		public static final String SABADO = "Sabado";
	}
	
}
