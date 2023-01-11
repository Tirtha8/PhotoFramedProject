<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>

<html>
<head>
<link href="<c:url value="/resources/css/Login.css?version=5" />" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Admin Login | ProtoFramed</title>
</head>
<body>
	
	<div id="loginDashboard">Admin | PhotoFramed</div>
	
	<div style=" color: red; margin: 10px auto 0px; text-align: center;">
		<c:out value="${loginMsg}"></c:out>
	</div>
	
	<div id="loginFormContainer">
		<form:form action="validateAdminLogin" modelAttribute="userLogin">
			<table>
				<tr>
					<td><label>User name: </label></td>
					<td> <form:input path="username" /> </td>
				</tr>
				<tr>
					<td><label>Password: </label></td>
					<td><form:input path="password" /></td>
				</tr>
				<%-- <tr>
					<td><form:button id="loginBtn" name="submit">Submit</form:button></td>
					<td><label>Dont have an account? <a href="adminSignup"> Signup </a></label></td>
				</tr> --%>
			</table>
			<form:button id="loginBtn" name="submit">Submit</form:button>
			<label>Dont have an account? <a href="/PhotoFramed/login"> Signup </a></label>
		
		</form:form>
	</div>
	
</body>
</html>



			<%-- User-name: <form:input path="username" />
			<br />
			<br />
			Password: <form:input path="password" />
			<br />
			<br />
			<br />
			<form:button name="submit">Submit</form:button> --%>