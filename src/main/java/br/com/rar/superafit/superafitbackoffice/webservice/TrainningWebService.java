package br.com.rar.superafit.superafitbackoffice.webservice;

import br.com.rar.superafit.superafitbackoffice.model.CreateTrainningRequest;
import br.com.rar.superafit.superafitbackoffice.model.ListTrainningResponse;
import br.com.rar.superafit.superafitbackoffice.model.TrainningResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TrainningWebService {

	@POST("day-training")
	Call<Void> create(@Body CreateTrainningRequest request, @Header("Authorization") String jwtToken);

	@GET("day-training")
	Call<ListTrainningResponse> getTrainning(@Query("date") String string, @Header("Authorization") String jwtToken);
	
	@GET("day-training/{id}")
	Call<TrainningResponse> getSpecificTrainning(@Path("id") String id, @Header("Authorization") String jwtToken);
	
	@DELETE("day-training/{id}")
	Call<Void> delete(@Path("id") String id, @Header("Authorization") String jwtToken);

	@POST("day-training/notification")
	Call<Void> notification(@Header("Authorization") String jwtToken);
	
}
