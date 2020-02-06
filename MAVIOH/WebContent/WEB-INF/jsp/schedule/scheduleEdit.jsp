<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.OneDaySchedule" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../base/head.jsp"/>
<link rel="stylesheet" href="css/schedule.css">
</head>
<body>
<jsp:include page="../base/header.jsp" />
<div class="mainContent">

	<!-- メインのリンク -->
	<jsp:include page="../base/mainLinks.jsp" />

	<!-- イベントフォームを表示 -->
	<form class="scheduleForm" action="ScheduleServlet" method="post">
		<input type="hidden" name="process" value="executeEdit">
		<input type="hidden" name="date" value="${oldEvent.date}">

		<!-- イベント内容の入力欄の表示 -->
		タイトル <input type="text" name="title" value="${oldEvent.title}"><br>
		詳細<br>
		<textarea name="detail" cols="70" rows="7">${oldEvent.detail}</textarea><br>
		<input type="submit" value="更新">
	</form>

	<!-- 削除フォーム -->
	<form class="scheduleForm" action="ScheduleServlet" method="post">
		<input type="hidden" name="process" value="executeDelete">
		<input type="hidden" name="id" value="${oldEvent.id}">
		<input type="submit" value="削除">
	</form>

</div>
</body>
</html>