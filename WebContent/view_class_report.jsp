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

	<%!private String makeClassGrid(String... columns) {

		StringBuilder classGrid = new StringBuilder();

		classGrid.append("<li class='list-group-item mb-5 mt-5 border-0'><div class='row'>");

		for (String itr : columns) {

			classGrid.append("<div class='col-4 '>");
			classGrid.append("<div class='card'><div class='card-header'>");
			classGrid.append(itr);
			classGrid.append("</div></div></div>");
		}

		classGrid.append("</div></li>");

		return classGrid.toString();

	}%>

	<%!private String makeClassCardTitle(String laClassName) {

		return "<span class='h3 text-muted'>" + laClassName + "</span>";

	}%>

	<%!private String getCurrentTime() {
		return new Date().getTime() + "";
	}%>

	<%!private String makeCollapseElement(ArrayList<String> dataForCollapse, String collapseId, String accordianId,
			boolean isAccordianForStudents) {

		StringBuilder collapse = new StringBuilder();

		collapse.append("<div id='" + collapseId + "' class='collapse show' ");

		collapse.append("data-parent='#" + accordianId + "'><div class='card-body'>");

		if (isAccordianForStudents) {

			collapse.append("<ol>");

			for (String student : dataForCollapse) {
				collapse.append("<li>");
				collapse.append(student);
				collapse.append("</li>");
			}

			collapse.append("</ol>");
		} else {

			/**
			<table class="table table-striped">
											<tr>
												<th>Subject</th>
			
												<th>Assigned to</th>
											</tr>
			
											<tr>
												<td>Subject 1</td>
			
												<td>Teacher 1
											</tr>
			
			
											<tr>
												<td>Subject 1</td>
			
												<td>Teacher 1</td>
											</tr>
										</table>
			*/

			collapse.append("<table class='table table-striped'>");

			collapse.append("<thead><tr><th>Teacher</th><th>Subject assigned</th></tr></thead>");

			collapse.append("<tbody>");

			Iterator teachersAndSubjects = dataForCollapse.iterator();

			while (teachersAndSubjects.hasNext()) {

				String teacherName = (String) teachersAndSubjects.next();

				if (teachersAndSubjects.hasNext())
					collapse.append("<tr><td>" + teacherName + "</td><td>" + teachersAndSubjects.next() + "</td></tr>");
			}
			collapse.append("</tbody>");
			collapse.append("</table>");
		}

		collapse.append("</div>");

		return collapse.toString();

	}%>

	<%!private String makeAccordian(String classId, String laClassName, String accordianTitle,
			boolean isAccordianForStudents, ArrayList<String> dataforCollapse) {

		StringBuilder accordian = new StringBuilder();

		String accordianId = laClassName.replaceAll(" ", "") + getCurrentTime() + classId + "Accordian";

		String collapseId = laClassName.replaceAll(" ", "") + getCurrentTime() + classId + "Collapse"
				+ ((isAccordianForStudents) ? ("Student") : ("TAndS"));

		accordian.append("<div class='accordion' id='" + accordianId + "'>");

		accordian.append(
				"<div class=''><div class='card' ><h2 class='mb-0'><button class='btn btn-block text-left  font-weight-bold text-muted ' style='background-color: rgba(0,0,0,.03);' type='button'data-toggle='collapse'");
		accordian.append("data-target='#" + collapseId + "'");

		accordian.append("aria-controls='" + collapseId + "'>" + accordianTitle + "</button></h2></div>");

		accordian.append(makeCollapseElement(dataforCollapse, collapseId, accordianId, isAccordianForStudents));

		accordian.append("</div></div></div>");

		return accordian.toString();

	}%>

	<%!private final String CLASS_ID_KEY = "class_id";

	private final String CLASS_KEY = "laClassName";

	private final String TEACHER_SUBJECT_KEY = "teachersAndSubjects";

	private final String STUDENT_KEY = "studentsOfClass";

	private final String REQUEST_ATTRIBUTE_KEY = "academy_data";

	private final String TEACHERS_SUBJECTS_ACCORDIAN_TITLE = "Teachers and subjects";

	private final String STUDENTS_ACCORDIAN_TITLE = "Students in class";%>



	<%!public String getDataFromRequestAndRenderHtml(HttpServletRequest request) {

		StringBuilder finalGrid = new StringBuilder();

		ArrayList<HashMap<String, Object>> arrayListOfallClassGroups = (ArrayList<HashMap<String, Object>>) request
				.getAttribute(REQUEST_ATTRIBUTE_KEY);

		for (HashMap<String, Object> singleLaClass : arrayListOfallClassGroups) {
			//{laClassName=Class 2, teachersAndSubjects=[Teacher 1, Subject 1, Teacher 2, Subject 2, Teacher 3, Subject 3], class_id=2, studentsOfClass=[Student 3]}

			HashMap<String, Object> currentClassInfo = singleLaClass;

			String classTitle = (String) currentClassInfo.get(CLASS_KEY);

			String classId = (String) currentClassInfo.get(CLASS_ID_KEY);

			ArrayList<String> listOfStudentsInCurrentClass = (ArrayList<String>) currentClassInfo.get(STUDENT_KEY);

			ArrayList<String> listOfTeachersAndSubjectsCurrentClass = (ArrayList<String>) currentClassInfo
					.get(TEACHER_SUBJECT_KEY);

			String gridClassName = makeClassCardTitle(classTitle);

			String teachersAndSubjectsAccordian = makeAccordian(classId, classTitle, TEACHERS_SUBJECTS_ACCORDIAN_TITLE,
					false, listOfTeachersAndSubjectsCurrentClass);

			String studentsAccordian = makeAccordian(classId, classTitle, STUDENTS_ACCORDIAN_TITLE, true,
					listOfStudentsInCurrentClass);

			finalGrid.append(makeClassGrid(gridClassName, teachersAndSubjectsAccordian, studentsAccordian));

		}

		return finalGrid.toString();
	}%>



	<div class="container">

		<ol class="list-group list-group-flush">
			<%
			out.println(getDataFromRequestAndRenderHtml(request));
			%>
		</ol>

	</div>


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

	<script>
		$('.collapse').collapse()
	</script>

</body>
</html>