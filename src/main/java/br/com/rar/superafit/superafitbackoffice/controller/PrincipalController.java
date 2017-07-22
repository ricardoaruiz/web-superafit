package br.com.rar.superafit.superafitbackoffice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {
	
	@RequestMapping(value="principal", method=RequestMethod.GET)
	public ModelAndView principal(HttpSession session) {
		ModelAndView mav = new ModelAndView("principal/principal");
		return mav;
	}
	
}
