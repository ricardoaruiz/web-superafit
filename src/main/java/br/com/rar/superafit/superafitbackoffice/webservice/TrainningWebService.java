package br.com.rar.superafit.superafitbackoffice.webservice;

import br.com.rar.superafit.superafitbackoffice.model.CreateTrainningRequest;
import br.com.rar.superafit.superafitbackoffice.model.ListTrainningResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TrainningWebService {

	@POST("day-training")
	Call<Void> create(@Body CreateTrainningRequest request);

	@GET("day-training")
	Call<ListTrainningResponse> getTrainning(@Query("date") String string);
	
}
