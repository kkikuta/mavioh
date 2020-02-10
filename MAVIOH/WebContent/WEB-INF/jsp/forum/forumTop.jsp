<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.Comment, beans.User, java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<jsp:include page="../base/head.jsp"/>
<link rel="stylesheet" href="css/forum.css">
</head>
<body>
<jsp:include page="../base/header.jsp" />
<div class="mainContent">

	<!-- メインのリンク -->
	<jsp:include page="../base/mainLinks.jsp" />

	<!-- コメント投稿フォーム -->
	<form class="createCommentForm" action="ForumServlet" method="post">
		<jsp:include page="../base/errorInformation.jsp" />
		<input type="hidden" name="process" value="create">
		タイトル <input type="text" name="title"><br>
		コメント<br>
		<textarea rows="7" cols="70" name="body"></textarea><br>
		<div class="submitButton"><input type="submit" value="投稿する"></div>
	</form>

	<!-- コメント一覧 -->
	<c:forEach var="comment" items="${commentList}">
		<div class="commentBox">
			<div class="title">
				<c:out value="${comment.title}" />
			</div>
			<div class="body">
				<c:out value="${comment.body}" />
			</div>
			<div class="userIdAndPostTime">

				<c:forEach var="user" items="${userList}">
					<c:if test="${comment.userId == user.id}">
						<c:out value="${user.name}" />　:　
					</c:if>
				</c:forEach>

				<c:out value="${comment.postTime}" />
			</div>
		</div>
	</c:forEach>

</div>
</body>
</html>