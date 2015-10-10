<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  		<link rel="stylesheet" href="/resources/ui.css" >
		<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css" type="text/css">
		<title>Project 1: Data</title>
	</head>

	<body>
		<div class="form-style-3">

		<h1>Project 1: Data mining</h1><br/>

		<form name="query1" action="MainServlet" method="get">
			<fieldset>
				<legend>Query 1</legend>
				<input type="hidden" name="queryNumber" value="1"></input> <br>

				<label for="diseaseName"><span>Disease name</span>
				<select name="diseaseName" class="select-field">
					<option value="ALL">ALL</option>
					<option value="AML" selected>AML</option>
					<option value="Giloblastome">Giloblastome</option>
					<option value="Colon tumor">Colon tumor</option>
					<option value="Breast tumor">Breast tumor</option>
					<option value="Flu">Flu</option>
				</select>
				</label>

				<label for="diseaseType"><span>Disease type</span>
				<select name="diseaseType" class="select-field">
					<option value="CNS_tumor">CNS Tumor</option>
					<option value="leukemia" selected>Leukemia</option>
					<option value="adeno-carcinoma">Adeno carcinoma</option>
					<option value="infection">Infection</option>
				</select>
				</label>

				<label for="diseaseDescription"><span>Disease description</span>
				<select name="diseaseDescription" class="select-field">
					<option value="tumor" selected>Tumor</option>
					<option value="Desp. of flue" selected>Desp. of flue</option>
				</select>
				</label>

				<label><span>&nbsp;</span><input type="submit" value="Run query" /></label>
			</fieldset>
		</form><br/><br/>

		<form name="query2" action="MainServlet" method="get">
			<fieldset>
				<legend>Query 2</legend>
				<input type="hidden" name="queryNumber" value="2"></input> <br>

				<label for="diseaseDescription"><span>Disease description</span>
				<select name="diseaseDescription" class="select-field">
					<option value="tumor" selected>Tumor</option>
					<option value="Desp. of flue" selected>Desp. of flue</option>
				</select>
				</label>

				<label><span>&nbsp;</span>
				<input type="submit" value="Run query" /></label>
			</fieldset>
		</form><br/><br/>

		<form name="query3" action="MainServlet" method="get">
			<fieldset>
				<legend>Query 3</legend>
				<input type="hidden" name="queryNumber" value="3"></input> <br>

				<label for="diseaseName"><span>Disease name</span>
				<select name="diseaseName" class="select-field">
					<option value="ALL">ALL</option>
					<option value="AML" selected>AML</option>
					<option value="Giloblastome">Giloblastome</option>
					<option value="Colon tumor">Colon tumor</option>
					<option value="Breast tumor">Breast tumor</option>
					<option value="Flu">Flu</option>
				</select>
				</label>

				<label for="goID"><span>GO ID</span>
				<input type="text" class="input-field" name="goID" value="" /></label>

				<label><span>&nbsp;</span><input type="submit" value="Run query" /></label>
			</fieldset>
		</form><br/><br/>

		<form name="query4" action="MainServlet" method="get">
			<fieldset>
				<legend>Query 4</legend>
				<input type="hidden" name="queryNumber" value="4"></input> <br>
				
				<label for="field1"><span>Disease name</span>
				<select name="selectedDisease" class="select-field">
					<option value="ALL">ALL</option>
					<option value="AML" selected>AML</option>
					<option value="Giloblastome">Giloblastome</option>
					<option value="Colon tumor">Colon tumor</option>
					<option value="Breast tumor">Breast tumor</option>
					<option value="Flu">Flu</option>
				</select>
				</label>

				<label for="field2"><span>Measure unit ID</span>
				<input type="text" class="input-field" name="measureUnitID" value="" /></label>
				
				<label for="field3"><span>Cluster ID</span>
				<input type="text" class="input-field" name="clusterID" value="" /></label>

				<label><span>&nbsp;</span><input type="submit" value="Run query" /></label>
			</fieldset>
		</form><br/><br/>

		<form name="query5" action="MainServlet" method="get">
			<fieldset>
				<legend>Query 5</legend>
				<input type="hidden" name="queryNumber" value="5"></input> <br>
				
				<label for="field1"><span>Disease name</span>
				<select name="diseaseName" class="select-field">
					<option value="ALL">ALL</option>
					<option value="AML" selected>AML</option>
					<option value="Giloblastome">Giloblastome</option>
					<option value="Colon tumor">Colon tumor</option>
					<option value="Breast tumor">Breast tumor</option>
					<option value="Flu">Flu</option>
				</select>
				</label>

				<label for="goID"><span>GO ID</span>
				<input type="text" class="input-field" name="goID" value="" /></label>

				<label><span>&nbsp;</span><input type="submit" value="Run query" /></label>
			</fieldset>
		</form><br/><br/>

		<form name="query6" action="MainServlet" method="get">
			<fieldset>
				<legend>Query 6</legend>
				<input type="hidden" name="queryNumber" value="6"></input> <br>
				
				<label for="howManyDiseases">
				<span>No. of diseases</span>
				<input type="radio" name="howManyDiseases" value="one">One disease<br>
				<span>&nbsp;</span><input type="radio" name="howManyDiseases" value="two">Two diseases<br>
				</label>

				<label for="diseaseName1"><span>Disease 1</span>
				<select name="diseaseName1" class="select-field">
					<option value="ALL">ALL</option>
					<option value="AML" selected>AML</option>
					<option value="Giloblastome">Giloblastome</option>
					<option value="Colon tumor">Colon tumor</option>
					<option value="Breast tumor">Breast tumor</option>
					<option value="Flu">Flu</option>
				</select>
				</label>

				<label for="diseaseName2"><span>Disease 2</span>
				<select name="diseaseName2" class="select-field">
					<option value="ALL">ALL</option>
					<option value="AML" selected>AML</option>
					<option value="Giloblastome">Giloblastome</option>
					<option value="Colon tumor">Colon tumor</option>
					<option value="Breast tumor">Breast tumor</option>
					<option value="Flu">Flu</option>
				</select>
				</label>

				<label for="goID"><span>GO ID</span>
				<input type="text" class="input-field" name="goID" value="" /></label>

				<label><span>&nbsp;</span><input type="submit" value="Run query" /></label>
			</fieldset>
		</form><br/><br/>

		<form name="part3_a" action="MainServlet" method="get">
			<fieldset>
				<legend>Query 7 (Part 3a) </legend>
				<input type="hidden" name="queryNumber" value="7"></input> <br>
				
				<label for="diseaseName"><span>Disease name</span>
				<select name="diseaseName" class="select-field">
					<option value="ALL">ALL</option>
					<option value="AML" selected>AML</option>
					<option value="Giloblastome">Giloblastome</option>
					<option value="Colon tumor">Colon tumor</option>
					<option value="Breast tumor">Breast tumor</option>
					<option value="Flu">Flu</option>
				</select>
				</label>

				<label><span>&nbsp;</span><input type="submit" value="Run query" /></label>
			</fieldset>
		</form><br/><br/>

		</div>
	</body>
</html>