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


	<%!private final String CLASS_KEY_FOR_REQUEST = "la_classes";

	private final String SUBJECT_KEY_FOR_REQUEST = "la_subjects";

	private final String TEACHERS_KEY_FOR_REQUEST = "la_teachers";%>


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


	<%!private ArrayList<String> getSubjectFromRequest(HttpServletRequest request) {

		ArrayList<String> subjectsInAcademy = removeNaFromArrayList(
				(ArrayList<String>) request.getAttribute(SUBJECT_KEY_FOR_REQUEST));

		return subjectsInAcademy;

	}%>

	<%!private ArrayList<String> getTeachersFromRequest(HttpServletRequest request) {

		ArrayList<String> teachersInAcademy = removeNaFromArrayList(
				(ArrayList<String>) request.getAttribute(TEACHERS_KEY_FOR_REQUEST));

		return teachersInAcademy;

	}%>


	<%!private String createMenuOptions(String optionName, ArrayList<String> optionValuesAndLabel) {

		StringBuilder menuOptions = new StringBuilder();

		Iterator itr = optionValuesAndLabel.iterator();

		for (; itr.hasNext();) {
			menuOptions.append("<option value='" + itr.next() + "'>" + itr.next() + "</option>\n");
		}

		return menuOptions.toString();

	}%>







	<!-- Initial data fetching above -->



	<div class="container mt-5 h-100 text-center pt-5">
		<div
			class="jumbotron mt-5 d-flex align-items-center justify-content-center bg-transparent w-100">
			<div class="card w-50">

				<div class="card-body">
					<h3 class="card-title text-left pb-4 text-muted">Assign
						subjects to class</h3>
					<form method="post" action="createMapping">

						<div class="input-group mb-4 w-100 ">
							<div class="input-group-prepend ">
								<span class="input-group-text bg-primary text-white">Class</span>
							</div>
							<select class="custom-select" name="selectedClass">

								<!-- Here classes should come from database -->
								<option selected>Select class</option>

								<%
								out.print(createMenuOptions("selectedClass", getClassesFromRequest(request)));
								%>
							</select>
						</div>


						<div class="input-group mb-4 w-100 ">
							<div class="input-group-prepend ">
								<span class="input-group-text bg-primary text-white">Teacher</span>
							</div>
							<select class="custom-select" name="selectedTeacher">

								<!-- Here teachers should come from database -->
								<option selected>Select teacher</option>
								<%
								out.print(createMenuOptions("selectedTeacher", getTeachersFromRequest(request)));
								%>
							</select>
						</div>


						<div class="input-group mb-4 w-100 ">
							<div class="input-group-prepend ">
								<span class="input-group-text bg-primary text-white">Subject</span>
							</div>
							<select class="custom-select" name="selectedSubject">

								<!-- Here teachers should come from database -->
								<option selected>Select subject</option>
								<%
								out.print(createMenuOptions("selectedSubject", getSubjectFromRequest(request)));
								%>
							</select>
						</div>


						<button type="submit" value="submit"
							class="btn btn-success btn-block mt-4">Done</button>



					</form>

				</div>

			</div>
		</div>
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

</body>
</html>