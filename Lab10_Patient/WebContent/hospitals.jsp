<%@page import="com.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/hospitals.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">

				<h1 class="m-2">Hospital Management</h1>
				<form action = "http://localhost:8080/Lab10_Hospital/hospitals" method="post" id="formhospital" name="formhospital">
					Hospital Name: <input id="Hospital_Name" name="Hospital_Name"
						type="text" class="form-control form-control-sm"> <br>
					Hospital Address: <input id="Hospital_Address"
						name="Hospital_Address" type="text"
						class="form-control form-control-sm"> <br> Hospital
					PhoneNo: <input id="Hosiptal_PhoneNo" name="Hosiptal_PhoneNo"
						type="text" class="form-control form-control-sm"> <br>
					Hospital City: <input id="Hospital_City" name="Hospital_City"
						type="text" class="form-control form-control-sm"> <br>
					Hospital Email: <input id="Hospital_Email" name="Hospital_Email"
						type="text" class="form-control form-control-sm"> <br>

					<input id="btnSave" name="btnSave" type="submit" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidHospitalIDSave" name="hidHospitalIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divHospitalsGrid">
					<%
						Hospital hospital = new Hospital();
						out.print(hospital.readHospitals());
					%>
				</div>
				<br> <br>
			</div>
		</div>
	</div>
</body>
</html>
