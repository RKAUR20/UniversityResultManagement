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
import org.springframework.stereotype.Service;

import com.university.rm.model.FileBucket;
import com.university.rm.model.Student;
import com.university.rm.model.Students;
import com.university.rm.service.FileHandlerService;


@Service
public class FileHandlerServiceImpl implements FileHandlerService{

	public List<Student> convertXMLFileToStudents(FileBucket fileBucket) throws IllegalStateException, IOException, JAXBException {
		File fileUpload = new File(fileBucket.getFile().getOriginalFilename());
		fileBucket.getFile().transferTo(fileUpload);
		JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);  
		   
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        Students students= (Students) jaxbUnmarshaller.unmarshal(fileUpload);
        return students.getStudents();
	}
	
	public void createJSONReports(List<Student> students) throws JsonGenerationException, JsonMappingException, IOException {
		
		clearPreviousJSONReports();
		
		for(Student student : students) {
			ObjectMapper ow1 = new ObjectMapper();
			ow1.writeValue(new File(student.getName()+".json"), student);
		}
	}
	
	private void clearPreviousJSONReports() {
		
		File serverDirectory = new File(System.getProperty("user.dir"));

		File[] fList = serverDirectory.listFiles();
		for (File file : fList) {
			if ("json".equals(getFileExtension(file))) {
				System.out.println(file.getName());
				file.delete();
			}
		}
	}
	
	private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
	public File getStudentReport(String studentName) {
		
		File serverDirectory = new File(System.getProperty("user.dir"));

		File[] fList = serverDirectory.listFiles();
		for (File file : fList) {
			if ("json".equals(getFileExtension(file))) {
				if(file.getName().contains(studentName)) {
					return file;
				}
			}
		}
		
		return null;
		
	}

}
