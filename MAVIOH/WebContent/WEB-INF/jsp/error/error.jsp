<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<jsp:include page="../layout/head.jsp"/>
<link rel="stylesheet" href="css/common.css">
</head>
<body>
<jsp:include page="../layout/header.jsp" />
<div class="mainContent">

	<!-- エラーメッセージ -->
	<div class="centerBoxOuter">
		<div class="centerBoxInner">
			<div class="errorMessage">エラーが発生しました。ログインし直してください。</div>
			<a href="index.jsp">ログイン画面へ</a>
		</div>
	</div>

</div>
</body>
</html>