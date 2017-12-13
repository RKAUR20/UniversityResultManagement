package com.university.rm.model;

import javax.xml.bind.annotation.XmlElement;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT_SUBJECT")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return this.subjectName.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return this.subjectName.equals(((Subject) o).getSubjectName());
	}

}
