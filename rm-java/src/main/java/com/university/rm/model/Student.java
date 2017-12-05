package com.university.rm.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Student {

	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	
	@XmlAttribute 
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	@XmlAttribute 
	public void setName(String name) {
		this.name = name;
	}
	
}
