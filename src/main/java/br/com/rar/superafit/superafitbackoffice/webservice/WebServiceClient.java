package br.com.rar.superafit.superafitbackoffice.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class WebServiceClient {

	private static WebServiceClient instance;
	
	private Retrofit retrofit;
		
	private WebServiceClient() {
		retrofit = new Retrofit.Builder()
				.baseUrl("https://superafit-api.herokuapp.com/")
				.addConverterFactory(JacksonConverterFactory.create())
				.build();
	}
	
	public static WebServiceClient getInstance() {
		if(instance == null) {
			instance = new WebServiceClient();
		}
		return instance;
	}
	
	public ScheduleWebService getScheduleWebService() {
		return retrofit.create(ScheduleWebService.class);
	}
	
}
