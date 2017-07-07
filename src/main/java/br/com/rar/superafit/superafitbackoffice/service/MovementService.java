package br.com.rar.superafit.superafitbackoffice.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rar.superafit.superafitbackoffice.model.ListMovementResponse;
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
	
	public ListMovementResponse findAll() {
		try {
			Call<ListMovementResponse> call = webServiceClient.getMovementWebService().findAll();
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
