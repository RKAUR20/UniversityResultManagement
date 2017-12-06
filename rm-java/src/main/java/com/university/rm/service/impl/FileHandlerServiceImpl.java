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

import com.university.rm.customexceptions.InputFileUnmarshalException;
import com.university.rm.customexceptions.JSONReportsGenerationException;
import com.university.rm.customexceptions.ReportNotFoundException;
import com.university.rm.model.FileBucket;
import com.university.rm.model.Student;
import com.university.rm.model.Students;
import com.university.rm.service.FileHandlerService;


@Service
public class FileHandlerServiceImpl implements FileHandlerService{

	public List<Student> convertXMLFileToStudents(FileBucket fileBucket) throws InputFileUnmarshalException{
		File fileUpload = new File(fileBucket.getFile().getOriginalFilename());
		Students students = null;
		try {
			fileBucket.getFile().transferTo(fileUpload);
			JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
	        students = (Students) jaxbUnmarshaller.unmarshal(fileUpload);
		} catch (IllegalStateException | IOException | JAXBException ex) {
			// TODO Auto-generated catch block
			throw new InputFileUnmarshalException(ex);
		}  
		   
		return students.getStudents();
	}
	
	public void createJSONReports(List<Student> students) throws JSONReportsGenerationException{
		
		clearPreviousJSONReports();
		
		for(Student student : students) {
			ObjectMapper ow1 = new ObjectMapper();
			try {
				ow1.writeValue(new File(student.getName()+".json"), student);
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				throw new JSONReportsGenerationException(ex);
			}
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
	
	public File getStudentReport(String studentName) throws ReportNotFoundException {
		
		File serverDirectory = new File(System.getProperty("user.dir"));

		File[] fList = serverDirectory.listFiles();
		for (File file : fList) {
			if ("json".equals(getFileExtension(file))) {
				if(file.getName().contains(studentName)) {
					return file;
				}
			}
		}
		
		throw new ReportNotFoundException(studentName); 
		
	}

}
