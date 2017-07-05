package br.com.rar.superafit.superafitbackoffice.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessagesUtil {

	private static MessagesUtil instance;
	
	private Properties prop;
	
	public static MessagesUtil getInstance() {
		if(instance == null) {
			instance = new MessagesUtil();
		}
		return instance;
	}
	
	private MessagesUtil() {
    	try {
	    	prop = new Properties();
	    	InputStream in = getClass().getResourceAsStream("/br/com/rar/superafit/superafitbackoffice/utils/messages.properties");
			prop.load(in);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public String get(String code) {
    	if(prop != null) {
    		return prop.getProperty(code);
    	}
    	return "Mensagem não encontrada";
    }
    
}
