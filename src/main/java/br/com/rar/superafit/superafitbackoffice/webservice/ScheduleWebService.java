package br.com.rar.superafit.superafitbackoffice.webservice;

import br.com.rar.superafit.superafitbackoffice.model.CreateScheduleRequest;
import br.com.rar.superafit.superafitbackoffice.model.ListScheduleResponse;
import br.com.rar.superafit.superafitbackoffice.model.RemoveScheduleRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface ScheduleWebService {

	@GET("schedule")
	Call<ListScheduleResponse> list();
	
	@POST("schedule")
	Call<Void> create(@Body CreateScheduleRequest request);
	
	@HTTP(method = "DELETE", path = "schedule", hasBody = true)
	Call<Void> remove(@Body RemoveScheduleRequest request);
	
}
