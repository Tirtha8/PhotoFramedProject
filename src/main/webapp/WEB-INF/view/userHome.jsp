<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<link href="<c:url value="/resources/css/UserHome.css?version=5" />" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>

	<div id="dashboardContainer">
		<span id="brandName">PhotoFramed </span> 
		<span id="welcomeHead">Welcome, @${sessionScope.name}! </span> 
		<a id="dashboardOptionHome" href="/PhotoFramed/userHome" method="get">Home</a> 
		<a id="dashboardOptionProfile" href="/PhotoFramed/profile" method="get">Profile</a> 
		<a id="dashboardOptionLogout" href="/PhotoFramed/logout">Logout</a>
	</div>

	<div id="homePostsContainer">
		<c:if test = "${empty imageModelList }">
			<div style = " text-align: center; font-size: 20px; margin-top: 200px;">No post to show. Try after some time.</div>
		</c:if>
		<c:if test = "${!empty imageModelList}">
				
				<c:forEach items = "${imageModelList}" var="imageModel">
					<div id="imageContainer">
						<c:set var = "imageId" scope = "session" value = "${imageModel.imageId}"/>
						
						<table>
							<tr>
								<td>
									<c:if test = "${!empty userModelList}">
										<c:forEach items = "${userModelList}" var="userModel">
											<c:if test = "${userModel.username == imageModel.userModel.username}">
												<span>
													<img id="homePostProfilePic" alt="postProfilePic" 
														src ="<c:url value = "/resources/images/${userModel.profilePicture}" />" >
												</span>
											</c:if>
										</c:forEach>
									</c:if>
								</td>
								<td>
									<span id="userHandle">@<c:out value = "${imageModel.userModel.username}" /></span><br/>					
								</td>
							</tr>
						</table>
						
						
						<div id="homePostCaption"><c:out value="${imageModel.imageTag}" /></div>
						<img id="homePost" alt="post" src ="<c:url value = "/resources/images/${imageModel.imageName}" />" ><br/>
						<table id="homePostActions">
							<tr>
								<%-- <td id="homePostAction"><b> ${imageModel.likes} </b>  Likes</td>
								<td id="homePostAction"><b> ${imageModel.comments} </b>  Comments</td>
								<td id="homePostAction"><b>  ${imageModel.shares}  </b>  Shares</td> --%>
								<td id="homePostAction"><b> 0 </b>  Likes</td>
								<td id="homePostAction"><b> 0 </b>  Comments</td>
								<td id="homePostAction"><b> 0 </b>  Shares</td>
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
						
						<c:if test = "${imageModel.userModel.username != sessionScope.name}">
							<div id="postCommentSection">
								<form action="userHome/addComment" method="post">
									<input id="commentor" type="hidden" name="commentorUsername" value="${sessionScope.name}"> 
									<input id="image-Id" type="hidden" name="imageId" value="${imageId}"> 
									<input id="homePostComment" type="text" name="postComment" size="55" placeholder="Add a comment here">
									<button id="addCommentBtn">Add</button>
								</form>
							</div>
						</c:if>
						
					</div>
				</c:forEach>			
		</c:if>
	</div>

</body>
</html>



	<%-- <tags:url var="all" value="/user/getAllUser" />
	<tags:url var="prof" value="/user/profile/${sessionScope.name}" />
	
	<h1>Welcome to Home! <a href="${prof}" > ${sessionScope.name} </a></h1>
	<br/>
	${userDetails}
	
	<a href="${all}" ><h2>See the List of employees</h2></a>
	${employeeList} --%>