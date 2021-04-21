package com.learnersAcademy.dto;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "class_teachers_subjects_mapping")
public class AssignmentsMappingDto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long mappingId;

	@Column
	private long laClassId;

	@Column
	private long laTeacherId;

	@Column
	private long laSubjectId;

	public AssignmentsMappingDto(long mappingId, long laClassId, long laTeacherId, long laSubjectId) {
		super();
		this.mappingId = mappingId;
		this.laClassId = laClassId;
		this.laTeacherId = laTeacherId;
		this.laSubjectId = laSubjectId;
	}

	public AssignmentsMappingDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getMappingId() {
		return mappingId;
	}

	public void setMappingId(long mappingId) {
		this.mappingId = mappingId;
	}

	public long getLaClassId() {
		return laClassId;
	}

	public void setLaClassId(long laClassId) {
		this.laClassId = laClassId;
	}

	public long getLaTeacherId() {
		return laTeacherId;
	}

	public void setLaTeacherId(long laTeacherId) {
		this.laTeacherId = laTeacherId;
	}

	public long getLaSubjectId() {
		return laSubjectId;
	}

	public void setLaSubjectId(long laSubjectId) {
		this.laSubjectId = laSubjectId;
	}

}
