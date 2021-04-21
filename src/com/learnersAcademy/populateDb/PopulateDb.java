package com.learnersAcademy.populateDb;

import java.util.Random;

import com.learnersAcademy.dao.LearnersAcademyDaoImp;

public class PopulateDb {

	private final long[] CLASS_IDS = { 1, 2, 3, 4, 5, 6, 7, 8 };

	LearnersAcademyDaoImp learnersAcademyDaoImp;

	public PopulateDb() {

		this.learnersAcademyDaoImp = new LearnersAcademyDaoImp();

		populateClassesTable();

		populateSubjectTable();
		
		populateTeachersTable();
		
		populateStudentsTable();

	}

	private void populateClassesTable() {

		for (long classId : this.CLASS_IDS) {
			learnersAcademyDaoImp.setInitialClasses("Class " + classId);
		}

	}

	private void populateSubjectTable() {

		for (long classId : this.CLASS_IDS) {
			learnersAcademyDaoImp.setInitialSubjects("Subject " + classId);
		}

	}

	private void populateTeachersTable() {

		int count = 0;
		for (long classId : this.CLASS_IDS) {
			if (count >= 4)
				break;
			learnersAcademyDaoImp.setInitialTeachers("Teacher " + classId);
			count += 1;
		}

	}

	private void populateStudentsTable() {

		for (int i = 0; i < 30 ; i++) {
			learnersAcademyDaoImp.setInitialStudents(getRandomClassId(),"Student " + i);
		}

	}

	private long getRandomClassId() {
		Random random = new Random();
		return this.CLASS_IDS[random.nextInt(this.CLASS_IDS.length)];
	}

}
