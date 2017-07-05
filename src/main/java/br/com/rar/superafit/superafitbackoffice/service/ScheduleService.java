package br.com.rar.superafit.superafitbackoffice.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import br.com.rar.superafit.superafitbackoffice.controller.model.Schedule;
import br.com.rar.superafit.superafitbackoffice.model.CreateScheduleRequest;
import br.com.rar.superafit.superafitbackoffice.model.ListScheduleResponse;
import br.com.rar.superafit.superafitbackoffice.service.exception.ApiServiceException;
import br.com.rar.superafit.superafitbackoffice.utils.MessagesUtil;
import br.com.rar.superafit.superafitbackoffice.webservice.WebServiceClient;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class ScheduleService {

	//TODO mudar para subir exceções para a camada de controller
	public ListScheduleResponse list() {				
		try {
			Call<ListScheduleResponse> call = WebServiceClient.getInstance().getScheduleWebService().list();
			Response<ListScheduleResponse> response = call.execute();
			return response.body();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	//TODO mudar para subir exceções para a camada de controller
	public void create(Schedule schedule) {
		
		try {
			CreateScheduleRequest request = new CreateScheduleRequest();
			request.setWeekDay(schedule.getWeekDay());
			request.setScheduleStart(schedule.getScheduleStart());
			request.setScheduleEnd(schedule.getScheduleEnd());
			
			Call<Void> call = WebServiceClient.getInstance().getScheduleWebService().create(request);
			Response<Void> response = call.execute();
			
			if(!response.isSuccessful()) {			
				throw new ApiServiceException(response);
			}
			
		} catch (IOException e) {
			throw new ApiServiceException(MessagesUtil.getInstance().get("api_generic_error"));
		}
		
	}
	
	
	
}
