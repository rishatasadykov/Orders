<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<style>
   table { 
    border: 1px double black; 
    border-collapse: collapse; 
   }
   th { 
    text-align: left;
    padding: 5px; 
    border: 1px solid black; 
   }
   td { 
    padding: 5px; 
    border: 1px solid black; 
   }
  </style>
</head>
<body>
<p>All users</p>
<table>
<tr>
<th>Name</th>
<th>Surname</th>
<th>Age</th>
<th>Actions</th>
</tr>
<c:forEach var="user" items="${users}">
<tr>
<td>
	${user.firstname}
</td>
<td>
	${user.lastname}
</td>
<td>
	${user.age}
</td>
<td>
<a href="/final-app/user/update?id=${user.id}">Update</a>
<a href="/final-app/user/delete?id=${user.id}">Delete</a>
</td>
</tr>
</c:forEach>
</table>
<a href="/final-app/user/add">Add new</a>              <a href="/final-app/orders">Order list</a>
</body>
</html>
