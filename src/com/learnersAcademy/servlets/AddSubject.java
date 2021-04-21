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

/**
 * Servlet implementation class AddSubject
 */
@WebServlet("/AddSubject")
public class AddSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String SUBJECT_PARAM_KEY = "subjectName";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSubject() {
		super();
		// TODO Auto-generated constructor stub
	}

	private boolean pushSubjectToDatabase(HttpServletRequest request) {
		return new LearnersAcademyDaoImp().addSubject(request.getParameter(SUBJECT_PARAM_KEY));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			if (RequestValidator.requestHasParams(request, SUBJECT_PARAM_KEY)) {

				if (pushSubjectToDatabase(request)) {

					response.getWriter().println(JsAlert.getAlert("Subject added", "Dashboard"));

				} else {

					throw new Exception("Error adding Subject");
				}

			} else {

				throw new Exception("Cannot add empty Subject name");

			}
		} catch (Exception e) {

			response.getWriter().println(JsAlert.getAlert("Unable to add Subject", "Dashboard"));

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
