package com.learnersAcademy.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.learnersAcademy.dao.LearnersAcademyDaoImp;
import com.learnersAcademy.populateDb.PopulateDb;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void populateDatabaseAndSetSessionAttrib(HttpServletRequest request) {
		/*HttpSession session = request.getSession(false);
		if (session.getAttribute("dbAlreadyPopulated") == null) {
			new PopulateDb();
			System.out.println("Database populated");
			session.setAttribute("dbAlreadyPopulated", true);
		}*/

	}

	private ArrayList<String> getClassesInAcademy() {
		
		LearnersAcademyDaoImp learnersAcademyDaoImp = new LearnersAcademyDaoImp();

		ArrayList<String> classesInAcademyArrayList = learnersAcademyDaoImp.getClassesOfAcademy();

		return (classesInAcademyArrayList == null) ? (new ArrayList<String>()) : (classesInAcademyArrayList);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		populateDatabaseAndSetSessionAttrib(request);

		
		request.setAttribute("la_classes", getClassesInAcademy());
		

		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
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
