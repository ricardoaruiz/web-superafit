package br.com.rar.superafit.superafitbackoffice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rar.superafit.superafitbackoffice.controller.model.Schedule;
import br.com.rar.superafit.superafitbackoffice.model.ListScheduleResponse;
import br.com.rar.superafit.superafitbackoffice.service.ScheduleService;
import br.com.rar.superafit.superafitbackoffice.service.exception.ApiServiceException;
import br.com.rar.superafit.superafitbackoffice.utils.MessagesUtil;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list() {
		
		ListScheduleResponse list = scheduleService.list();
		
		ModelAndView mav = new ModelAndView("schedule/list");
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView add(Schedule schedule) {
		ModelAndView mav = new ModelAndView("schedule/add");
		return mav;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView save(@Valid Schedule schedule, BindingResult result
			, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return add(schedule);
		}
		
		ModelAndView mv = new ModelAndView("redirect:/schedule/add");
		try {
			scheduleService.create(schedule);			
			attributes.addFlashAttribute("success", MessagesUtil.getInstance().get("schedule_msg_saved_success"));
		} catch(ApiServiceException e) {
			attributes.addFlashAttribute("apiErrors", e.getErrors());
			attributes.addFlashAttribute("schedule", schedule);
		}
		return mv;
		
	}	
	
}
