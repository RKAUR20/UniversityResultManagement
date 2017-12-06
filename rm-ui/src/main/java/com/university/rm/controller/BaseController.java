package com.university.rm.controller;

import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView upload() {
		ModelAndView model = new ModelAndView("upload");
		FileBucket fileBucket = new FileBucket();
		model.addObject("fileBucket", fileBucket);
		return model;
	}
	
	@RequestMapping(value = "/uploadServlet", method = RequestMethod.POST)
	public String uploadServlet(@ModelAttribute("fileBucket") FileBucket fileBucket) {
		inputHandlerServiceFacade.handleInputFromUI(fileBucket);
		return "success";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view() {
		ModelAndView model = new ModelAndView("search");
		return model;
	}
	
	@RequestMapping(value = "/searchStudentReport", method = RequestMethod.POST)
	public String searchStudentReport(@RequestParam("studentName") String studentName, HttpServletResponse response) throws IOException {
		File f = inputHandlerServiceFacade.handleOutput(studentName);
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename=" + f.getName());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(f));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
        response.getOutputStream().flush();
		return "home";
	}
	
}
