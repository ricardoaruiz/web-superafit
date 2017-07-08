package br.com.rar.superafit.superafitbackoffice.webservice;

import br.com.rar.superafit.superafitbackoffice.model.CreateTrainningRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TrainningWebService {

	@POST("day-training")
	Call<Void> create(@Body CreateTrainningRequest request);
	
}
