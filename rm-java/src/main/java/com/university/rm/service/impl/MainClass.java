package com.university.rm.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import com.university.rm.model.Student;
import com.university.rm.model.Students;
import com.university.rm.model.Subject;

public class MainClass {
	
	public static void main(String [] args) throws JAXBException, JsonGenerationException, JsonMappingException, IOException {
		File fileUpload = new File("C:\\Users\\rkau23\\students.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);  
		   
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        Students students= (Students) jaxbUnmarshaller.unmarshal(fileUpload);
        
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(students.getStudents().get(0));
        
        ResultCalculatorServiceImpl resultCalculatorService= new ResultCalculatorServiceImpl();
        resultCalculatorService.calculateStudentsResult(students.getStudents());
        
        FileHandlerServiceImpl impl = new FileHandlerServiceImpl();
        impl.createJSONReports(students.getStudents());
        
        System.out.println(json);
        
	}
	
	static boolean isStudentPass(List<Subject> subjects) {
		for(Subject subject : subjects) {
			if(subject.getMarks() < 35) {
				return false;
			}
		}
		 return true;
	}
	
	static Integer getTotalMarks(List<Subject> subjects) {
		Integer total = 0;
		for(Subject subject : subjects) {
			total = total + subject.getMarks();
		}
		 return total;
	}

}
