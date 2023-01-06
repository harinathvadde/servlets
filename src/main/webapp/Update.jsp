<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p><h3>UPDATE DATA</h3><p>
<form action="update" method="post">
	<h4>${update}</h4>
	
	Email : <input type="text" name = "email"/><br>
	<br>Name : <input type = "text" name = "name"/><br>
	<br>New E-mail : <input type = "text" name = "newemail"/><br>
	<br>Phno : <input type = "text" name = "phone"/><br>
	<br>Date Of Birth : <input type = "date" name = "dob"/><br>
	<br>Password : <input type = "password" name = "password"/><br>
	<input type = "submit" value = "submit"/>
	</form>
</body>
</html>