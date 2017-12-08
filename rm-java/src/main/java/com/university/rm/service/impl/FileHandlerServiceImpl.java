package com.university.rm.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
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

	final Logger logger = Logger.getLogger(FileHandlerServiceImpl.class);
	
	public List<Student> convertXMLFileToStudents(FileBucket fileBucket) throws InputFileUnmarshalException{
		logger.debug("UnMarshalling uploaded XML file to Students Object started.");
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
		logger.debug("UnMarshalling uploaded XML file to Students Object completed.");
		return students.getStudents();  
	}
	
	public void createJSONReports(List<Student> students){
		
		logger.debug("Going to delete existing JSON reports.");
		
		clearPreviousJSONReports();
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		logger.debug("Going to generate JSON reports for uploaded XML student data");
		for(Student student : students) {
			executor.submit(() -> {
				ObjectMapper ow1 = new ObjectMapper();
				try {
					ow1.writeValue(new File(student.getName()+".json"), student);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//throw new JSONReportsGenerationException(e);
				}
			});
		}
		logger.debug("Generation of JSON reports for uploaded XML data completed");
	}
	
	private void clearPreviousJSONReports() {
		
		File serverDirectory = new File(System.getProperty("user.dir"));

		File[] fList = serverDirectory.listFiles();
		for (File file : fList) {
			if ("json".equals(getFileExtension(file))) {
				file.delete();
			}
		}
		
		logger.debug("Existing JSON reports deleted.");
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
