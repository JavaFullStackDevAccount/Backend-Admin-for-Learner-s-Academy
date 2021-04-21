package com.learnersAcademy.dto;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "la_classes")
public class ClassesDto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long classId;

	@Column
	private String className;

	public ClassesDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassesDto(long classId, String className) {
		super();
		this.classId = classId;
		this.className = className;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
