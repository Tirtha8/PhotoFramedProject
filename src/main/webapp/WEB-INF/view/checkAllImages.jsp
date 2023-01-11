<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/CheckAllImages.css?version=5" />" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>All images | Admin </title>
</head>
<body>

	<div id="adminDashboardContainer">
		<span id="adminHeading">Welcome home, ${sessionScope.name}!</span>
		<a style=" text-decoration: none; color: white; font-weight: bold; font-size: 20px; padding-left: 20px;" 
		href="/PhotoFramed/admin/adminHome">Home</a>
		<a style=" text-decoration: none; color: white; font-weight: bold; float: right; font-size: 20px; padding-right: 20px;" 
		href="/PhotoFramed/logout">Logout</a>
	 </div>
	 
	<!--  <div style="text-align: center; width: 100px; font-size: 20px; margin: 10px auto;">
	 	All images
	 </div>
	 	<hr/> -->
	
	<div >
		<c:if test="${empty imageModelList}">
			<span style= "display: table; font-size: 30px; margin: 30px auto;">No posts to show!</span>
		</c:if>
		<c:if test="${!empty imageModelList}">

			<c:forEach items="${imageModelList}" var="imageModel">
				<div id="imageContainer">
					<c:set var="imageId" scope="session" value="${imageModel.imageId}" />
					
					<table style=" float: right;">
						<tr>
							<td>
								<form action="checkAllImages/editPost" method="post">
									<input type="hidden" name="imageTag" value="${imageModel.imageTag}">
									<input type="hidden" name="imageName" value="${imageModel.imageName}">
									<input type="hidden" name="imageId" value="${imageModel.imageId}">
									<input type="hidden" name="userId" value="${userId}">
									<button id="editCommentBtn">Edit post</button>
								</form>	
							</td>
							<td>
								<form action="checkAllImages/deletePost" method="post">
									<input type="hidden" name="imageId" value="${imageModel.imageId}">
									<input type="hidden" name="userId" value="${userId}">
									<button id="deleteCommentBtn">Delete post</button>
								</form>	
							</td>
						</tr>
					</table>

					<table>
							<tr>
								<%-- <td>
									<c:if test = "${!empty userModelDetails}">
										<c:forEach items = "${userModelDetails}" var="userModel">
											<c:if test = "${userModel.username == imageModel.userModel.username}">
												<span>
													<img id="profilePostProfilePic" alt="ppic" 
														 
														src ="<c:url value = "/resources/images/${userModel.profilePicture}" />" >
												</span>
											</c:if>
										</c:forEach>
									</c:if>
								</td> --%>
								<td>
									<span id="userHandle">@<c:out value = "${imageModel.userModel.username}" /></span><br/>					
								</td>
							</tr>
						</table>
										
					<br/>
					
					<div id="homePostCaption"><c:out value="${imageModel.imageTag}" /></div>				
					<img id="homePost" alt="post" style = "width: 500px; height: 480px; margin: 20px auto; border-radius: 5px;"						
						src="<c:url value = "/resources/images/${imageModel.imageName}" />"><br />
					
					<table id="homePostActions">
						<tr>
							<td id="homePostAction"><b> 0 </b> Likes</td>
							<td id="homePostAction"><b> 0 </b> Comments</td>
							<td id="homePostAction"><b> 0 </b> Shares</td>
						</tr>
					</table>
					<div id="commentSection">
							
							<c:if test = "${empty commentModelList }">
								<div style=" text-align: center; color: white;">
									No Comments to show.
								</div>
							</c:if>
							
							<c:if test = "${!empty commentModelList }">
								<c:forEach items = "${commentModelList}" var="commentModel">
									<c:if test = "${commentModel.imageModel.imageId == imageId }">
										<div id="commentBox">
											<table>
												<tr>
													<td>
														<div id="commentorUsername">
																<span > @${commentModel.commentorUsername} </span>
														</div>
													</td>
												</tr>
											</table>
											<div id="commentString">
												<span> ${commentModel.commentString}</span><br/>
											</div>
										</div>
									</c:if>							
								</c:forEach>
							</c:if>
						</div>
				</div>
			</c:forEach>
		</c:if>

	</div>

</body>
</html>