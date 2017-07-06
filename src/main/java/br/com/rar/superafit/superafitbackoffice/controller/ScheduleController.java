package br.com.rar.superafit.superafitbackoffice.controller;

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

import br.com.rar.superafit.superafitbackoffice.controller.model.Schedule;
import br.com.rar.superafit.superafitbackoffice.enumeration.WeekDayEnum;
import br.com.rar.superafit.superafitbackoffice.model.ListScheduleResponse;
import br.com.rar.superafit.superafitbackoffice.service.ScheduleService;
import br.com.rar.superafit.superafitbackoffice.service.exception.ApiServiceException;
import br.com.rar.superafit.superafitbackoffice.utils.MessageEnum;
import br.com.rar.superafit.superafitbackoffice.utils.SFConstants;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

	private final Logger LOG = LoggerFactory.getLogger(ScheduleController.class);
	
	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(RedirectAttributes attributes) {
		
		LOG.info("Listando os horários");
		
		ModelAndView mav = new ModelAndView("schedule/list");
		try {
			ListScheduleResponse list = scheduleService.list();			
						
			if(list == null || list.getSchedules() == null && list.getSchedules().isEmpty()) {
				mav.addObject(SFConstants.ExportViewValuesKey.INFORMATION, MessageEnum.SCHEDULE_MSG_NOT_FOUND.getMsg());
			} else {			
				mav.addObject(SFConstants.ExportViewValuesKey.INFORMATION, list.isSync() ? null : MessageEnum.SCHEDULE_MSG_REMIDER_PUBLISH.getMsg());
			}
			
			LOG.info("Horários listados com sucesso.");
			mav.addObject("list", list);
		} catch(ApiServiceException e) {
			LOG.error("Erro ao consultar os horários");
			mav.addObject(SFConstants.ExportViewValuesKey.API_ERRORS, e.getErrors());
		}
		return mav;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView add(Schedule schedule) {
		ModelAndView mav = new ModelAndView("schedule/add");
		mav.addObject("weekDays", WeekDayEnum.values());
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
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.SUCCESS, MessageEnum.SCHEDULE_MSG_SAVED_SUCCESS.getMsg());
		} catch(ApiServiceException e) {
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.API_ERRORS, e.getErrors());
			attributes.addFlashAttribute("schedule", schedule);
		}
		return mv;
		
	}	
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public ModelAndView delete(Schedule schedule, RedirectAttributes attributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/schedule");
		try {
			scheduleService.remove(schedule);			
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.SUCCESS, MessageEnum.SCHEDULE_MSG_REMOVED_SUCCESS.getMsg());
		} catch(ApiServiceException e) {
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.API_ERRORS, e.getErrors());
		}
		return mv;
		
	}

	@RequestMapping(value="notification", method=RequestMethod.POST)
	public ModelAndView notification(RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/schedule");
		try {
			scheduleService.notification();
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.SUCCESS, MessageEnum.SCHEDULE_MSG_PUBLISHED.getMsg());
		} catch(ApiServiceException e) {
			attributes.addFlashAttribute(SFConstants.ExportViewValuesKey.API_ERRORS, e.getErrors());
		}
		return mv;
	}
	
}
