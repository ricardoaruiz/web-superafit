package br.com.rar.superafit.superafitbackoffice.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rar.superafit.superafitbackoffice.controller.model.ExerciceRequest;
import br.com.rar.superafit.superafitbackoffice.model.ListMovementResponse;
import br.com.rar.superafit.superafitbackoffice.model.MovementResponse;
import br.com.rar.superafit.superafitbackoffice.service.exception.ApiServiceException;
import br.com.rar.superafit.superafitbackoffice.utils.MessageEnum;
import br.com.rar.superafit.superafitbackoffice.webservice.WebServiceClient;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class MovementService {

	private final Logger LOG = LoggerFactory.getLogger(MovementService.class);
	
	@Autowired
	private WebServiceClient webServiceClient;
	
	public void save(ExerciceRequest exerciceRequest, String jwtToken) {
		br.com.rar.superafit.superafitbackoffice.model.ExerciceRequest request = createExerciceRequest(exerciceRequest);
		
		try {
			Call<Void> call = webServiceClient.getMovementWebService().save(request, jwtToken);
			Response<Void> response = call.execute();
			
			LOG.info("Cadastro de exercício http-status: " + response.code());
			if(!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Cadastro de exercício body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);
			}
			
		} catch (IOException e) {
			LOG.error("Erro cadastrar exercício", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}			
		
	}
	
	private br.com.rar.superafit.superafitbackoffice.model.ExerciceRequest createExerciceRequest(
			ExerciceRequest exerciceRequest) {

		br.com.rar.superafit.superafitbackoffice.model.ExerciceRequest request = 
				new br.com.rar.superafit.superafitbackoffice.model.ExerciceRequest();
		
		request.setId(exerciceRequest.getId());
		request.setName(exerciceRequest.getName());
		request.setTranslate(exerciceRequest.getTranslate());
		request.setActive(exerciceRequest.isActive() ? "1" : "0");
		request.setDescription(exerciceRequest.getDescription());
		
		return request;
	}

	public MovementResponse findOne(Long id, String jwtToken) {
		try {
			Call<MovementResponse> call = webServiceClient.getMovementWebService().findOne(id, jwtToken);
			Response<MovementResponse> response = call.execute();
			
			LOG.info("Consulta de exercício http-status: " + response.code());
			if(!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Consulta exercicio body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);
			}
			
			return response.body();			
		} catch (IOException e) {
			LOG.error("Erro ao consultar exercício", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}		
	}
	
	public ListMovementResponse findAllActived(String jwtToken) {
		try {
			Call<ListMovementResponse> call = webServiceClient.getMovementWebService().findAllActived(jwtToken);
			Response<ListMovementResponse> response = call.execute();
			
			LOG.info("Consulta de exercícios http-status: " + response.code());
			if(!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Consulta exercicios body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);
			}
			
			return response.body();			
		} catch (IOException e) {
			LOG.error("Erro ao consultar exercícios", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}
	}
	
	public ListMovementResponse findAll(String jwtToken) {
		try {
			Call<ListMovementResponse> call = webServiceClient.getMovementWebService().findAll(jwtToken);
			Response<ListMovementResponse> response = call.execute();
			
			LOG.info("Consulta de exercícios http-status: " + response.code());
			if(!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Consulta exercicios body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);
			}
			
			return response.body();			
		} catch (IOException e) {
			LOG.error("Erro ao consultar exercícios", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}
	}	
	
}
