<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="loginpage" method="post">
		<h3>LOGIN PAGE</h3>
		<br>Email ID:<input type="text" name="email"><br>
        <br>Password:<input type="text" name="password">
        <h5>${msg}</h5>
        <input type="submit" value="login" /><br> 
        New User <a href="Registration.jsp">clickhere</a> <br> 
</form>
</body>
</html>