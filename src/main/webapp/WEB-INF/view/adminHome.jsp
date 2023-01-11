<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/AdminHome.css?version=5" />" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Admin Home | PhotoFramed</title>
</head>
<body>
	<div id="adminDashboardContainer">
		<span id="adminHeading">Welcome home, ${sessionScope.name}!</span>
		<a style=" text-decoration: none; color: white; font-weight: bold; float: right; font-size: 20px; padding-right: 20px;" 
		
		href="/PhotoFramed/logout">Logout</a>
	 </div>

	<div style=" color: red; margin: 10px auto 0px; text-align: center;">
		<c:out value="${addAdminWarning}"></c:out>
	</div>
	<span style=" display: table; margin: 20px auto 5px;">Add new admin here</span>
	<div id="addAdminContainer">
		<form action="createNewAdmin" method="post">
			<table>
				<tr>
					<td><label>Email Id: </label></td>
					<td> <input type="text" name="email" /> </td>
				</tr>
				<tr>
					<td><label>User name: </label></td>
					<td> <input type="text" name="username" /> </td>
				</tr>
				<tr>
					<td><label>Password: </label></td>
					<td> <input type="text" name="password" /></td>
				</tr>
				
			</table>
			<button id="submitBtn" type="submit">Submit</button>

		</form>
	</div>
	
	<%-- <div id="addDefaultProfilePictureContainer">
		<form action="adminHome/uploadDefaultProfilePic" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td><label>Upload as Default profile picture: </label></td>
			</tr>
			<tr>
				<td><input type="file" name="profilePic"></td>
			</tr>
			<tr>
				<td><button id="editFormUploadBtn"> Upload </button></td>
			</tr>
		</table>
	</form>
	</div> --%>
	<hr/>

	
	<div style=" color: red; margin: 10px auto 0px; text-align: center;">
		<c:out value="${deletionStatus}"></c:out>
	</div>

	<div id="userListContainer">

		<c:out value="${msg}"></c:out>
		
		<h2 id="userListHeading">User List</h2>
		
		<c:if test="${!empty userList}">
			<table id="userListTable">
				<tr>
					<th>User Id</th>
					<th>User-email</th>
					<th>User-name</th>
					<th>Role</th>
					<th>Status</th>
					<th>Action</th>
					<th>Check</th>
				</tr>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td><c:out value="${user.userId}" /></td>
						<td><c:out value="${user.email}" /></td>
						<td><c:out value="${user.username}" /></td>
						<td><c:out value="${user.role}" /></td>
						<td><c:out value="${user.active}" /></td>
						<td>
							<c:if test = "${user.role eq 'ROLE_ADMIN'}" >
							
							</c:if>
							<c:if test = "${user.role != 'ROLE_ADMIN'}" >
								<form id="actionsBtns" action="adminHome/editUser" method="post" >
									<button disabled>Edit</button>
								</form>
							</c:if>
							<c:if test = "${user.username != 'admin'}" >
								<form id="actionsBtns" action="adminHome/deleteUser" method="post">
									<input type="hidden" name="active" value="${user.active}">
									<input type="hidden" name="userId" value="${user.userId}">
									<input type="hidden" name="role" value="${user.role}">
									<button> Delete </button>
								</form>
							</c:if>
						</td>
						<td>
							<c:if test = "${user.role eq 'ROLE_ADMIN'}" >
								N/A
							</c:if>
							<c:if test = "${user.role != 'ROLE_ADMIN'}" >
								<form action="adminHome/checkAllImages" method="post">
									<input type="hidden" name="userId" value="${user.userId}">
									<button > All images </button>
								</form>
							</c:if>
						</td>
						
					</tr>
				</c:forEach>
			</table>
		</c:if>


	</div>




</body>
</html>