package com.university.rm.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlElementWrapper;

@Entity
@Table(name="Student")
public class Student {

	@Id
	@Column(name ="student_id")
	private Integer id;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Set<Subject> subjects;
	
	@Transient
	private Status status;
	
	@Transient
	private Integer totalMarks;
	
	@Transient
	private Integer rank = 1;
	
	public Set<Subject> getSubjects() {
		return subjects;
	}

	@XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
	public void setSubjects(Set<Subject> subjects) {
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
}
