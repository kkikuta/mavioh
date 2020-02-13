<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<jsp:include page="../layout/head.jsp"/>
<link rel="stylesheet" href="css/common.css">
</head>
<body>
<jsp:include page="../layout/header.jsp" />
<div class="mainContent">
	<jsp:include page="../layout/mainLinks.jsp" />

	<!-- コメント投稿フォーム -->
	<form class="postForm" action="ForumServlet" method="post">
		<jsp:include page="../error/errorInformation.jsp" />
		<input type="hidden" name="process" value="create">
		タイトル <input type="text" name="title"><br>
		コメント<br>
		<textarea rows="7" cols="70" name="body"></textarea><br>
		<input type="submit" value="投稿する">
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
				<!-- コメントの投稿者 -->
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