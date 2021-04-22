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
 * Servlet implementation class AddLaClasse
 */
@WebServlet("/AddLaClass")
public class AddLaClasse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String LA_CLASS_PARAM_KEY = "laClassName";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddLaClasse() {
		super();
		// TODO Auto-generated constructor stub
	}

	private boolean pushLaClassToDatabase(HttpServletRequest request) {
		return new LearnersAcademyDaoImp().addLaClass(request.getParameter(LA_CLASS_PARAM_KEY));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			if (SessionValidator.hasSession(request)) {
				
				if (RequestValidator.requestHasParams(request, LA_CLASS_PARAM_KEY)) {

					if (pushLaClassToDatabase(request)) {

						response.getWriter().println(JsAlert.getAlert("Class added", "Dashboard"));

					} else {

						throw new Exception("Problem adding Class");
					}

				} else {

					throw new Exception("Cannot add empty Class name");

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
