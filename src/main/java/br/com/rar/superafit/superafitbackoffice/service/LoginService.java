package br.com.rar.superafit.superafitbackoffice.service;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rar.superafit.superafitbackoffice.controller.model.LoginRequest;
import br.com.rar.superafit.superafitbackoffice.service.exception.ApiServiceException;
import br.com.rar.superafit.superafitbackoffice.utils.MessageEnum;
import br.com.rar.superafit.superafitbackoffice.webservice.WebServiceClient;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class LoginService {

	private final Logger LOG = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private WebServiceClient webServiceClient;
	
	public String confirmLogin(LoginRequest login) {
				
		try {
			Call<Void> call = webServiceClient.getLoginWebService().login(getLoginRequest(login));
			Response<Void> response = call.execute();
			
			LOG.info("Login http-status: " + response.code());
			if(!response.isSuccessful()) {
				String bodyError = response.errorBody().string();
				LOG.info("Login body-error: " + bodyError);						
				throw new ApiServiceException(response.code(), bodyError);				
			}
			
			String jwtToken = response.headers().get("Authorization");
			jwtToken = jwtToken.replaceAll("Superafit ", "");
			return jwtToken;
			
		} catch (IOException e) {
			LOG.error("Erro ao criar treino diário", e);
			throw new ApiServiceException(MessageEnum.API_GENERIC_ERROR.getMsg());
		}
		
	}

	private br.com.rar.superafit.superafitbackoffice.model.LoginRequest getLoginRequest(LoginRequest login) {
		br.com.rar.superafit.superafitbackoffice.model.LoginRequest request = new br.com.rar.superafit.superafitbackoffice.model.LoginRequest();
		request.setUserName(login.getLogin());
		request.setPassword(login.getPassword());
		return request;
	}
	
}
