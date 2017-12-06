package com.university.rm.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.university.rm.customexceptions.ApplicationExceptionFactory;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	private static final String DEFAULT_EXCEPTION_MESSAGE = "Unknown error occured. Please contact system administrator or try again later!";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleExceptions(Exception ex){
		System.out.println("handleExceptions");
		ModelAndView model = new ModelAndView("exception");
		if (ex instanceof ApplicationExceptionFactory) {
			model.addObject("exceptionMessage", ((ApplicationExceptionFactory) ex).getExceptionMessage());
		}else {
			model.addObject("exceptionMessage", DEFAULT_EXCEPTION_MESSAGE);
		}
		return model;
	}

}
