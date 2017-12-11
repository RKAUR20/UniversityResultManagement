package com.university.rm.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.university.rm.model.Student;
import com.university.rm.model.Students;
import com.university.rm.model.Subject;

public class XMLMarshaller {
	
	public static void main(String [] args) throws JAXBException, FileNotFoundException {
		Students students = new Students();
		Random rand = new Random();
		students.setStudents(new ArrayList<>());
		for(int i=0 ; i < 5; i++) {
			Student s = new Student();
			s.setId(i+100);
			s.setName("Student "+i);
			
			List<Subject> subjects = new ArrayList<>();
			
			for(int j=0; j<5; j++) {
				Subject sub = new Subject();
				sub.setSubjectId(j);
				//sub.setSubjectName("Subject " + j);
				sub.setMarks(rand.nextInt(100) + 30);
				subjects.add(sub);
			}
			s.setSubjects(subjects);
			students.getStudents().add(s);
		}
		
		System.out.println(students.getStudents().size());
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.marshal(students, new FileOutputStream("C:\\Users\\rkau23\\Students.xml"));  
		
	}

}
