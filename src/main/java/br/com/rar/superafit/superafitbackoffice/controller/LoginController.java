package br.com.rar.superafit.superafitbackoffice.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rar.superafit.superafitbackoffice.controller.model.LoginRequest;
import br.com.rar.superafit.superafitbackoffice.service.LoginService;
import br.com.rar.superafit.superafitbackoffice.service.exception.ApiServiceException;
import br.com.rar.superafit.superafitbackoffice.utils.SFConstants;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/")
	public ModelAndView login(LoginRequest loginRequest) {
		return new ModelAndView("login/login");
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ModelAndView confirmLogin(@Valid LoginRequest loginRequest, BindingResult result
			, RedirectAttributes attributes, HttpSession session) {

		ModelAndView mv = new ModelAndView("redirect:/principal");
		
		if (result.hasErrors()) {
			return login(loginRequest);
		}
		
		try{
			String jwtToken = loginService.confirmLogin(loginRequest);			
			session.setAttribute(LOGGED_USER, loginRequest);
			session.setAttribute(JWT_TOKEN, jwtToken);			
		} catch(ApiServiceException e) {
			mv.addObject(SFConstants.ExportViewValuesKey.API_ERRORS, e.getErrors());
			mv.addObject("loginRequest", loginRequest);
			mv.setViewName("login/login");
		}		
		
		return mv;
	}
	
}
