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
 * Servlet implementation class AddTeacher
 */
@WebServlet("/AddTeacher")
public class AddTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String TEACHER_PARAM_KEY = "teacherName";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTeacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	private boolean pushTeachersInfoToDatabase(HttpServletRequest request) {
		
		return new LearnersAcademyDaoImp().addTeacher(request.getParameter(TEACHER_PARAM_KEY));
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			if (SessionValidator.hasSession(request)) {
				
				if (RequestValidator.requestHasParams(request, TEACHER_PARAM_KEY)) {

					if (pushTeachersInfoToDatabase(request)) {

						response.getWriter().println(JsAlert.getAlert("Teacher added", "Dashboard"));

					} else {

						throw new Exception("Error adding teacher");
					}

				} else {

					throw new Exception("Cannot add empty teacher name");

				} 
			} else {

				throw new Exception("You need to login first");

			}
			
		} catch (Exception e) {

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
