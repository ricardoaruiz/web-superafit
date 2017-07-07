package br.com.rar.superafit.superafitbackoffice.webservice;

import br.com.rar.superafit.superafitbackoffice.model.ListMovementResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovementWebService {

	@GET("movement")
	Call<ListMovementResponse> findAll();
	
}
