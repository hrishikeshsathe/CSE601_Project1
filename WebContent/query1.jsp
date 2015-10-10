<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project 1: Data mining</title>
</head>
<body>
	<form name="query1" action="MainServlet" method="get">
		<input type="text" name="queryNumber" value="1"></input> <br>
		Disease Name: <input type="text" name="diseaseName"></input> <br>
		Disease Type: <input type="text" name="diseaseType"></input> <br>
		Disease Description: <input type="text" name="diseaseDescription"></input>
		<input type="submit" value="Submit">
	</form>
	<br>
	<form name="query2" action="MainServlet" method="get">
		<input type="text" name="queryNumber" value="2"></input> <br>
		Disease Description: <input type="text" name="diseaseDescription"></input>
		<input type="submit" value="Submit">
	</form>
	<br>
	<form name="query3" action="MainServlet" method="get">
		<input type="text" name="queryNumber" value="3"></input> <br>
		Disease Name: <input type="text" name="diseaseName"></input> <br>
		Measure Unit ID: <input type="text" name="measureUnitID"></input> <br>
		Cluster ID: <input type="text" name="clusterID"></input> <input
			type="submit" value="Submit">
	</form>
	<br>
	<form name="query4" action="MainServlet" method="post">
		<input type="text" name="queryNumber" value="4"></input> <br> 
		<select
			name="selectedDisease">
			<option value="ALL">ALL</option>
			<option value="AML" selected>AML</option>
			<option value="Giloblastome">Giloblastome</option>
			<option value="Colon tumor">Colon tumor</option>
			<option value="Breast tumor">Breast tumor</option>
			<option value="Flu">Flu</option>
		</select> <br> <input type="text" name="goID"> </input><input
			type="submit" value="Submit">
	</form>
	<br>
	<form name="query5" action="MainServlet" method="get">
		<input type="text" name="queryNumber" value="5"></input> <br>
		Disease Name: <input type="checkbox" name="diseaseName" value="AML">AML
		<input type="checkbox" name="diseaseName" value="ALL">ALL 
		<input type="checkbox" name="diseaseName" value="Giloblastome">Giloblastome
		<input type="checkbox" name="diseaseName" value="Colon tumor">Colon
		tumor <input type="checkbox" name="diseaseName" value="Breast tumor">Breast
		tumor <input type="checkbox" name="diseaseName" value="Flu">Flu
		<br> <input type="text" name="goID"></input> <input type="submit"
			value="Submit">
	</form>
	<br>
	<form name="part3_a" action="MainServlet" method="get">
		<input type="text" name="queryNumber" value="7"></input> <br>
		Disease Name: <input type="text" name="diseaseName"></input>
		<input type="submit" value="Submit">
	</form>
</body>
</html>