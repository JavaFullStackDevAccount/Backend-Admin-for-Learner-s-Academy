package com.learnersAcademy.dto;

import java.util.Set;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")

public class SubjectsDto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long subjectId;

	@Column
	private String subjectName;

	public SubjectsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubjectsDto(long subjectId, String subjectName) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
