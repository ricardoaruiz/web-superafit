package br.com.rar.superafit.superafitbackoffice.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rar.superafit.superafitbackoffice.controller.model.Pessoa;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView novo(Pessoa pessoa) {
		ModelAndView mav = new ModelAndView("principal");
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult result
			, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return novo(pessoa);
		}
		
		ModelAndView mv = new ModelAndView("redirect:/principal");
		attributes.addFlashAttribute("mensagem", "Pessoa salva com sucesso.");
		return mv;
		
	}	
	
}
