<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ALL DATA</title>
</head>
<body>
<p><h3>AVAIABLE DATA</h3><p>
        <c:forEach items="${data}" var="output">
        <p>Name: ${output.name}, EmailId : ${output.email}, Phone: ${output.phno}, Dob : ${output.dob}, Age : ${output.age}</p>
        </c:forEach>
</body>
</html>