package br.com.rar.superafit.superafitbackoffice.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import br.com.rar.superafit.superafitbackoffice.model.ListScheduleResponse;
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
	
	
	
	
}
