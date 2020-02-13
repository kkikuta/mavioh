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

	<div class="eventBox">
		<!-- 表示中のイベントの日にち -->
		<c:out value="${event.date}" /><br>

		<!-- イベント詳細 -->
		<c:out value="${event.title}" /><br>
		<c:out value="${event.detail}" /><br>

		<!-- 編集リンク -->
		<form method="post" name="editLink" action="ScheduleServlet">
			<input type="hidden" name="process" value="showEditPage">
			<input type="hidden" name="id" value="${event.id}">
			<a href="javascript:editLink.submit()">編集</a>
		</form>

		<!-- 削除リンク -->
		<form method="post" name="deleteLink" action="ScheduleServlet">
			<input type="hidden" name="process" value="executeDelete">
			<input type="hidden" name="id" value="${event.id}">
			<a href="javascript:deleteLink.submit()">削除</a>
		</form>
	</div>

</div>
</body>
</html>