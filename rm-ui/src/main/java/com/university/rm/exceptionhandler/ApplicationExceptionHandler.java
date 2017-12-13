package com.university.rm.exceptionhandler;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.university.rm.customexceptions.InputFileUnmarshalException;
import com.university.rm.customexceptions.JSONReportsGenerationException;
import com.university.rm.customexceptions.ReportNotFoundException;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	final Logger logger = Logger.getLogger(ApplicationExceptionHandler.class); 
	
	private static final String DEFAULT_EXCEPTION_MESSAGE = "Unknown error occured. Please contact system administrator or try again later!";

	@ExceptionHandler(value = ReportNotFoundException.class)
	public ModelAndView handleReportNotFoundException(ReportNotFoundException ex) {
		logger.debug("Exception received." + ex.getExceptionMessage());
		ModelAndView model = new ModelAndView("exception");
		model.addObject("exceptionMessage", ex.getExceptionMessage());
		return model;
	}
	
	@ExceptionHandler(value = JSONReportsGenerationException.class)
	public ModelAndView handleJSONReportsGenerationException(JSONReportsGenerationException ex) {
		logger.debug("Exception received." + ex.getExceptionMessage());
		ModelAndView model = new ModelAndView("exception");
		model.addObject("exceptionMessage", ex.getExceptionMessage());
		return model;
	}
	
	@ExceptionHandler(value = InputFileUnmarshalException.class)
	public ModelAndView handleInputFileUnmarshalException(InputFileUnmarshalException ex) {
		logger.debug("Exception received." + ex.getExceptionMessage());
		ModelAndView model = new ModelAndView("exception");
		model.addObject("exceptionMessage", ex.getExceptionMessage());
		return model;
	}
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(Exception ex) {
		logger.debug("Exception received." + ex.getMessage());
		ModelAndView model = new ModelAndView("exception");
		model.addObject("exceptionMessage", DEFAULT_EXCEPTION_MESSAGE);
		return model;
	}
	
	

}
