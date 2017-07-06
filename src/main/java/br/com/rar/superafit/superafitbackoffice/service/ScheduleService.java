package br.com.rar.superafit.superafitbackoffice.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rar.superafit.superafitbackoffice.controller.model.Schedule;
import br.com.rar.superafit.superafitbackoffice.model.CreateScheduleRequest;
import br.com.rar.superafit.superafitbackoffice.model.ListScheduleResponse;
import br.com.rar.superafit.superafitbackoffice.model.RemoveScheduleRequest;
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
				String bodyError = response.errorBody().string();
				LOG.info("Consulta horários body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);
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
				String bodyError = response.errorBody().string();
				LOG.info("Criar horário body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);
			}			
		} catch (IOException e) {
			LOG.error("Erro ao criar horário", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}		
	}
	
	public void remove(Schedule schedule) {
		try {
			Call<Void> call = webServiceClient.getScheduleWebService().remove(getRemoveScheduleRequest(schedule));
			Response<Void> response = call.execute();
			
			LOG.info("Remover horário http-status: " + response.code());
			if(!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Remover horário body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);
			}
			
		} catch(IOException e) {
			LOG.error("Erro ao criar horário", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());			
		}
	}
	
	public void notification() {
		try {
			Call<Void> call = webServiceClient.getScheduleWebService().notification();
			Response<Void> response = call.execute();
			
			LOG.info("Notificação de horários http-status: " + response.code());
			if(!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Notificação de horários body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);
			}			
			
		} catch (IOException e) {
			LOG.error("Erro ao notificar os horários", e);
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

	private RemoveScheduleRequest getRemoveScheduleRequest(Schedule schedule) {
		return new RemoveScheduleRequest(schedule.getWeekDay(), schedule.getScheduleStart());
	}
}
