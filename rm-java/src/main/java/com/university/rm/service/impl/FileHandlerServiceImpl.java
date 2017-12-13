package com.university.rm.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
	
	@Override
	public List<Student> convertXMLFileToStudents(FileBucket fileBucket) {
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
	
	@Override
	public void createJSONReports(List<Student> students) {

		ExecutorService executor = Executors.newFixedThreadPool(5);

		logger.debug("Going to generate JSON reports for uploaded XML student data");

		students.stream().forEach(student -> executor.submit(() -> {
			ObjectMapper ow1 = new ObjectMapper();
			try {
				ow1.writeValue(new File(student.getName() + "_" + student.getId() + ".json"), student);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new JSONReportsGenerationException(e);
			}
		}));
		
		executor.shutdown();

		try {
			executor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("Generation of JSON reports for uploaded XML data completed");
	}
	
	@Override
	public void clearPreviousJSONReports() {
		
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
	
	@Override
	public File getStudentReport(String studentName) {
		
		File serverDirectory = new File(System.getProperty("user.dir"));

		File[] fList = serverDirectory.listFiles();
		
		for (File file : fList) {
			if ("json".equals(getFileExtension(file))) {
				if(file.getName().toLowerCase().contains(studentName.toLowerCase())) {
					return file;
				}
			}
		}
		
		throw new ReportNotFoundException(studentName); 
		
	}

}
