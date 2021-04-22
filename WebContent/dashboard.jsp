<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">

<style>
.bg-purple {
	background-color: #E040FB;
}

.bg-purple:hover, .bg-purple:focus, .bg-purple:active {
	background-color: #D500F9;
}

.bg-accent {
	background-color: #00796B;
}

.bg-accent:hover, .bg-accent:active, .bg-accent:focus {
	background-color: #00695C;
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-primary">
		<a class="navbar-brand text-white" href="Dashboard">Learning
			Academy</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link text-white"
					href="logout">Logout <span class="sr-only">(current)</span></a></li>
			</ul>
		</div>
	</nav>

	<%!private final String CLASS_KEY_FOR_REQUEST = "la_classes";%>

	<%!private ArrayList<String> removeNaFromArrayList(ArrayList<String> arrayListWithNaIncluded) {

	ArrayList<String> cleanedArrayList = new ArrayList<String>();

	for (String element : arrayListWithNaIncluded) {
		if (!element.equals("Na"))
			cleanedArrayList.add(element);
	}

	return cleanedArrayList;
}%>

	<%!private ArrayList<String> getClassesFromRequest(HttpServletRequest request) {

	ArrayList<String> classesInAcademy = removeNaFromArrayList(
			(ArrayList<String>) request.getAttribute(CLASS_KEY_FOR_REQUEST));

	return classesInAcademy;

}%>

	<%!private String createMenuOptions(String optionName, ArrayList<String> optionValuesAndLabel) {

	StringBuilder menuOptions = new StringBuilder();

	Iterator itr = optionValuesAndLabel.iterator();

	for (; itr.hasNext();) {
		menuOptions.append("<option value='" + itr.next() + "'>" + itr.next() + "</option>\n");
	}

	return menuOptions.toString();

}%>


	<%!private String makeModal(String title, String body, String formAction, String modalId) {

	StringBuilder modal = new StringBuilder();

	modal.append("<div class='modal fade' id='" + modalId
			+ "' data-backdrop='static'><div class='modal-dialog'><div class='modal-content'><form method='post' action='"
			+ formAction + "' ><div class='modal-header'>");

	modal.append("<h5 class='modal-title' id='staticBackdropLabel'>" + title + "</h5>");

	modal.append(
			"<button type='button' class='close' data-dismiss='modal'aria-label='Close'><span aria-hidden='true'>&times;</span></button></div><div class='modal-body'>");

	modal.append(body);

	modal.append(
			"</div><div class='modal-footer'><button type='button' class='btn btn-secondary' data-dismiss='modal'>Close</button><button type='submit' class='btn btn-primary'>Done</button></div></form></div></div></div></div>");

	return modal.toString();
}%>

	<%!private String makeInputField(String inputFieldName, String inputFieldLabel) {

	StringBuilder inputField = new StringBuilder();

	inputField.append("<div class='input-group mb-4 w-100 '><div class='input-group-prepend '>");

	inputField.append("<span class='input-group-text bg-primary text-white'>" + inputFieldLabel + "</span>");

	inputField.append("</div>");

	inputField.append("<input type='text' class='form-control' placeholder='Add " + inputFieldLabel + "' name='"
			+ inputFieldName + "' required />	</div>");

	return inputField.toString();

}%>

	<div class="container pt-5 text-center h-100 ">


		<div class="row">
			<div class="col-5 mb-3 mx-auto">
				<a href="#"
					class="btn btn-success text-light bg-primary btn-block py-5 border-0"
					data-toggle="modal" data-target="#studentModal"><span
					class="h3">Add students</span></a>
			</div>

			<div class="col-5 mb-5 mx-auto">
				<a href="#"
					class="btn btn-success text-light bg-info btn-block py-5 border-0"
					data-toggle="modal" data-target="#addteacherModal"><span
					class="h3">Add teachers</span></a>


			</div>

			<div class="col-5 mb-3 mx-auto">
				<a href="#"
					class="btn btn-success text-light bg-secondary btn-block py-5 border-0"
					data-toggle="modal" data-target="#laClassNameModal"><span
					class="h3">Add Classes</span></a>
			</div>

			<div class="col-5 mb-3 mx-auto">
				<a href="#"
					class="btn btn-success text-light bg-warning btn-block py-5 border-0"
					data-toggle="modal" data-target="#subjectModal"><span
					class="h3">Add Subjects</span></a>
			</div>
		</div>


		<div class="row mt-4">
			<div class="col-11 mb-5 mx-auto">
				<a href="createMapping"
					class="btn btn-success text-light btn-block py-5 border-0 bg-purple"><span
					class="h3">Create Assignments</span></a>

			</div>

			<div class="col-11 mb-3 mx-auto">
				<a href="classReport"
					class="btn btn-success text-light bg-accent btn-block py-5 border-0"><span
					class="h3">View class report</span></a>

			</div>
		</div>


		<%
		out.println(makeModal("Add teachers", makeInputField("teacherName", "Teacher name"), "AddTeacher", "addteacherModal"));

		out.println(makeModal("Add class", makeInputField("laClassName", "Class name"), "AddLaClass", "laClassNameModal"));

		out.println(makeModal("Add subject", makeInputField("subjectName", "Subject"), "AddSubject", "subjectModal"));
		%>

		<%
		String studentNameField = makeInputField("studentName", "Student name");

		StringBuilder classOptions = new StringBuilder();

		classOptions.append(
				"<div class='input-group mb-4 w-100 '><div class='input-group-prepend '><span class='input-group-text bg-primary text-white'>Class</span></div>");

		classOptions.append("<select class='custom-select' name='selectedClass' required>");

		classOptions.append("<option selected>Select class</option>");

		classOptions.append(createMenuOptions("selectedClass", getClassesFromRequest(request)));

		classOptions.append("</select>");

		classOptions.append("</div>");

		out.println(makeModal("Add student", studentNameField + classOptions, "AddStudents", "studentModal"));
		%>

















		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
			integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
			crossorigin="anonymous"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
			integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
			crossorigin="anonymous"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
			integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
			crossorigin="anonymous"></script>
</body>
</html>