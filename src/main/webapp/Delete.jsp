<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p><h3>DELETE DATA</h3><P>
<form action="deletedata" method="post">
	<h4>${delete}</h4>
	<h4>${msg}</h4>
	Email Id : <input type = "text" name = "email"/><br>
	<input type = "submit" value = "submit"/>
	</form>
</body>
</html>