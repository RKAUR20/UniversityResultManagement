package com.university.rm.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;


public class Subject {
	
	private String subjectName;
	private Integer marks;

	public Integer getMarks() {
		return marks;
	}
	
	@XmlElement(name = "marks")
	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public String getSubjectName() {
		return subjectName;
	}

	@XmlElement(name = "subjectName")
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	

}
