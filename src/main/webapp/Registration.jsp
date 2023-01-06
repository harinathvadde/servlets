<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p><H1>NEW REGISTRATION FORM</H1><p>
<form action="registration" method="post">
<h4>${insertFailed}</h4>
<h4>${insertSuccess}</h4>
<h4>${null}</h4>
	Name : <input type = "text" name = "name"/><br>
	<br>E-mail : <input type = "text" name = "email"/><br>
	<br>Phone Number : <input type = "text" name = "phone"/><br>
	<br>Date Of Birth : <input type = "date" name = "dob"/><br>
	<br>Password : <input type = "password" name = "password"/><br>
	<br><input type="submit" value="Register" />
</form>
</body>
</html>