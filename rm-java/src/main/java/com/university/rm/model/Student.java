package com.university.rm.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Student {

	private Integer id;
	private String name;
	
	private List<Subject> subjects;
	private Status status;
	private Integer totalMarks;
	
	public List<Subject> getSubjects() {
		return subjects;
	}

	@XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Integer getId() {
		return id;
	}
	
	@XmlElement(name = "id")
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	@XmlElement(name = "name") 
	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus() {
		for(Subject subject : subjects) {
			if(subject.getMarks() < 35) {
				this.status = Status.FAIL;
				return;
			}
		}
		this.status = Status.PASS;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks() {
		Integer totalMarks = 0;
		for(Subject subject : this.subjects) {
			totalMarks = totalMarks + subject.getMarks();
		}
		this.totalMarks = totalMarks;
	}
	
}
