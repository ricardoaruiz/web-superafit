package br.com.rar.superafit.superafitbackoffice.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import br.com.rar.superafit.superafitbackoffice.controller.model.Schedule;
import br.com.rar.superafit.superafitbackoffice.model.CreateScheduleRequest;
import br.com.rar.superafit.superafitbackoffice.model.ListScheduleResponse;
import br.com.rar.superafit.superafitbackoffice.service.exception.ApiServiceException;
import br.com.rar.superafit.superafitbackoffice.utils.MessageEnum;
import br.com.rar.superafit.superafitbackoffice.webservice.WebServiceClient;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class ScheduleService {

	public ListScheduleResponse list() {				
		try {
			Call<ListScheduleResponse> call = WebServiceClient.getInstance().getScheduleWebService().list();
			Response<ListScheduleResponse> response = call.execute();
			
			if(!response.isSuccessful()) {			
				throw new ApiServiceException(response);
			}
			
			return response.body();
		} catch (IOException e) {
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}
	}

	public void create(Schedule schedule) {		
		try {			
			Call<Void> call = WebServiceClient.getInstance().getScheduleWebService().create(getCreateScheduleRequest(schedule));
			Response<Void> response = call.execute();
			
			if(!response.isSuccessful()) {			
				throw new ApiServiceException(response);
			}			
		} catch (IOException e) {
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}		
	}

	private CreateScheduleRequest getCreateScheduleRequest(Schedule schedule) {
		CreateScheduleRequest request = new CreateScheduleRequest();
		request.setWeekDay(schedule.getWeekDay());
		request.setScheduleStart(schedule.getScheduleStart());
		request.setScheduleEnd(schedule.getScheduleEnd());
		return request;
	}
	
}
