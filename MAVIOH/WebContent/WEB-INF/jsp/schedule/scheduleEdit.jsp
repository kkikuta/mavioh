<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../layout/head.jsp" />
<link rel="stylesheet" href="css/common.css">
</head>
<body>
<jsp:include page="../layout/header.jsp" />
<div class="mainContent">
	<jsp:include page="../layout/mainLinks.jsp" />

	<!-- 表示中のイベントの日にち -->
	<c:out value="${event.date}" /><br>

	<!-- イベント詳細 -->
	<c:out value="${event.title}" /><br>
	<c:out value="${event.detail}" /><br>

	<!-- 編集リンク -->
	<form class="postForm" name="editLink" action="ScheduleServlet" method="post">
		<jsp:include page="../error/errorInformation.jsp" />
		<input type="hidden" name="process" value="executeEdit">
		<input type="hidden" name="id" value="${event.id}">
		<input type="text" name="title" value="${event.title}"><br>
		<textarea name="detail" rows="7" cols="70" ><c:out value="${event.detail}" /></textarea><br>
		<a href="javascript:editLink.submit()">保存</a>
	</form>

</div>
</body>
</html>