package br.com.rar.superafit.superafitbackoffice.service.exception;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rar.superafit.superafitbackoffice.model.ErrorsResponse;
import br.com.rar.superafit.superafitbackoffice.utils.MessageEnum;

@Component
public class ApiServiceException extends RuntimeException {

	private final Logger LOG = LoggerFactory.getLogger(ApiServiceException.class);
	
	private static final long serialVersionUID = -7760461237718342017L;

	private ErrorsResponse errors;
	
	private int httpCode;
	
	public ApiServiceException() {
	
	}
	
	public ApiServiceException(final int httpCode, String bodyError) {
		this.httpCode = httpCode;
		try {
			ObjectMapper om = new ObjectMapper();
			switch (httpCode) {
			case HttpsURLConnection.HTTP_UNAVAILABLE:
				LOG.error("Servidor API indisponível");
				addError(MessageEnum.API_MSG_UNAVAILABLE.getMsg());
				break;
			case HttpURLConnection.HTTP_UNAUTHORIZED:
				errors = om.readValue(getInvalidUserBodyError(), ErrorsResponse.class);
				break;
			case 422:
				LOG.error("Validação de negócio");
				errors = om.readValue(bodyError, ErrorsResponse.class);
				break;
			case HttpsURLConnection.HTTP_INTERNAL_ERROR:
				LOG.error("Erro interno no servidor API");
				if (bodyError.contains("ExpiredJwtException")) {
					this.httpCode = HttpsURLConnection.HTTP_GONE; 
					errors = om.readValue(getSessionExpiredBodyError(), ErrorsResponse.class);
				} else {
					addError(MessageEnum.API_MSG_INTERNAL_SERVER_ERROR.getMsg());
				}
				break;
			default:
				LOG.error("Erro não mapeado");
				addError(MessageEnum.API_UNMAPPED_ERROR.getMsg());
				break;
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
	
	private String getInvalidUserBodyError() {		
		return "{\"errors\": [{\"error\":\"" + MessageEnum.API_MSG_UNAUTHORIZED.getMsg() + "\"}]}";		
	}
	
	private String getSessionExpiredBodyError() {
		return "{\"errors\": [{\"error\":\"" + MessageEnum.API_MSG_JWT_TOKEN_EXPIRED.getMsg() + "\"}]}";
	}

	public int getHttpCode() {
		return httpCode;
	}
	
}
