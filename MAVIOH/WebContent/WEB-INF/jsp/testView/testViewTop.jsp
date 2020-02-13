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

	<!-- テスト作成フォーム -->
	<form class="postForm" action="TestViewServlet" method="post">
		<jsp:include page="../error/errorInformation.jsp" />
		テストする範囲<br>
		<input type="hidden" name="process" value="createTest">
		<input type="text" name="startPosition">番から<br>
		<input type="text" name="endPosition">番まで<br>
		<input type="submit" value="上記の範囲でテストを作成">
	</form>

</div>
</body>
</html>