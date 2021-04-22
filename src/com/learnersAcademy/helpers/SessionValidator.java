package com.learnersAcademy.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionValidator {

	private static final String SESSION_KEY = "_userName_";

	public static boolean hasSession(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		if (session == null) {

			return false;

		} else {

			if (session.getAttribute(SESSION_KEY) != null) {
				
				return true;
			}
			return false;
		}

	}

}
