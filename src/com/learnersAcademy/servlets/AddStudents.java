package com.learnersAcademy.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learnersAcademy.dao.LearnersAcademyDaoImp;
import com.learnersAcademy.helpers.JsAlert;
import com.learnersAcademy.helpers.RequestValidator;
import com.learnersAcademy.helpers.SessionValidator;

/**
 * Servlet implementation class AddStudents
 */
@WebServlet("/AddStudents")
public class AddStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String STUDENT_PARAM_KEY = "studentName";

	private final String CLASS_PARAM_KEY = "selectedClass";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddStudents() {
		super();
		// TODO Auto-generated constructor stub
	}

	private boolean pushStudentToDatabase(HttpServletRequest request) {
		return new LearnersAcademyDaoImp().addStudent(request.getParameter(STUDENT_PARAM_KEY),
				Long.parseLong(request.getParameter(CLASS_PARAM_KEY)));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			
			if (SessionValidator.hasSession(request)) {
				
				if (RequestValidator.requestHasParams(request, STUDENT_PARAM_KEY, CLASS_PARAM_KEY)) {

					if (pushStudentToDatabase(request)) {

						response.getWriter().println(JsAlert.getAlert("Student added", "Dashboard"));

					} else {
						
						System.out.println("Error adding Student");

						throw new Exception("Error adding Student");
					}

				} else {
					
					System.out.println("Empty field found");

					throw new Exception("Cannot add empty Student name");

				} 
			} else {
				
				throw new ServletException("You need to login first");
				
			}
			
			
			
			
		}
		catch (ServletException e) {
			
			response.getWriter().println(JsAlert.getAlert(e.getMessage(), "Dashboard"));

		}
		
		
		catch (Exception e) {
			
			System.out.println(e.getLocalizedMessage());
			
			System.out.println(e.getCause());
			
			response.getWriter().println(JsAlert.getAlert(e.getMessage(), "Dashboard"));

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
