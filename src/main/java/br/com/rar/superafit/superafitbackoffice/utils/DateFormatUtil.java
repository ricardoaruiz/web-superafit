package br.com.rar.superafit.superafitbackoffice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	
	public static String toString(Date date, Format format) {
		return format.getFormatter().format(date);
	}
	
	public enum Format {
		
		DIA_MES_ANO(new SimpleDateFormat("dd/MM/yyyy")),
		
		HORA_MINUTO(new SimpleDateFormat("HH:mm"));
		
		private SimpleDateFormat formatter;
		
		Format(SimpleDateFormat formatter) {
			this.formatter = formatter;
		}

		public SimpleDateFormat getFormatter() {
			return formatter;
		}
		
	}
	
}
