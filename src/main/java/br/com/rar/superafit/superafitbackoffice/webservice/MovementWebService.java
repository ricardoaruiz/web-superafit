package br.com.rar.superafit.superafitbackoffice.webservice;

import br.com.rar.superafit.superafitbackoffice.model.ExerciceRequest;
import br.com.rar.superafit.superafitbackoffice.model.ListMovementResponse;
import br.com.rar.superafit.superafitbackoffice.model.MovementResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MovementWebService {

	@POST("movement")
	Call<Void> save(@Body ExerciceRequest request, @Header("Authorization") String jwtToken);
	
	@GET("movement/{id}")
	Call<MovementResponse> findOne(@Path("id") Long id, @Header("Authorization") String jwtToken);
	
	@GET("movement")
	Call<ListMovementResponse> findAllActived(@Header("Authorization") String jwtToken);
	
	@GET("movement/all")
	Call<ListMovementResponse> findAll(@Header("Authorization") String jwtToken);
	
}
