<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add User Form</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/UserController?action=insert" method="post">
		<legend>Add New User</legend>
		<label>Name: </label>
		<input type="text" id="userName" name="userName">
		<label>Email: </label>
		<input type="email" id="email" name="email">
		<button type="submit">Add New User</button>
		<a href="${pageContext.request.contextPath}/UserController?action=list">Back to Home</a>
	</form>
</body>
</html>