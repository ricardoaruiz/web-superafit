package br.com.rar.superafit.superafitbackoffice.webservice;

import br.com.rar.superafit.superafitbackoffice.model.ListScheduleResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ScheduleWebService {

	@GET("schedule")
	Call<ListScheduleResponse> list();
	
}
