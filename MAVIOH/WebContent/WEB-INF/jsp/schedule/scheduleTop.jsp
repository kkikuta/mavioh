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

	<!-- 月間カレンダーを表示 -->
	<table class="monthlyCalendar">
		<c:forEach var="oneDaySchedule" items="${monthlySchedule}">
			<tr>
				<!-- 最初だけ、選択中の月と選択フォームを表示 -->
				<c:if test="${oneDaySchedule.date == 1}">
					<div class="changeMonthForm">
						<c:out value="${oneDaySchedule.year}" />年<c:out value="${oneDaySchedule.month}" />月
						<!-- 表示する月の選択フォーム -->
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
					</div>
				</c:if>
				<!-- 日にち -->
				<td><c:out value="${oneDaySchedule.date}" /></td>
				<!-- 曜日 -->
				<td><c:out value="${oneDaySchedule.dayOfWeek}" /></td>
				<!-- イベント -->
				<td>
					<c:if test="${not empty oneDaySchedule.event}">
						<c:out value="${oneDaySchedule.event.title}" />
					</c:if>
				</td>
				<!-- 新規作成リンクまたは編集・削除リンクを表示 -->
				<!-- 新規作成リンクを表示 -->
				<td>
					<c:choose>
						<c:when test="${empty oneDaySchedule.event}">
							<form method="post" name="createLink${oneDaySchedule.date}" action="ScheduleServlet">
								<input type="hidden" name="process" value="showCreatePage">
								<a href="javascript:createLink${oneDaySchedule.date}.submit()">新規作成</a>
							</form>
						</c:when>
						<c:otherwise>
							<form method="post" name="editLink${oneDaySchedule.event.id}" action="ScheduleServlet">
								<input type="hidden" name="process" value="showEditPage">
								<input type="hidden" name="eventId" value="${oneDaySchedule.event.id}">
								<a href="javascript:editLink${oneDaySchedule.event.id}.submit()">編集</a>
							</form>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>

</div>
</body>
</html>