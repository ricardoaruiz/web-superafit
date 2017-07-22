package br.com.rar.superafit.superafitbackoffice.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rar.superafit.superafitbackoffice.model.TrainningTypeResponse;
import br.com.rar.superafit.superafitbackoffice.service.exception.ApiServiceException;
import br.com.rar.superafit.superafitbackoffice.utils.MessageEnum;
import br.com.rar.superafit.superafitbackoffice.webservice.WebServiceClient;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class TrainningTypeService {

	private final Logger LOG = LoggerFactory.getLogger(TrainningTypeService.class);
	
	@Autowired
	private WebServiceClient webServiceClient;
	
	public List<TrainningTypeResponse> findAllTrainningType(String jwtToken) {
		try {
			Call<List<TrainningTypeResponse>> call = webServiceClient.getTrainningTypeService().findAll(jwtToken);
			Response<List<TrainningTypeResponse>> response = call.execute();
			
			LOG.info("Consulta de tipos de treinamento http-status: " + response.code());
			if(!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Consulta tipos de treinamento body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);
			}
			
			return response.body();			
		} catch (IOException e) {
			LOG.error("Erro ao consultar os tipos de treinamento", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}
	}
	
}
