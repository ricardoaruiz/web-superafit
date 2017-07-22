package br.com.rar.superafit.superafitbackoffice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rar.superafit.superafitbackoffice.controller.model.TrainningRequest;
import br.com.rar.superafit.superafitbackoffice.model.CreateTrainningRequest;
import br.com.rar.superafit.superafitbackoffice.model.ListTrainningResponse;
import br.com.rar.superafit.superafitbackoffice.model.MovementRequest;
import br.com.rar.superafit.superafitbackoffice.model.TrainningResponse;
import br.com.rar.superafit.superafitbackoffice.service.exception.ApiServiceException;
import br.com.rar.superafit.superafitbackoffice.utils.DateFormatUtil;
import br.com.rar.superafit.superafitbackoffice.utils.MessageEnum;
import br.com.rar.superafit.superafitbackoffice.webservice.WebServiceClient;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class TrainningService {

	private final Logger LOG = LoggerFactory.getLogger(TrainningService.class);

	@Autowired
	private WebServiceClient webServiceClient;

	public ListTrainningResponse list(String jwtToken) {
		
		try {
			Call<ListTrainningResponse> call = webServiceClient.getTrainningService().getTrainning(
					DateFormatUtil.toString(new Date(), DateFormatUtil.Format.DIA_MES_ANO), jwtToken);
		
			Response<ListTrainningResponse> response = call.execute();
			
			LOG.info("Listagem do treino diário http-status: " + response.code());
			if(!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Listagem do treino diário body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);				
			}
		
			return response.body();
		} catch (IOException e) {
			LOG.error("Erro ao criar treino diário", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}
	}

	public TrainningResponse get(String id, String jwtToken) {
		try {
			Call<TrainningResponse> call = webServiceClient.getTrainningService().getSpecificTrainning(id, jwtToken);
			Response<TrainningResponse> response = call.execute();
			
			LOG.info("Busca do treino diário específico http-status: " + response.code());
			if (!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Busca do treino diário específico body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);				
			}
			
			return response.body();
		} catch (IOException e) {
			LOG.error("Erro ao obter treino diário específico", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}
	}
	
	public void save(TrainningRequest trainning, String jwtToken) {
		try {
			CreateTrainningRequest trainningRequest = getTrainningRequest(trainning);
			Call<Void> call = webServiceClient.getTrainningService().create(trainningRequest, jwtToken);
			Response<Void> response = call.execute();
			
			LOG.info("Criação de treino diário http-status: " + response.code());
			if(!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Criação de treino diário body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);
			}
		} catch (IOException e) {
			LOG.error("Erro ao criar treino diário", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}
	}
	
	public void update(TrainningRequest trainning, String jwtToken) {
		delete(trainning.getId(), jwtToken);
		save(trainning, jwtToken);		
	}
	
	public void delete(String id, String jwtToken) {
		try {
			Call<Void> call = webServiceClient.getTrainningService().delete(id, jwtToken);
			Response<Void> response = call.execute();
			
			LOG.info("Remoção de treino diário http-status: " + response.code());
			if(!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Remoção de treino diário body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);				
			}
			
		} catch (IOException e) {
			LOG.error("Erro ao remover treino diário", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}
		
	}
	
	public void notification(String jwtToken) {
		try {
			Call<Void> call = webServiceClient.getTrainningService().notification(jwtToken);
			Response<Void> response = call.execute();
			
			LOG.info("Notificação de treino diário http-status: " + response.code());
			if(!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Notificação de treino diário body-error: " + bodyError);
				throw new ApiServiceException(response.code(), bodyError);
			}			
			
		} catch (IOException e) {
			LOG.error("Erro ao notificar o treino diário", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}
	}	
	
	private CreateTrainningRequest getTrainningRequest(TrainningRequest trainning) {
		CreateTrainningRequest request = new CreateTrainningRequest();
		
		request.setDate(trainning.getDate());
		request.setDescription(trainning.getDescription());
		request.setRound(trainning.getRound());
		request.setType(trainning.getType());
		
		request.setMovements(getMovementList(trainning));
		
		return request;
	}

	private List<MovementRequest> getMovementList(TrainningRequest trainning) {
		
		List<MovementRequest> toReturn = new ArrayList<MovementRequest>();
		
		String[] movements = trainning.getMovementId().split(",");
		String[] repetitions = trainning.getMovementRepetition().split(",");
		
		for(int i=0; i<movements.length; i++) {
			toReturn.add(new MovementRequest(movements[i], repetitions[i]));
		}
		
		return toReturn;
	}

}
