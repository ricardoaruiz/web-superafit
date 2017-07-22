package br.com.rar.superafit.superafitbackoffice.controller;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import br.com.rar.superafit.superafitbackoffice.service.exception.ApiServiceException;
import br.com.rar.superafit.superafitbackoffice.utils.SFConstants;

public class BaseController {

	protected static final String JWT_TOKEN = "jwtToken";
	protected static final String LOGGED_USER = "usuarioLogado";

	protected void handleApiServiceException(ApiServiceException exception, 
			ModelAndView mv, HttpSession session) {
		switch (exception.getHttpCode()) {
			case HttpsURLConnection.HTTP_FORBIDDEN:
				session.invalidate();
				mv.setViewName("redirect:/");			
				break;
			case HttpsURLConnection.HTTP_GONE:
				session.invalidate();
				mv.setViewName("login/login");
				mv.addObject(SFConstants.ExportViewValuesKey.INFORMATION, exception.getErrors());
				break;
			default:
				break;
		}
	}
	
	protected String getJwtToken(HttpSession session) {
		if (session.getAttribute(JWT_TOKEN) != null) {
			return session.getAttribute(JWT_TOKEN).toString();
		}
		return null;
	}
	
}
