package br.com.rar.superafit.superafitbackoffice.service.exception;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rar.superafit.superafitbackoffice.model.ErrorsResponse;
import br.com.rar.superafit.superafitbackoffice.utils.MessageEnum;
import retrofit2.Response;

@Component
public class ApiServiceException extends RuntimeException {

	private static final long serialVersionUID = -7760461237718342017L;

	private ErrorsResponse errors;
	
	public ApiServiceException() {
	
	}
	
	public ApiServiceException(Response response) {
		try {
			if(response.code() == HttpsURLConnection.HTTP_UNAVAILABLE) {
				addError(MessageEnum.API_MSG_UNAVAILABLE.getMsg());
			} else {
				ObjectMapper om = new ObjectMapper();
				errors = om.readValue(response.errorBody().string(), ErrorsResponse.class);
			}
		} catch (IOException e) {
			addError(MessageEnum.API_GENERIC_ERROR.getMsg());
			
		}
	}
	
	public ApiServiceException(String error) {
		addError(error);
	}

	public ErrorsResponse getErrors() {		
		return errors;
	}
	
	private void addError(String error) {
		if(errors == null) {
			errors = new ErrorsResponse();
		}
		errors.addError(error);
	}
	
}
