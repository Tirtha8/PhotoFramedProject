<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Edit Post | Admin</title>
</head>
<link href="<c:url value="/resources/css/EditPost.css?version=5" />" rel="stylesheet">
<body>
	<div id="imageContainer">
		<div style=" font-size: 25px; text-align: center;">Edit your post here!</div>
		<hr />
		
		<form action="postEditedByAdmin" method="post">
			<div  id="captionDiv">
				<label>Edit the caption: </label><br/>
				<input id="captionInput" type="text" name="imageTag" value="${imageTag}"><br/>
				<input type="hidden" name="imageId" value="${imageId}"><br/>
				<input type="hidden" name="userId" value="${userId}"><br/>
			</div>
			
			<div id="postToEdit">
				<img alt="post" style= " width: 400px; "
					 src="<c:url value = "/resources/images/${imageName}" />" >
			</div>
			<button id="updateBtn" >Update</button>
		</form>	
	
	</div>
</body>
</html>