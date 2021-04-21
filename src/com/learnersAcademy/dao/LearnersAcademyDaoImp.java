package com.learnersAcademy.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.learnersAcademy.dto.AssignmentsMappingDto;
import com.learnersAcademy.dto.ClassesDto;
import com.learnersAcademy.dto.StudentsDto;
import com.learnersAcademy.dto.SubjectsDto;
import com.learnersAcademy.dto.TeachersDto;
import com.learnersAcademy.helpers.QueryResultToArrayList;

public class LearnersAcademyDaoImp {

	private SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	public LearnersAcademyDaoImp() {
		super();
		try {
			sessionFactory = new Configuration().configure("../../../hibernate.cfg.xml").buildSessionFactory();
		} catch (HibernateException e) {
			System.err.println(e);
		}
	}

	private Session getDatabaseSession() {
		return sessionFactory.openSession();
	}

	private void closeDatabaseSession(Session session) {
		session.close();
	}

	private Transaction getTransaction(Session session) {
		return session.beginTransaction();
	}

	private void rollBackTransaction(Transaction transcation) {
		transcation.rollback();
	}

	private void handleException(Exception e) {
		e.printStackTrace();
	}

	private List<String> executeAndGetResultOfQuery(String query, Object... config) {

		Session session = (Session) config[0];

		Transaction txn = (Transaction) config[1];

		List resultList = session.createSQLQuery(query).list();

		txn.commit();

		return resultList;

	}

	public ArrayList<String> getClassesOfAcademy() {

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		try {

			/*
			 * @Id
			 * 
			 * @GeneratedValue(strategy = GenerationType.AUTO) private long classId;
			 * 
			 * @Column private String className;
			 * 
			 * @OneToMany(mappedBy = "laClasses") private List<SubjectsDto>
			 * subjectsForClass;
			 * 
			 * @ManyToOne
			 * 
			 * @JoinColumn(name = "teachersId") private TeachersDto teacherAssignedToClass;
			 */

			final String QUERY = "select  classId, className  from la_classes";

			return QueryResultToArrayList.extract(executeAndGetResultOfQuery(QUERY, session, txn));

		} catch (Exception e) {

			handleException(e);

		} finally {

			closeDatabaseSession(session);

		}

		return null;
	}

	public ArrayList<String> getSubjectsOfAcademy() {

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		try {

			/*
			 * @Entity
			 * 
			 * @Table(name = "subjects")
			 * 
			 * @Id
			 * 
			 * @GeneratedValue(strategy = GenerationType.AUTO) private long subjectId;
			 * 
			 * @Column private String subjectName;
			 * 
			 * @ManyToOne
			 * 
			 * @JoinColumn(name = "classId") private ClassesDto laClasses;
			 * 
			 * @ManyToOne
			 * 
			 * @JoinColumn(name = "teachersId") private TeachersDto
			 * teacherAssignedToSubject;
			 */

			final String QUERY = "select subjectId, subjectName   from subjects";

			return QueryResultToArrayList.extract(executeAndGetResultOfQuery(QUERY, session, txn));

		} catch (Exception e) {

			handleException(e);

		} finally {

			closeDatabaseSession(session);

		}

		return null;
	}

	public ArrayList<String> getTeachersOfAcademy() {

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		try {

			/*
			 * @Entity
			 * 
			 * @Table(name = "teachers")
			 * 
			 * @Id
			 * 
			 * @GeneratedValue(strategy = GenerationType.AUTO) private long teachersId;
			 * 
			 * @Column private String teachersName;
			 * 
			 * @OneToOne
			 * 
			 * @JoinColumn(name = "classId") private ClassesDto laClasses;
			 * 
			 * @OneToMany(mappedBy = "teacherAssignedToClass") private List<ClassesDto>
			 * classesAssignedToTeacher;
			 * 
			 * @OneToMany(mappedBy = "teacherAssignedToSubject") private List<SubjectsDto>
			 * subjectsAssignedToTeacher;
			 */

			final String QUERY = "select teachersId, teachersName   from teachers";

			return QueryResultToArrayList.extract(executeAndGetResultOfQuery(QUERY, session, txn));

		} catch (Exception e) {

			handleException(e);

		} finally {

			closeDatabaseSession(session);

		}

		return null;
	}

	private boolean isRecordAlreadyPresent(ArrayList<String> arrayListOfDataToBeUpdated) {

		long laClassId = Long.parseLong(arrayListOfDataToBeUpdated.get(0));

		long teacherId = Long.parseLong(arrayListOfDataToBeUpdated.get(1));

		long subjectId = Long.parseLong(arrayListOfDataToBeUpdated.get(2));

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		final String QUERY = "select *  from class_teachers_subjects_mapping where laClassId = " + laClassId
				+ " and laSubjectId = " + subjectId + " and laTeacherId = " + teacherId;

		ArrayList<String> result = QueryResultToArrayList.extract(executeAndGetResultOfQuery(QUERY, session, txn));

		return !(result.size() == 0 || result == null);

	}

	private boolean isSubjectAlreadyAllocatedToTeacherInClass(ArrayList<String> arrayListOfDataToBeUpdated) {

		long laClassId = Long.parseLong(arrayListOfDataToBeUpdated.get(0));

		long teacherId = Long.parseLong(arrayListOfDataToBeUpdated.get(1));

		long subjectId = Long.parseLong(arrayListOfDataToBeUpdated.get(2));

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		final String QUERY = "select *  from class_teachers_subjects_mapping where laClassId = " + laClassId
				+ " and laTeacherId = " + teacherId;

		ArrayList<String> result = QueryResultToArrayList.extract(executeAndGetResultOfQuery(QUERY, session, txn));

		return !(result.size() == 0 || result == null);

	}

	private boolean isSubjectAlreadyTakenForTheClass(ArrayList<String> arrayListOfDataToBeUpdated) {

		long laClassId = Long.parseLong(arrayListOfDataToBeUpdated.get(0));

		long teacherId = Long.parseLong(arrayListOfDataToBeUpdated.get(1));

		long subjectId = Long.parseLong(arrayListOfDataToBeUpdated.get(2));

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		final String QUERY = "select *  from class_teachers_subjects_mapping where laClassId = " + laClassId
				+ " and laSubjectId = " + subjectId;

		ArrayList<String> result = QueryResultToArrayList.extract(executeAndGetResultOfQuery(QUERY, session, txn));

		return !(result.size() == 0 || result == null);

	}

	public boolean updateDatabase(ArrayList<String> arrayListOfDataToBeUpdated) {

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		try {

			/*
			 * class, teacher, subject,
			 */

			long laClassId = Long.parseLong(arrayListOfDataToBeUpdated.get(0));

			long teacherId = Long.parseLong(arrayListOfDataToBeUpdated.get(1));

			long subjectId = Long.parseLong(arrayListOfDataToBeUpdated.get(2));

			// (long mappingId, long laClassId, long laTeacherId, long laSubjectId

			if (isRecordAlreadyPresent(arrayListOfDataToBeUpdated)
					|| isSubjectAlreadyAllocatedToTeacherInClass(arrayListOfDataToBeUpdated)
					|| isSubjectAlreadyTakenForTheClass(arrayListOfDataToBeUpdated)) {

				System.out.println("Allocation Record Present");

				return false;
			}

			AssignmentsMappingDto relationDto = new AssignmentsMappingDto(0, laClassId, teacherId, subjectId);

			session.save(relationDto);

			txn.commit();

			closeDatabaseSession(session);

			System.out.println("Done updating");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			rollBackTransaction(txn);

			closeDatabaseSession(session);

		}
		return false;
	}

	public boolean addTeacher(String teacherName) {

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		try {

			TeachersDto teacherDto = new TeachersDto();
			
			teacherDto.setTeachersName(teacherName);

			session.save(teacherDto);

			txn.commit();

			closeDatabaseSession(session);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			rollBackTransaction(txn);

			closeDatabaseSession(session);

		}
		return false;
	}

	public boolean addSubject(String subjectName) {

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		try {

			SubjectsDto subjectDto = new SubjectsDto(0, subjectName);

			session.save(subjectDto);

			txn.commit();

			closeDatabaseSession(session);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			rollBackTransaction(txn);

			closeDatabaseSession(session);

		}
		return false;
	}

	public boolean addLaClass(String laClassName) {

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		try {

			ClassesDto laClassDto = new ClassesDto(0, laClassName);

			session.save(laClassDto);

			txn.commit();

			closeDatabaseSession(session);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			rollBackTransaction(txn);

			closeDatabaseSession(session);

		}
		return false;
	}

	public boolean addStudent(String studentName, long classId) {

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		try {
			
			ClassesDto laClassDto = (ClassesDto) session.load(ClassesDto.class, classId);
			
			StudentsDto studentDto = new StudentsDto();
			
			studentDto.setStudentsName(studentName);
			
			studentDto.setClassId(laClassDto);

			session.save(studentDto);

			txn.commit();

			closeDatabaseSession(session);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			rollBackTransaction(txn);

			closeDatabaseSession(session);

		}
		return false;
	}

	
	/* This on only used to populate database at the start */

	public void setInitialClasses(String className) {

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		try {

			ClassesDto classDto = new ClassesDto();

			classDto.setClassName(className);

			session.save(classDto);

			txn.commit();

		} catch (Exception e) {

			handleException(e);

		} finally {

			closeDatabaseSession(session);

		}

	}

	public void setInitialSubjects(String subjectName) {

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		try {

			SubjectsDto subjectsDto = new SubjectsDto();

			subjectsDto.setSubjectName(subjectName);

			session.save(subjectsDto);

			txn.commit();

		} catch (Exception e) {

			handleException(e);

		} finally {

			closeDatabaseSession(session);

		}

	}

	public void setInitialTeachers(String teacherName) {

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		try {

			TeachersDto teacherDto = new TeachersDto();

			teacherDto.setTeachersName(teacherName);

			session.save(teacherDto);

			txn.commit();

		} catch (Exception e) {

			handleException(e);

		} finally {

			closeDatabaseSession(session);

		}

	}

	public void setInitialStudents(long classId, String studentName) {

		Session session = getDatabaseSession();

		Transaction txn = getTransaction(session);

		try {

			StudentsDto studentDto = new StudentsDto();

			studentDto.setStudentsName(studentName);

			ClassesDto classesDto = new ClassesDto();

			classesDto.setClassId(classId);

			studentDto.setClassId(classesDto);

			session.save(studentDto);

			System.out.println("Saved student");

			txn.commit();

		} catch (Exception e) {

			handleException(e);

		} finally {

			closeDatabaseSession(session);

		}

	}

}
