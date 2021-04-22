package com.learnersAcademy.servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learnersAcademy.dao.LearnersAcademyDaoImp;
import com.learnersAcademy.helpers.JsAlert;
import com.learnersAcademy.helpers.SessionValidator;

/**
 * Servlet implementation class ViewClassReport
 */
@WebServlet("/classReport")
public class ViewClassReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String CLASS_ID_KEY = "class_id";

	private final String CLASS_KEY = "laClassName";

	private final String TEACHER_SUBJECT_KEY = "teachersAndSubjects";

	private final String STUDENT_KEY = "studentsOfClass";

	private final String REQUEST_ATTRIBUTE_KEY = "academy_data";

	public ViewClassReport() {
		super();
	}

	private ArrayList<String> getStudentsForClass(LearnersAcademyDaoImp learnersAcademyDaoImp, long classId) {

		return learnersAcademyDaoImp.getStudentsOfAcademyForClass(classId);
	}

	private ArrayList<String> getTeachersAndSubjectsForClass(LearnersAcademyDaoImp learnersAcademyDaoImp,
			long classId) {

		return learnersAcademyDaoImp.getTeachersAndSubjectsOfAcademyForClass(classId);
	}

	private ArrayList<String> getClassesInAcademy(LearnersAcademyDaoImp learnersAcademyDaoImp) {

		return learnersAcademyDaoImp.getClassesOfAcademy();
	}

	private ArrayList<String> extractClassName(ArrayList<String> classesData) {

		ArrayList<String> uniqueClassNames = new ArrayList<String>();

		for (int itr = 1; itr < classesData.size(); itr++) {
			if (itr % 2 != 0) {
				uniqueClassNames.add(classesData.get(itr));
			}
		}

		return uniqueClassNames;

	}

	private ArrayList<Long> extractClassIds(ArrayList<String> classesData) {

		ArrayList<Long> uniqueClassIds = new ArrayList<Long>();

		for (int itr = 0; itr < classesData.size(); itr++) {
			if (itr % 2 == 0) {
				uniqueClassIds.add(Long.parseLong(classesData.get(itr)));
			}
		}

		return uniqueClassIds;

	}

	private ArrayList<HashMap<String, Object>> getClassTeacherSubjectMap(LearnersAcademyDaoImp learnersAcademyDaoImp) {

		ArrayList<HashMap<String, Object>> arrayListOfallClassGroups = new ArrayList<HashMap<String, Object>>();

		ArrayList<String> laClassesArrayList = getClassesInAcademy(learnersAcademyDaoImp);

		ArrayList<String> allClassesNames = extractClassName(laClassesArrayList);

		ArrayList<Long> allClassesIds = extractClassIds(laClassesArrayList);

		for (int itr = 0; itr < allClassesNames.size() && itr < allClassesIds.size(); itr++) {

			long currentLaClassId = allClassesIds.get(itr);

			HashMap<String, Object> classesTeachersSubjectHashMap = new HashMap<String, Object>();

			classesTeachersSubjectHashMap.put(CLASS_ID_KEY, Long.toString(currentLaClassId));

			classesTeachersSubjectHashMap.put(CLASS_KEY, allClassesNames.get(itr));

			classesTeachersSubjectHashMap.put(TEACHER_SUBJECT_KEY,
					getTeachersAndSubjectsForClass(learnersAcademyDaoImp, currentLaClassId));

			classesTeachersSubjectHashMap.put(STUDENT_KEY,
					getStudentsForClass(learnersAcademyDaoImp, currentLaClassId));

			arrayListOfallClassGroups.add(classesTeachersSubjectHashMap);
		}

		return arrayListOfallClassGroups;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		try {

			if (SessionValidator.hasSession(request)) { 
				
				LearnersAcademyDaoImp learnersAcademyDaoImp = new LearnersAcademyDaoImp();
				
				request.setAttribute(REQUEST_ATTRIBUTE_KEY, getClassTeacherSubjectMap(learnersAcademyDaoImp));
				
				request.getRequestDispatcher("view_class_report.jsp").forward(request, response);
				
			} else {

				throw new ServletException("You need to login first");

			}
			

		} 
		
		catch(ServletException se) {
			
			response.getWriter().print(JsAlert.getAlert(se.getMessage(), "Dashboard"));
			
		}
		catch (Exception e) {

			response.getWriter().print(JsAlert.getAlert("Problem fetching data", "Dashboard"));
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
