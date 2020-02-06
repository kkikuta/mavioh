<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.OneDaySchedule, beans.ErrorInformation" %>
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
		<input type="hidden" name="process" value="executeCreate">

		<!-- 年の選択肢を表示 -->
		<select name="year">
			<% for (int i = 2020; i < 2023; i++) { %>
				<option value="<%= i %>"><%= i %></option>
			<% } %>
		</select>年

		<!-- 月の選択肢を表示 -->
		<select name="month">
		<% for (int i = 1; i < 13; i++) { %>
			<option value="<%= i %>"><%= i %></option>
		<% } %>
		</select>月

		<!-- 日にちの選択肢を表示 -->
		<select name="date">
			<% for (int i = 1; i < 32; i++) { %>
				<option value="<%= i %>"><%= i %></option>
			<% } %>
		</select>日
		<br>

		<!-- イベント内容の入力欄の表示 -->
		タイトル <input type="text" name="title"><br>
		詳細<br>
		<textarea name="detail" cols="70" rows="7"></textarea><br>
		<input type="submit" value="更新">
	</form>

</div>
</body>
</html>