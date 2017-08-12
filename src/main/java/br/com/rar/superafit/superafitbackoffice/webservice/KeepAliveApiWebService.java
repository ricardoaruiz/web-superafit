package br.com.rar.superafit.superafitbackoffice.webservice;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KeepAliveApiWebService {

	@GET("app/trainning-available")
	Call<Void> isOn();
	
}
