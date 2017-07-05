package br.com.rar.superafit.superafitbackoffice.webservice;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Service
public class WebServiceClient {

	@Value("${api.base.url}")
	private String apiBaseUrl;
	
	private Retrofit retrofit;
		
	@PostConstruct
	private void config() {
		
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		
		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(interceptor)
				.build();		
		
		retrofit = new Retrofit.Builder()
				.baseUrl(apiBaseUrl)
				.client(client)
				.addConverterFactory(JacksonConverterFactory.create())
				.build();		
	}
		
	public ScheduleWebService getScheduleWebService() {
		return retrofit.create(ScheduleWebService.class);
	}
	
}
