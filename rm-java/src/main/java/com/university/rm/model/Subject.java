package com.university.rm.model;

import javax.xml.bind.annotation.XmlElement;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUBJECT_MAPPING")
public class Subject {
	
	@Id
	@Column(name="SUBJECT_ID")
	private Integer subjectId;
	
	@Column(name = "SUBJECT_NAME")
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

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	

}
