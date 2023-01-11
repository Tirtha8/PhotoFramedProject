<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/EditForm.css?version=5" />" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Update Profile</title>
</head>
<body>

	<div id="dashboardContainer">
		<span id="brandName">PhotoFramed </span> 
		<span id="welcomeHead">Welcome, @${sessionScope.name}! </span> 
		<a id="dashboardOptionHome" href="/PhotoFramed/userHome" method="get">Home</a> 
		<a id="dashboardOptionProfile" href="/PhotoFramed/profile" method="get">Profile</a> 
		<a id="dashboardOptionLogout" href="/PhotoFramed/logout">Logout</a>
	</div>

	<h4 id="editProfileHead">Edit your profile here</h4>
	
	<div id="editFormContainer">
	
	<form action="fileForm/uploadProfilePic" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td><label>Select an image to upload as profile picture: </label></td>
			</tr>
			<tr>
				<td><input type="file" name="profilePic" accept="image/png, image/jpeg"></td>
			</tr>
			<tr>
				<td><button id="editFormUploadBtn"> Upload </button></td>
			</tr>
		</table>
		
	</form>
	
	
	<form action="fileForm/uploadFullname" method="post">
		<table>			
			<tr>	
				<td><label> Edit your full name: </label></td>
				<td><input type="text" name="fullname" value="${userModel.fullName}"></td>
				<td><button id="editFormUploadBtn"> Upload </button></td>
			</tr>
		</table>
	</form>
	<form action="fileForm/uploadMobileNumber" method="post">
		<table>			
			<tr>	
				<td><label> Edit your Mobile Number: </label></td>
				<td><input type="text" name="mobileNumber" value="${userModel.mobileNumber}" maxLength="10" size="10"></td>
				<%-- <td><input type="hidden" name="userId" value="${userModel.userId}" > </td> --%>
				<td><button id="editFormUploadBtn"> Upload </button></td>
		</table>
	</form>
	<form action="fileForm/uploadActive" method="post">
		<table>
			<tr>	
				<td><label>Active </label></td>
				<td>
					<input type="radio" id="userActive" name="active" value="Active" >Active
					<input type="radio" id="userActive" name="active" value="Inactive" checked="true">Inactive
				</td>
				<td>
					<button id="editFormUploadBtn"> Upload </button>
				</td>
			</tr>
		</table>
	</form>
	<form action="fileForm/updateNewPassword" method="post">
		<table>
			<tr>
				<td>Password</td>
				<td>
					<input type="text" name="existingPassword" placeholder="Enter existing password here"><br/>
					<input type="text" name="newPassword" placeholder="Enter new password here">
					
				</td>
				<td>
					<button id="editFormUploadBtn"> Upload </button>
				</td>
			</tr>
		</table>
	</form>
		<h4>${message}</h4>	
	</div>

</body>
</html>


	<%-- 	<form:form method="POST" action="uploadImage" enctype="multipart/form-data">
		<table>
			<tr>
				<td><form:label path="file">Select a file to upload</form:label></td>
				<td><input type="file" name="profile" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
		</form:form> --%>