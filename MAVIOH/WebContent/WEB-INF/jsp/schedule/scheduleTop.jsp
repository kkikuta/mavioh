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

	<div class="postForm">
		<!-- 表示中の月 -->
		<c:out value="${scheduleInformation.year}" />年
		<c:out value="${scheduleInformation.month}" />月

		<!-- 月の変更フォーム -->
		<form action="ScheduleServlet" method="post">
			<input type="hidden" name="process" value="changeMonth">
			<!-- 年 -->
			<select name="year">
				<% for (int i = 2020; i < 2023; i++) { %>
					<option value="<%= i %>"><%= i %></option>
				<% } %>
			</select>年
			<!-- 月 -->
			<select name="month">
				<% for (int i = 1; i < 13; i++) { %>
					<option value="<%= i %>"><%= i %></option>
				<% } %>
			</select>月
			<input type="submit" value="表示">
		</form>

		<!-- 新規作成リンク -->
		<form method="post" name="createLink" action="ScheduleServlet">
			<input type="hidden" name="process" value="showCreatePage">
			<input type="hidden" name="id" value="${event.id}">
			<a href="javascript:createLink.submit()">イベントを新規作成</a>
		</form>
	</div>

	<!-- 月間カレンダー -->
	<table class="simpleTable">
		<c:forEach var="oneDaySchedule" items="${monthlySchedule}">
			<tr>
				<!-- 日にち -->
				<td><c:out value="${oneDaySchedule.date}" /></td>
				<!-- 曜日 -->
				<td><c:out value="${oneDaySchedule.dayOfWeek}" /></td>
				<!-- イベント -->
				<td>
					<c:forEach var="event" items="${oneDaySchedule.eventList}">
						<form method="post" name="detailLink${event.id}" action="ScheduleServlet">
							<input type="hidden" name="process" value="showDetail">
							<input type="hidden" name="id" value="${event.id}">
							<a href="javascript:detailLink${event.id}.submit()"><c:out value="${event.title}" /></a>
						</form>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>

</div>
</body>
</html>