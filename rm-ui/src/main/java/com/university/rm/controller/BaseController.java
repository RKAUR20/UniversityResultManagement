package com.university.rm.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.university.rm.model.Student;

@Controller
public class BaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(String locale, Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/uploadServlet", method = RequestMethod.POST)
	public String uploadServlet(@ModelAttribute("file") MultipartFile multiPartFileUpload) throws JAXBException, IllegalStateException, IOException {
		File fileUpload = new File(multiPartFileUpload.getOriginalFilename());
		multiPartFileUpload.transferTo(fileUpload);
		JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);  
		   
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        Student que= (Student) jaxbUnmarshaller.unmarshal(fileUpload); 
        
		return "index";
	}
	
}
