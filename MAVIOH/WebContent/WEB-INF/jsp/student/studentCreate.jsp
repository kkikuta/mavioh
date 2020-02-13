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
	<form class="postForm" action="StudentServlet" method="post">
		<jsp:include page="../error/errorInformation.jsp" />
		<input type="hidden" name="process" value="executeCreate">
		名前:
		<input type="text" name="name"><br>

		<select name="grade">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>年

		<select name="gender">
			<option value="1">男</option>
			<option value="2">女</option>
		</select>

		<input type="text" name="school">高校<br>

		偏差値:
		<input type="text" name="deviationValue"><br>

		<input type="submit" value="上記の内容で保存する">
	</form>

</div>
</body>
</html>