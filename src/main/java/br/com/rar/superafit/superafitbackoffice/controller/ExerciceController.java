package br.com.rar.superafit.superafitbackoffice.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rar.superafit.superafitbackoffice.controller.model.ExerciceRequest;
import br.com.rar.superafit.superafitbackoffice.model.ListMovementResponse;
import br.com.rar.superafit.superafitbackoffice.model.MovementResponse;
import br.com.rar.superafit.superafitbackoffice.service.MovementService;
import br.com.rar.superafit.superafitbackoffice.service.exception.ApiServiceException;
import br.com.rar.superafit.superafitbackoffice.utils.MessageEnum;
import br.com.rar.superafit.superafitbackoffice.utils.SFConstants;

@Controller
@RequestMapping("exercice")
public class ExerciceController extends BaseController {

	private final Logger LOG = LoggerFactory.getLogger(ExerciceController.class);
	
	private static final String FORM_PATH = "register/exercice/form";
	
	@Autowired
	private MovementService exerciceService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView callForm(ExerciceRequest exerciceRequest, HttpSession session) {		
		
		ModelAndView mv = new ModelAndView(FORM_PATH);
		
		try{
			String jwtToken = getJwtToken(session);
			
			if(exerciceRequest.getId() != null && !exerciceRequest.getId().isEmpty()) {
				MovementResponse exercice = exerciceService.findOne(Long.valueOf(exerciceRequest.getId()), jwtToken);
				populateRequest(exercice, exerciceRequest);
			}
			
			ListMovementResponse exercices = exerciceService.findAll(jwtToken);
			mv.addObject("exercices", exercices == null ? null : exercices.getMovements());
		} catch(ApiServiceException e) {
			handleApiServiceException(e, mv, session);
		}
		
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView confirmForm(@Valid ExerciceRequest exerciceRequest, BindingResult result
			, RedirectAttributes attributes, HttpSession session) {		
		
		if (result.hasErrors()) {
			return callForm(exerciceRequest, session);
		}
		
		String jwtToken = getJwtToken(session);
		ModelAndView mv = new ModelAndView(FORM_PATH);		
		try{
			
			exerciceService.save(exerciceRequest, jwtToken);
			
			mv.setViewName("redirect:/exercice");
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.SUCCESS, MessageEnum.CREATE_EXERCICE_SUCCESS.getMsg());
			
			ListMovementResponse exercices = exerciceService.findAll(jwtToken);
			attributes.addFlashAttribute("exercices", exercices.getMovements());
			
		} catch(ApiServiceException e) {
			mv.addObject(SFConstants.ExportViewValuesKey.API_ERRORS, e.getErrors());
			mv.addObject("exerciceRequest", exerciceRequest);
			
			ListMovementResponse exercices = exerciceService.findAll(jwtToken);
			mv.addObject("exercices", exercices == null ? null : exercices.getMovements());			
			
			handleApiServiceException(e, mv, session);
		}		
		return mv;
	}

	private void populateRequest(MovementResponse exercice, ExerciceRequest exerciceRequest) {
		exerciceRequest.setId(exercice.getId());
		exerciceRequest.setDescription(exercice.getDescription());
		exerciceRequest.setName(exercice.getName());
		exerciceRequest.setTranslate(exercice.getTranslate());
		exerciceRequest.setActive(exercice.isActive());
	}
	
}
