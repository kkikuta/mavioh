<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../base/head.jsp"/>
<link rel="stylesheet" href="css/student.css">
</head>
<body>
<jsp:include page="../base/header.jsp" />
<div class="mainContent">

	<!-- メインのリンク -->
	<jsp:include page="../base/mainLinks.jsp" />

	<!-- 生徒フォーム -->
	<form class="studentForm" action="StudentServlet" method="post">
		<input type="hidden" name="process" value="executeEdit">
		<input type="hidden" name="id" value="${student.id}">

		<!-- 名前 -->
		名前:
		<input type="text" name="name" value="${student.name}"><br>

		<!-- 学年 -->
		<select name="grade">
			<c:if test="${student.grade == 1}">
				<option value="1" selected>1</option>
				<option value="2">2</option>
				<option value="3">3</option>
			</c:if>
			<c:if test="${student.grade == 2}">
				<option value="1">1</option>
				<option value="2" selected>2</option>
				<option value="3">3</option>
			</c:if>
			<c:if test="${student.grade == 3}">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3" selected>3</option>
			</c:if>
		</select>年

		<!-- 性別 -->
		<select name="gender">
			<c:choose>
				<c:when test="${student.gender == 1}">
					<option value="1" selected>男</option>
					<option value="2">女</option>
				</c:when>
				<c:otherwise>
					<option value="1">男</option>
					<option value="2" selected>女</option>
				</c:otherwise>
			</c:choose>
		</select>

		<!-- 高校名 -->
		<input type="text" name="school" value="${student.school}">高校<br>

		<!-- 偏差値 -->
		偏差値:
		<input type="text" name="deviationValue" value="${student.deviationValue}"><br>

		<!-- 更新ボタン -->
		<input type="submit" value="更新">
	</form>

	<!-- 削除フォーム -->
	<form class="studentForm" action="StudentServlet" method="post">
		<input type="hidden" name="process" value="executeDelete">
		<input type="hidden" name="id" value="${student.id}">
		<input type="submit" value="削除">
	</form>

</div>
</body>
</html>