<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form name="query1" action="MainServlet" method="get">
<input type="text" name="queryNumber" value="1"></input>
<br>
Disease Name: <input type="text" name="diseaseName"></input>
<br>
Disease Type: <input type="text" name="diseaseType"></input>
<br>
Disease Description: <input type="text" name="diseaseDescription"></input>
<input type="submit" value="Submit">
</form>
<br>
<form name="query2" action="MainServlet" method="get">
<input type="text" name="queryNumber" value="2"></input>
<br>
Disease Description: <input type="text" name="diseaseDescription"></input>
<input type="submit" value="Submit">
</form>
<br>
<form name="query3" action="MainServlet" method="get">
<input type="text" name="queryNumber" value="3"></input>
<br>
Disease Name: <input type="text" name="diseaseName"></input>
<br>
Measure Unit ID: <input type="text" name="measureUnitID"></input>
<br>
Cluster ID: <input type="text" name="clusterID"></input>
<input type="submit" value="Submit">
</form>
</body>
</html>