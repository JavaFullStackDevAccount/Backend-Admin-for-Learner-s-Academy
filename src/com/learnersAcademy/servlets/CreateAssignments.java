package com.learnersAcademy.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learnersAcademy.dao.LearnersAcademyDaoImp;
import com.learnersAcademy.helpers.JsAlert;
import com.learnersAcademy.helpers.SessionValidator;
//import com.learnersAcademy.populateDb.PopulateDb;

/**
 * Servlet implementation class AssignSubjectsToClass
 */
@WebServlet("/createMapping")
public class CreateAssignments extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final String CLASS_KEY_FOR_REQUEST = "la_classes";

	private final String SUBJECT_KEY_FOR_REQUEST = "la_subjects";

	private final String TEACHERS_KEY_FOR_REQUEST = "la_teachers";

	private final String CLASS_KEY_FOR_FORM_SUBMITTED = "selectedClass";

	private final String SUBJECT_KEY_FOR_FORM_SUBMITTED = "selectedSubject";

	private final String TEACHERS_KEY_FOR_FORM_SUBMITTED = "selectedTeacher";

	public CreateAssignments() {
		super();
	}

	private ArrayList<String> getClassesInAcademy(LearnersAcademyDaoImp learnersAcademyDaoImp) {

		ArrayList<String> classesInAcademyArrayList = learnersAcademyDaoImp.getClassesOfAcademy();

		return (classesInAcademyArrayList == null) ? (new ArrayList<String>()) : (classesInAcademyArrayList);
	}

	private ArrayList<String> getSubjectsInAcademy(LearnersAcademyDaoImp learnersAcademyDaoImp) {

		ArrayList<String> subjectsInAcademyArrayList = learnersAcademyDaoImp.getSubjectsOfAcademy();

		return (subjectsInAcademyArrayList == null) ? (new ArrayList<String>()) : (subjectsInAcademyArrayList);
	}

	private ArrayList<String> getTeachersInAcademy(LearnersAcademyDaoImp learnersAcademyDaoImp) {

		ArrayList<String> teachersInAcademyArrayList = learnersAcademyDaoImp.getTeachersOfAcademy();

		return (teachersInAcademyArrayList == null) ? (new ArrayList<String>()) : (teachersInAcademyArrayList);
	}

	private boolean setRequestAttributes(HttpServletRequest request) {
		try {

			LearnersAcademyDaoImp learnersAcademyDaoImp = new LearnersAcademyDaoImp();

			request.setAttribute(CLASS_KEY_FOR_REQUEST, getClassesInAcademy(learnersAcademyDaoImp));

			request.setAttribute(TEACHERS_KEY_FOR_REQUEST, getTeachersInAcademy(learnersAcademyDaoImp));

			request.setAttribute(SUBJECT_KEY_FOR_REQUEST, getSubjectsInAcademy(learnersAcademyDaoImp));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	private ArrayList<String> extractParametersFromRequest(HttpServletRequest request) {

		ArrayList<String> requestParameters = new ArrayList<String>();

		requestParameters.add(request.getParameter(CLASS_KEY_FOR_FORM_SUBMITTED));

		requestParameters.add(request.getParameter(TEACHERS_KEY_FOR_FORM_SUBMITTED));

		requestParameters.add(request.getParameter(SUBJECT_KEY_FOR_FORM_SUBMITTED));

		return requestParameters;

	}

	private boolean allFieldAreSelected(HttpServletRequest request) {

		for (String params : extractParametersFromRequest(request)) {
			if (params == null)
				return false;
		}
		return true;
	}

	private boolean pushFormDataToDatabase(HttpServletRequest request) {

		try {

			ArrayList<String> formData = extractParametersFromRequest(request);

			System.out.println("Data to push is -> " + formData);

			LearnersAcademyDaoImp learnersAcademyDaoImp = new LearnersAcademyDaoImp();

			return learnersAcademyDaoImp.updateDatabase(formData);

		} catch (Exception e) {

			return false;

		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		try {
			if (SessionValidator.hasSession(request)) {
				
				setRequestAttributes(request);
				
				request.getRequestDispatcher("create_assignments.jsp").forward(request, response);
				
			} else {

				throw new Exception("You need to login first");

			} 
		} catch (Exception e) {
			
			response.getWriter().println(JsAlert.getAlert(e.getMessage(), "Dashboard"));

		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		if (allFieldAreSelected(request)) {

			if (!pushFormDataToDatabase(request)) {
				
				response.getWriter().println(
						JsAlert.getAlert("Invalid assignment of class to subject and teacher", "createMapping"));
				
			} else {
				
				response.getWriter().println(JsAlert.getAlert("Assignment successfull", "Dashboard"));
				
			}

		} else {

			response.getWriter().println(JsAlert.getAlert("All fields are required", "createMapping"));

		}

	}

}
