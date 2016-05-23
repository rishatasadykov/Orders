<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title>Add user</title>
			<style>
				.error {
					color: red;	
				}
			</style>
	</head>
	<body>
		<p>Add user</p>
		<form:form method="POST" modelAttribute="user">
	    Firstname<form:errors path="firstname" cssClass="error"/><br />
	    <form:input path="firstname"/><br /><br />
	    Lastname<form:errors path="lastname" cssClass="error"/><br />
	    <form:input path="lastname"/><br /><br />
	    Age<form:errors path="age" cssClass="error"/><br />
	    <form:input path="age"/><br /><br />
	    <input type="submit" value="Save user"> 
		</form:form>
		<a href="/final-app/users"><button>Cancel</button></a>
	</body>
</html>