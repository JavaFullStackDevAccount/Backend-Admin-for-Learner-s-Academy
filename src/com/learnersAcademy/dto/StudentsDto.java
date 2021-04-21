package com.learnersAcademy.dto;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class StudentsDto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long studentsId;

	@Column
	private String studentsName;

	@OneToOne
	@JoinColumn(name = "classId")
	private ClassesDto classId;

	public StudentsDto(long studentsId, String studentsName, ClassesDto classId) {
		super();
		this.studentsId = studentsId;
		this.studentsName = studentsName;
		this.classId = classId;
	}

	public StudentsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getStudentsId() {
		return studentsId;
	}

	public void setStudentsId(long studentsId) {
		this.studentsId = studentsId;
	}

	public String getStudentsName() {
		return studentsName;
	}

	public void setStudentsName(String studentsName) {
		this.studentsName = studentsName;
	}

	public ClassesDto getClassId() {
		return classId;
	}

	public void setClassId(ClassesDto classId) {
		this.classId = classId;
	}

}
