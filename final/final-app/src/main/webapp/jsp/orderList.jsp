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
<p>All orders</p>
<table>
<tr>
<th>Username</th>
<th>Lastname</th>
<th>Age</th>
<th>Product</th>
<th>Price</th>
<th>Actions</th>
</tr>
<c:forEach var="order" items="${orders}">
<tr>
<td>
	${order.user.firstname}
</td>
<td>
	${order.user.lastname}
</td>
<td>
	${order.user.age}
</td>
<td>
	${order.product}
</td>
<td>
	${order.price}
</td>
<td>
<a href="/final-app/order/update?id=${order.id}">Update</a>
<a href="/final-app/order/delete?id=${order.id}">Delete</a>
</td>
</tr>
</c:forEach>
</table>
<a href="/final-app/order/add">Add new</a> <a href="/final-app/users">User list</a>
</body>
</html>
