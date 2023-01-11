<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Comment</title>
</head>
<body>

	<div style=" width:400px; margin: 40px auto; padding: 20px; border: 2px solid gray; 
				 border-radius: 10px; background-color: darkslategray; color: white;">
		<h3>Edit your comment here!</h3>
	
		<form action="updateNewComment" method="post">
			<input type="hidden" name="commentId" value="${commentId}">
			<input type="text" name="editedComment" value="${comment}" size="50">
			<br><br>
			<button> Update Comment</button>
		</form>
		
	</div>

</body>
</html>