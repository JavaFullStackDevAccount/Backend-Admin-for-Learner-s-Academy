<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LearnersAcademy</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
</head>
<body>


	


	<div
		class="container mt-5 text-center d-flex align-items-center justify-content-center h-100">

		<form method="post" action="login">
			<div class="card mt-5">
				<div class="card-header text-left">
					<span class="h3 text-muted">Login</span>
				</div>
				<div class="card-body">
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="btn btn-info text-white bg-info">Username</span>
						</div>
						<input type="email" name="userEmail"
							placeholder="Enter your username ..." class="form-control"
							required />
					</div>

					<div class="input-group mt-3">
						<div class="input-group-prepend">
							<span class="btn btn-info text-white bg-info">Password</span>
						</div>
						<input type="password" name="userPassword"
							placeholder="Enter your password ..." class="form-control"
							required />
					</div>
				</div>

				<div class="card-footer text-right bg-white">
					<button class="btn btn-white bg-success text-white" type="submit">Login</button>
				</div>
			</div>
		</form>

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