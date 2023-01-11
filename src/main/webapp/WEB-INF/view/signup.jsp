<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link href="<c:url value="/resources/css/Signup.css?version=5" />" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Sign up | ProtoFramed</title>
</head>
<body>
	<div id="signupDashboard">PhotoFramed</div>
	
	<div style=" color: red; margin: 10px auto 0px; text-align: center;">
		<c:out value="${signupMsg}"></c:out>
	</div>
	
	<div id="signupFormContainer">
	
		<form:form action="createNewUserAccount" method="post" modelAttribute="user">
			<table>
				<tr>
					<td><label>Email Id: </label></td>
					<td> <form:input path="email" required="true"/> </td>
				</tr>
				<tr>
					<td><label>User name: </label></td>
					<td> <form:input path="username" required="true"/> </td>
				</tr>
				<tr>
					<td><label>Password: </label></td>
					<td><form:input path="password" required="true"/></td>
				</tr>
				<%-- <tr>
					<td><form:button name="submit">Submit</form:button></td>
					<td><label>Already have an account? <a href="login"> Login </a></label></td>
				</tr> --%>
			</table>
			<form:button id="signupBtn" name="submit">Submit</form:button>
			<label>Already have an account? <a href="/PhotoFramed/login"> Login </a></label>
		</form:form>
	</div>
		
</body>
</html>


			<%-- Email Id: <form:input path="email"/><br/><br/>
			Username: <form:input path="username"/><br/><br/>
			Password: <form:password path="password"/><br/><br/>
			<form:button name="submit">Submit</form:button> --%>