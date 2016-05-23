<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Authentication</title>
    <style>
				.error {
					color: red;	
				}
			</style>
</head>
 
<body>
 
<form:form method="POST" modelAttribute="user">
Login<form:errors path="login" cssClass="error"/><br />
<form:input path="login"/><br /><br />
Password<form:errors path="password" cssClass="error"/><br />
<form:input path="password"/><br /><br />
<input type="submit" value="Sign in"> 
</form:form>


 
</body>
</html>