package com.university.rm.controller;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.university.rm.model.Student;

public class MainTest {
	
	public static void main(String [] args) throws JAXBException {
        File file = new File("D:\\Students.xml");
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);  
		   
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        Student que= (Student) jaxbUnmarshaller.unmarshal(file);
        System.out.println(que);
	}

}
