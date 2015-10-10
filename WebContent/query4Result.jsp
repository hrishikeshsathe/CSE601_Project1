<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Query 4 result</title>
</head>
<body>
<% ArrayList<String> arr = (ArrayList<String>)request.getAttribute("result"); %>
<table border = "1" cellpadding = "5">
<tr>
<th>T-Test</th>
</tr>
<% for(int i=0; i<arr.size(); i++) { 
String temp[] = arr.get(i).split("\\|");%>
<tr>
<% for(int j=0; j<temp.length; j++) {%>
<td><%=temp[j] %></td>
<%} %>
</tr>
<%} %>
</table>
</body>
</html>