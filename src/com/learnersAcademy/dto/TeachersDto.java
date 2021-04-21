package com.learnersAcademy.dto;

import java.util.Set;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class TeachersDto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long teachersId;

	@Column
	private String teachersName;
	
	public TeachersDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TeachersDto(long teachersId, String teachersName) {
		super();
		this.teachersId = teachersId;
		this.teachersName = teachersName;
	}


	public long getTeachersId() {
		return teachersId;
	}


	public void setTeachersId(long teachersId) {
		this.teachersId = teachersId;
	}


	public String getTeachersName() {
		return teachersName;
	}


	public void setTeachersName(String teachersName) {
		this.teachersName = teachersName;
	}

	
	
	
}
