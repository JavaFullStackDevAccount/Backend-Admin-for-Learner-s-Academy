package com.learnersAcademy.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.learnersAcademy.helpers.JsAlert;
import com.learnersAcademy.helpers.RequestValidator;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ADMIN_EMAIL = "admin@admin.com";

	private static final String ADMIN_PASSWORD = "@dm!n123";

	private static final String ADMIN_EMAIL_INPUT_NAME = "userEmail";

	private static final String ADMIN_PASSWORD_INPUT_NAME = "userPassword";

	private static final String SESSION_KEY = "_userName_";

	public Login() {
		super();

	}

	private boolean credentialsAreCorrect(HttpServletRequest request) {

		String emailInRequest = request.getParameter(ADMIN_EMAIL_INPUT_NAME);

		String passwordInRequest = request.getParameter(ADMIN_PASSWORD_INPUT_NAME);

		if (emailInRequest == null || passwordInRequest == null)
			return false;

		return (emailInRequest.equals(ADMIN_EMAIL) && passwordInRequest.equals(ADMIN_PASSWORD));
	}

	private void createUserSession(HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (session != null) {
			session.invalidate();
		}

		request.getSession(true).setAttribute(SESSION_KEY, request.getParameter(ADMIN_EMAIL_INPUT_NAME));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			if (RequestValidator.requestHasParams(request, ADMIN_EMAIL_INPUT_NAME, ADMIN_PASSWORD_INPUT_NAME)) {

				if (credentialsAreCorrect(request)) {
					createUserSession(request);

					response.sendRedirect("Dashboard");

				} else {

					throw new Exception("Wrong username or password");

				}

			} else {

				throw new Exception("All fields are required");

			}

		} catch (Exception e) {

			response.getWriter().println(JsAlert.getAlert(e.getMessage(), "http://localhost:8080/LearnersAcademy/"));
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
