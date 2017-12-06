package com.university.rm.service;

import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.university.rm.model.FileBucket;
import com.university.rm.model.Student;


public interface FileHandlerService {
	
	public List<Student> convertXMLFileToStudents(FileBucket fileBucket) throws IllegalStateException, IOException, JAXBException;
	
	public void createJSONReports(List<Student> students) throws JsonGenerationException, JsonMappingException, IOException;

}
