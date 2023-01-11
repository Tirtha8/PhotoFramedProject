<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<link href="<c:url value="/resources/css/Profile.css?version=5" />" rel="stylesheet">
<script src="<c:url value="/resources/js/Profile.js" />"></script>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
<body>

	<div id="dashboardContainer">
		<span id="brandName">PhotoFramed </span> 
		<span id="welcomeHead">Welcome, @${sessionScope.name}! </span> 
		<a id="dashboardOptionHome" href="/PhotoFramed/userHome" method="get">Home</a> 
		<a id="dashboardOptionProfile" href="/PhotoFramed/profile" method="get">Profile</a> 
		<a id="dashboardOptionLogout" href="/PhotoFramed/logout">Logout</a>
	</div>

	<table id="t1">
		<tr id="r1">
			<td id="d1">
				<%-- <c:if test="${empty userModelDetails.profilePicture}">
					<c:set var="userModelDetails.profilePicture" value="dpp.png" />
					<h5>/resources/images/${userModelDetails.profilePicture}</h5>
				</c:if>  --%>						
				
				<img id="profilePic" alt="pp"
					src="<c:url value = "/resources/images/${userModelDetails.profilePicture}" />">
				<br />

				<div style=" font-size: 20px; font-weight: bold; text-align: center;"><c:out value="${userModelDetails.fullName}" /></div>
			</td>
			<td id="d2">
				${userModelDetails.posts} Posts &nbsp;&nbsp;&nbsp;&nbsp;
				${userModelDetails.followers} Followers &nbsp;&nbsp;&nbsp;&nbsp;
				${userModelDetails.following} Following <br /> <br /> 
				
				User name: <b> @<c:out value="${userModelDetails.username}" /></b></b><br /> 
				Mobile Number: <b><c:out value="${userModelDetails.mobileNumber}" /></b><br /> 
				Status: <b><c:out value="${userModelDetails.active}" /></b><br /> <br /> 
				<a id="editProfileBtn" href="profile/fileForm">Edit Profile</a> <br />
			</td>
			</tr>

	</table>
	<!-- <hr> -->

	<div id="uploadPostContainer">
		<form action="profile/uploadPost" method="post" enctype="multipart/form-data" modelAttribute="postUpload">

			<div style=" margin:10px 0px;">
				<label>Enter the caption here: </label> 
				<span><input type="text" name="imageTag" size="35" style="padding: 5px;"></span>
			</div>

			<div style=" margin:10px 0px;">
				<span><label>Select the image to upload: </label></span> 
				<span><input type="file" name="userPost" accept="image/png, image/gif, image/jpeg"></span>
			</div>
			
			<button id="uploadPostBtn">Upload post</button>

			<%-- <h4>${message}</h4> --%>

		</form>
	</div>

	<hr>
	<c:if test="${empty imageModelList }">
		<div style= "background-color: white; border: none; text-align: center; font-size: 25px; margin-top: 100px;">
			No post to show! Try posting a photo.
		</div>
	</c:if>
		
		
	<div id="profilePostContainer">		
		<c:if test="${!empty imageModelList}">

			<c:forEach items="${imageModelList}" var="imageModel">
				<div id="imageContainer">
					<c:set var="imageId" scope="session" value="${imageModel.imageId}" />
					
					<table style=" float: right;">
						<tr>
							<td>
								<form action="profile/editPost" method="post">
									<input type="hidden" name="imageTag" value="${imageModel.imageTag}">
									<input type="hidden" name="imageName" value="${imageModel.imageName}">
									<input type="hidden" name="imageId" value="${imageModel.imageId}">
									<button id="editCommentBtn">Edit post</button>
								</form>	
							</td>
							<td>
								<form action="profile/deletePost" method="post">
									<input type="hidden" name="imageId" value="${imageModel.imageId}">
									<button id="deleteCommentBtn">Delete post</button>
								</form>	
							</td>
						</tr>
					</table>

					<table>
							<tr>
								<td>
									<c:if test = "${!empty userModelDetails}">
										<%-- <c:forEach items = "${userModelDetails}" var="userModel">
										</c:forEach> --%>
											<c:if test = "${userModelDetails.username == imageModel.userModel.username}">
												<span>
													<img id="profilePostProfilePic" alt="postProfilePic" 
														src ="<c:url value = "/resources/images/${userModelDetails.profilePicture}" />" >
												</span>
											</c:if>
									</c:if>
								</td>
								<td>
									<span id="userHandle">@<c:out value = "${imageModel.userModel.username}" /></span><br/>					
								</td>
							</tr>
						</table>

					<%-- <c:if test="${!empty userModelDetails}">
						<c:forEach items="${userModelDetails}" var="userModel">
							<c:if test="${userModel.username == imageModel.userModel.username}">
								<span> 
									<img id="profilePostProfilePic" alt="postProfilePic"
												src="<c:url value = "/resources/images/${userModel.profilePicture}" />">
								</span>
							</c:if>
						</c:forEach>
					</c:if>
					<span id="userHandle"><c:out value="${imageModel.userModel.username}" /></span> --%>
						
						
					<br/>
					<div id="homePostCaption"><c:out value="${imageModel.imageTag}" /></div>				
					<img id="homePost" alt="post"
						src="<c:url value = "/resources/images/${imageModel.imageName}" />"><br />
					<table id="homePostActions">
						<tr>
							<td id="homePostAction"><b> <c:out value="${imageModel.likes}" /> </b> Likes</td>
							<td id="homePostAction"><b> <c:out value="${imageModel.comments}" /> </b> Comments</td>
							<td id="homePostAction"><b> <c:out value="${imageModel.shares}" /> </b> Shares</td>
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
													<c:if test="${ sessionScope.name == commentModel.commentorUsername }">
															<td>
																<form action="userHome/editComment" method="post">
																	<input type="hidden" name="commentId" value="${commentModel.commentId}">
																	<input type="hidden" name="comment" value="${commentModel.commentString}">
																	<button id="editComment"> Edit  </button>
																</form>
															</td>
															<td>
																<form action="userHome/deleteComment" method="post">
																	<input type="hidden" name="commentId" value="${commentModel.commentId}">
																	<button id="deleteComment"> Delete </button>
																</form>
															</td>
													</c:if>
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