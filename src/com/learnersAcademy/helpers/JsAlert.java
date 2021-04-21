package com.learnersAcademy.helpers;

public class JsAlert {

	
	public static String getAlert(String message, String redirectTo) {
		
		StringBuilder alert = new StringBuilder();
		
		alert.append("<script>");
		
		alert.append("alert('"+message+"');");
		
		alert.append("location.href='"+redirectTo+"' ;");
		
		alert.append("</script>");
		
		return alert.toString();
	}
}
