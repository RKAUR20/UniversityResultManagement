package com.university.rm.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@Entity
@Table(name="Student")
public class Student {

	@Id
	@Column(name ="student_id")
	private Integer id;
	private String name;
	private String course;
	
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
		this.status = Status.PASS;
		this.getSubjects().stream().forEach(subject -> {
			if(subject.getMarks() < 35) {
				this.status = Status.FAIL;
				this.rank = null;
				return;
			}
		});
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks() {
		this.totalMarks = this.getSubjects().stream().mapToInt(sub -> sub.getMarks()).sum();
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "id : " + id + " name : " + name + " course : " + course + " status : " + status + " totalMarks : " + totalMarks + " rank : " + rank;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}
