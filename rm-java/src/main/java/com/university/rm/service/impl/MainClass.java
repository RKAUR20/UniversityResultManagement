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
				
		/* File fileUpload = new File("C:\\Users\\rkau23\\students.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);  
		   
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        Students students= (Students) jaxbUnmarshaller.unmarshal(fileUpload);
        
        ResultCalculatorServiceImpl resultCalculatorService= new ResultCalculatorServiceImpl();
        resultCalculatorService.calculateStudentsResult(students.getStudents());
        
        FileHandlerServiceImpl impl = new FileHandlerServiceImpl();
        impl.createJSONReports(students.getStudents());
        
        File file = impl.getStudentReport("Ram");
        
        ObjectMapper objectMapper = new ObjectMapper();

        Student student = objectMapper.readValue(file, Student.class);
        
        System.out.println(student.getName());*/
        
	}
	

}
