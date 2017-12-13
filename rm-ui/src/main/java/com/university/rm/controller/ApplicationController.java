package com.university.rm.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.university.rm.facade.OutputReportHandlerServiceFacade;
import com.university.rm.facade.UploadHandlerServiceFacade;
import com.university.rm.model.FileBucket;

@Controller
public class ApplicationController {
	
	final Logger logger = Logger.getLogger(ApplicationController.class); 

	@Autowired
	private UploadHandlerServiceFacade uploadHandlerServiceFacade;
	
	@Autowired
	private OutputReportHandlerServiceFacade outputReportHandlerServiceFacade;

	public UploadHandlerServiceFacade getUploadHandlerServiceFacade() {
		return uploadHandlerServiceFacade;
	}

	public void setUploadHandlerServiceFacade(UploadHandlerServiceFacade uploadHandlerServiceFacade) {
		this.uploadHandlerServiceFacade = uploadHandlerServiceFacade;
	}
	
	public OutputReportHandlerServiceFacade getOutputReportHandlerServiceFacade() {
		return outputReportHandlerServiceFacade;
	}

	public void setOutputReportHandlerServiceFacade(OutputReportHandlerServiceFacade outputReportHandlerServiceFacade) {
		this.outputReportHandlerServiceFacade = outputReportHandlerServiceFacade;
	}
	

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
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
	public String uploadServlet(@ModelAttribute("fileBucket") FileBucket fileBucket){
		logger.debug("Upload XML data request received.");
		getUploadHandlerServiceFacade().handleUploadService(fileBucket);
		logger.debug("Upload XML data request completed.");
		return "success";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view() {
		ModelAndView model = new ModelAndView("search");
		return model;
	}

	@RequestMapping(value = "/searchStudentReport", method = RequestMethod.POST)
	public void searchStudentReport(@RequestParam("studentName") String studentName, HttpServletResponse response)
			throws Exception {
		logger.debug("Search student report request for "+ studentName +" received.");
		File f = getOutputReportHandlerServiceFacade().handleOutputReportService(studentName);
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename=" + f.getName());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(f));

		FileCopyUtils.copy(inputStream, response.getOutputStream());
		response.getOutputStream().flush();
		logger.debug("Search student report request for "+ studentName +" completed.");
		//return "home";
	}


}
