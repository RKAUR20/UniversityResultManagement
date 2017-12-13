package com.university.rm.test;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.university.rm.customexceptions.JSONReportsGenerationException;
import com.university.rm.customexceptions.ReportNotFoundException;
import com.university.rm.dao.impl.StudentDAOImpl;
import com.university.rm.model.Student;
import com.university.rm.model.Students;
import com.university.rm.model.Subject;
import com.university.rm.service.impl.FileHandlerServiceImpl;
import com.university.rm.service.impl.ResultCalculatorServiceImpl;

public class MainClass {
	
	public static void main(String [] args) throws JAXBException, JSONReportsGenerationException, ReportNotFoundException, JsonParseException, JsonMappingException, IOException {
		
		System.out.println(Runtime.getRuntime().availableProcessors());
		//ApplicationContext context = new ClassPathXmlApplicationContext("hibernate-config.xml");
		File fileUpload = new File("C:\\Users\\rkau23\\Students.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);  
		   
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        Students students= (Students) jaxbUnmarshaller.unmarshal(fileUpload);
        
        
        //ResultCalculatorServiceImpl resultCalculatorService= (ResultCalculatorServiceImpl) context.getBean(ResultCalculatorServiceImpl.class);
        /*ResultCalculatorServiceImpl resultCalculatorService = new ResultCalculatorServiceImpl();
        resultCalculatorService.calculateAndSaveStudentsResult(students.getStudents());*/
        
        /*Collections.sort(students.getStudents(), new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				return o2.getTotalMarks().compareTo(o1.getTotalMarks());
			}
		});*/
        
        students.getStudents().stream().forEach(student -> System.out.println(student.toString()));
        
        /*FileHandlerServiceImpl impl = new FileHandlerServiceImpl();
        impl.createJSONReports(students.getStudents());*/
        
        System.out.println(Instant.now());
        
        /*File file = impl.getStudentReport("Ram");
        
        ObjectMapper objectMapper = new ObjectMapper();

        Student student = objectMapper.readValue(file, Student.class);
        
        System.out.println(students.getStudents().size());*/
        
	}
	

}
