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

	<!-- 生徒フォームを表示 -->
	<form class="studentForm" action="StudentServlet" method="post">
		<input type="hidden" name="process" value="executeCreate">

		<!-- 名前 -->
		名前:
		<input type="text" name="name"><br>

		<!-- 学年の選択肢を表示 -->
		<select name="grade">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>年

		<!-- 性別の選択肢を表示 -->
		<select name="gender">
			<option value="1">男</option>
			<option value="2">女</option>
		</select>

		<!-- 高校名 -->
		<input type="text" name="school">高校<br>

		<!-- 偏差値 -->
		偏差値:
		<input type="text" name="deviationValue"><br>

		<!-- 更新ボタン -->
		<input type="submit" value="更新">
	</form>

</div>
</body>
</html>