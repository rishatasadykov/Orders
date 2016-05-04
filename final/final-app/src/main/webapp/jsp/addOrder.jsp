<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title>Add order</title>
			<style>
				.error {
					color: red;	
				}
			</style>
	</head>
	<body>
		<p>Add order</p>
		<form:form method="POST" modelAttribute="order">
		User<br />
		<form:select path="user">
    	<c:forEach var="user" items="${userList}">
        <form:option value="${user.id}"><c:out value="${user.lastname} ${user.firstname}"/></form:option>
    	</c:forEach>
    	</form:select>
	    <br /><br />
	    Product<form:errors path="product" cssClass="error"/><br />
	    <form:input path="product"/><br /><br />
	    Price<form:errors path="price" cssClass="error"/><br />
	    <form:input path="price"/><br /><br />
	    <input type="submit" value="Add order">
		</form:form>
		 <a href="/final-app/orders"><button>Cancel</button></a>
	</body>
</html>