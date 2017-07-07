package br.com.rar.superafit.superafitbackoffice.webservice;

import java.util.List;

import br.com.rar.superafit.superafitbackoffice.model.TrainningTypeResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TrainningTypeWebService {

	@GET("trainning-type")
	Call<List<TrainningTypeResponse>> findAll();
	
}
