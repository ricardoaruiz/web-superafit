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
import br.com.rar.superafit.superafitbackoffice.model.TrainningTypeResponse;
import br.com.rar.superafit.superafitbackoffice.service.MovementService;
import br.com.rar.superafit.superafitbackoffice.service.TrainningTypeService;

@Controller
@RequestMapping("trainning")
public class TrainningController {

	private final Logger LOG = LoggerFactory.getLogger(TrainningController.class);
	
	@Autowired
	private TrainningTypeService trainningTypeService;
	
	@Autowired
	private MovementService movementService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	private ModelAndView add(Trainning trainning) {
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
		return mv;
	}
	
}
