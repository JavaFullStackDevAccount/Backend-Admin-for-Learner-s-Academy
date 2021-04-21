package com.learnersAcademy.helpers;

import javax.servlet.http.HttpServletRequest;

public class RequestValidator {

	public static boolean requestHasParams(HttpServletRequest request, String... paramsToFind) {

		for (String param : paramsToFind) {
			if (request.getParameter(param) == null)
				return false;
			else if (request.getParameter(param) != null) {
				if (request.getParameter(param).toUpperCase().equals("NULL")
						|| request.getParameter(param).toUpperCase().isBlank()
						|| request.getParameter(param).toUpperCase().isEmpty())
					return false;

			}
		}
		return true;

	}
}
