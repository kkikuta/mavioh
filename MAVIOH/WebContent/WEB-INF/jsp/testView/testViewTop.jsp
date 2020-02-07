<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../base/head.jsp"/>
<link rel="stylesheet" href="css/testView.css">
</head>
<body>
<jsp:include page="../base/header.jsp" />
<div class="mainContent">

	<!-- メインのリンク -->
	<jsp:include page="../base/mainLinks.jsp" />

	<!-- テスト作成フォームを表示 -->
	<form class="testForm" action="TestViewServlet" method="post">
		テストする範囲<br>
		<input type="hidden" name="process" value="showTest">
		<input type="text" name="startPosition">番から<br>
		<input type="text" name="endPosition">番まで<br>
		<input type="submit" value="作成">
	</form>

</div>
</body>
</html>