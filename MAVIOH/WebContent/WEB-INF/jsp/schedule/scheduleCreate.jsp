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

	<!-- 新規作成フォーム -->
	<form class="postForm" name="createLink" action="ScheduleServlet" method="post" >
		<jsp:include page="../error/errorInformation.jsp" />
		<input type="hidden" name="process" value="executeCreate">
		<input type="hidden" name="id" value="${event.id}">
		<select name="year">
			<% for (int i = 2020; i < 2023; i++) { %>
				<option value="<%= i %>"><%= i %></option>
			<% } %>
		</select>年
		<select name="month">
			<% for (int i = 1; i < 13; i++) { %>
				<option value="<%= i %>"><%= i %></option>
			<% } %>
		</select>月
		<input type="text" name="date">日<br>
		タイトル<input type="text" name="title"><br>
		詳細<br>
		<textarea name="detail" rows="7" cols="70" ></textarea><br>
		<a href="javascript:createLink.submit()">保存</a>
	</form>

</div>
</body>
</html>