package br.com.rar.superafit.superafitbackoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {

	@RequestMapping("/")
	public ModelAndView root() {
		return new ModelAndView("redirect:/principal");
	}
	
	@RequestMapping(value="principal", method=RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView mav = new ModelAndView("principal/principal");
		return mav;
	}
	
}
