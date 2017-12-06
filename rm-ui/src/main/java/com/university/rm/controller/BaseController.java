package com.university.rm.controller;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.university.rm.facade.InputHandlerServiceFacade;
import com.university.rm.model.FileBucket;

@Controller
public class BaseController {
	
	@Autowired
	InputHandlerServiceFacade inputHandlerServiceFacade;

	public InputHandlerServiceFacade getInputHandlerServiceFacade() {
		return inputHandlerServiceFacade;
	}

	public void setInputHandlerServiceFacade(InputHandlerServiceFacade inputHandlerServiceFacade) {
		this.inputHandlerServiceFacade = inputHandlerServiceFacade;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("index");
		FileBucket fileBucket = new FileBucket();
		model.addObject("fileBucket", fileBucket);
		return model;
	}
	
	@RequestMapping(value = "/uploadServlet", method = RequestMethod.POST)
	public String uploadServlet(@ModelAttribute("fileBucket") FileBucket fileBucket) {
		inputHandlerServiceFacade.handleInputFromUI(fileBucket);
		return "success";
	}
	
}
