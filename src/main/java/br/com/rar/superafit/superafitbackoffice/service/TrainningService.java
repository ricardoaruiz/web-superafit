package br.com.rar.superafit.superafitbackoffice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rar.superafit.superafitbackoffice.controller.model.Trainning;
import br.com.rar.superafit.superafitbackoffice.model.CreateTrainningRequest;
import br.com.rar.superafit.superafitbackoffice.model.ListTrainningResponse;
import br.com.rar.superafit.superafitbackoffice.model.MovementRequest;
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

	public ListTrainningResponse list() {
		
		try {
			Call<ListTrainningResponse> call = webServiceClient.getTrainningService().getTrainning(
					DateFormatUtil.toString(new Date(), DateFormatUtil.Format.DIA_MES_ANO));
		
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
	
	public void save(Trainning trainning) {
		try {
			CreateTrainningRequest trainningRequest = getTrainningRequest(trainning);
			Call<Void> call = webServiceClient.getTrainningService().create(trainningRequest);
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
	
	public void delete(String id) {
		try {
			Call<Void> call = webServiceClient.getTrainningService().delete(id);
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
	
	public void notification() {
		try {
			Call<Void> call = webServiceClient.getTrainningService().notification();
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
	
	private CreateTrainningRequest getTrainningRequest(Trainning trainning) {
		CreateTrainningRequest request = new CreateTrainningRequest();
		
		request.setDate(trainning.getDate());
		request.setDescription(trainning.getDescription());
		request.setRound(trainning.getRound());
		request.setType(trainning.getType());
		
		request.setMovements(getMovementList(trainning));
		
		return request;
	}

	private List<MovementRequest> getMovementList(Trainning trainning) {
		
		List<MovementRequest> toReturn = new ArrayList<MovementRequest>();
		
		String[] movements = trainning.getMovementId().split(",");
		String[] repetitions = trainning.getMovementRepetition().split(",");
		
		for(int i=0; i<movements.length; i++) {
			toReturn.add(new MovementRequest(movements[i], repetitions[i]));
		}
		
		return toReturn;
	}

}
