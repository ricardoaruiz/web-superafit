package br.com.rar.superafit.superafitbackoffice.controller;

import java.util.List;

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

import br.com.rar.superafit.superafitbackoffice.controller.model.Trainning;
import br.com.rar.superafit.superafitbackoffice.model.ListMovementResponse;
import br.com.rar.superafit.superafitbackoffice.model.ListTrainningResponse;
import br.com.rar.superafit.superafitbackoffice.model.TrainningTypeResponse;
import br.com.rar.superafit.superafitbackoffice.service.MovementService;
import br.com.rar.superafit.superafitbackoffice.service.TrainningService;
import br.com.rar.superafit.superafitbackoffice.service.TrainningTypeService;
import br.com.rar.superafit.superafitbackoffice.service.exception.ApiServiceException;
import br.com.rar.superafit.superafitbackoffice.utils.MessageEnum;
import br.com.rar.superafit.superafitbackoffice.utils.SFConstants;

@Controller
@RequestMapping("trainning")
public class TrainningController {

	private final Logger LOG = LoggerFactory.getLogger(TrainningController.class);
	
	@Autowired
	private TrainningService trainningService;
	
	@Autowired
	private TrainningTypeService trainningTypeService;
	
	@Autowired
	private MovementService movementService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("trainning/list");		
		try{
			LOG.info("Recuperando o treino diário");
			ListTrainningResponse trainning = trainningService.list();
			
			if(trainning == null || trainning.getData() == null && trainning.getData().isEmpty()) {
				mv.addObject(SFConstants.ExportViewValuesKey.INFORMATION, MessageEnum.TRAINNING_MSG_NOT_FOUND.getMsg());
			} else {			
				mv.addObject(SFConstants.ExportViewValuesKey.INFORMATION, trainning.isSync() ? null : MessageEnum.TRAINNING_MSG_REMIDER_PUBLISH.getMsg());
			}
			
			mv.addObject("list", trainning);
			LOG.info("Treino diário recuperado com sucesso.");
		} catch(ApiServiceException e) {
			LOG.error("Erro ao consultar o treino diário");
			mv.addObject(SFConstants.ExportViewValuesKey.API_ERRORS, e.getErrors());
		}		
		return mv;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView add(Trainning trainning) {
		ModelAndView mv = new ModelAndView("trainning/add");
		
		List<TrainningTypeResponse> trainningTypes = trainningTypeService.findAllTrainningType();
		mv.addObject("trainningTypes", trainningTypes);
		
		ListMovementResponse movements = movementService.findAll();
		mv.addObject("movements", movements!= null ? movements.getMovements() : null);
		
		return mv;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView confirmAdd(@Valid Trainning trainning, BindingResult result
			, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return add(trainning);
		}
		
		ModelAndView mv = new ModelAndView("redirect:/trainning/add");
		try{
			trainningService.save(trainning);
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.SUCCESS, MessageEnum.CREATE_TRAINNING_SUCCESS.getMsg());
		} catch(ApiServiceException e) {
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.API_ERRORS, e.getErrors());
			attributes.addFlashAttribute("trainning", trainning);
		}		
		return mv;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView confirmDelete(Trainning trainning, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/trainning");
		try{
			trainningService.delete(trainning.getId());
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.SUCCESS, MessageEnum.DELETE_TRAINNING_SUCCESS.getMsg());
		} catch(ApiServiceException e) {
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.API_ERRORS, e.getErrors());
			attributes.addFlashAttribute("trainning", trainning);
		}
		return mv;
	}
	
	@RequestMapping(value="notification", method=RequestMethod.POST)
	public ModelAndView notification(RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/trainning");
		try {
			trainningService.notification();
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.SUCCESS, MessageEnum.TRAINNING_MSG_PUBLISHED.getMsg());
		} catch(ApiServiceException e) {
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.API_ERRORS, e.getErrors());
		}
		return mv;
	}	
	
}
