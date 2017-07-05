package br.com.rar.superafit.superafitbackoffice.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	private final Logger LOG = LoggerFactory.getLogger(ScheduleService.class);
	
	@Autowired
	private WebServiceClient webServiceClient;
	
	public ListScheduleResponse list() {				
		try {
			Call<ListScheduleResponse> call = webServiceClient.getScheduleWebService().list();
			Response<ListScheduleResponse> response = call.execute();
			
			LOG.info("Consulta horários http-status: " + response.code());
			if(!response.isSuccessful()) {
				LOG.info("Consulta horários body-error: " + response.errorBody().string());
				throw new ApiServiceException(response);
			}
			
			return response.body();
		} catch (IOException e) {
			LOG.error("Erro ao consultar os horários", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}
	}

	public void create(Schedule schedule) {		
		try {			
			Call<Void> call = webServiceClient.getScheduleWebService().create(getCreateScheduleRequest(schedule));
			Response<Void> response = call.execute();
			
			LOG.info("Criar horário http-status: " + response.code());
			if(!response.isSuccessful()) {
				LOG.info("Criar horário body-error: " + response.errorBody().string());
				throw new ApiServiceException(response);
			}			
		} catch (IOException e) {
			LOG.error("Erro ao criar horário", e);
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
