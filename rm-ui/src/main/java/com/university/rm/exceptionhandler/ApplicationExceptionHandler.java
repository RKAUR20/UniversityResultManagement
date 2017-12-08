package com.university.rm.exceptionhandler;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.university.rm.customexceptions.ApplicationExceptionFactory;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	final Logger logger = Logger.getLogger(ApplicationExceptionHandler.class); 
	
	private static final String DEFAULT_EXCEPTION_MESSAGE = "Unknown error occured. Please contact system administrator or try again later!";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleExceptions(Exception ex){
		logger.debug("Exception received. Handling Exception.");
		ModelAndView model = new ModelAndView("exception");
		if (ex instanceof ApplicationExceptionFactory) {
			logger.debug("Custom Application exception received :: " + ((ApplicationExceptionFactory) ex).getExceptionMessage());
			model.addObject("exceptionMessage", ((ApplicationExceptionFactory) ex).getExceptionMessage());
		}else {
			logger.debug("Unknown exception received :: " + ex.getMessage());
			model.addObject("exceptionMessage", DEFAULT_EXCEPTION_MESSAGE);
		}
		return model;
	}

}
