<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../base/head.jsp" />
<link rel="stylesheet" href="css/error.css">
</head>
<body>
<jsp:include page="../base/header.jsp" />
<div class="mainContent">

	<!-- エラーメッセージ -->
	<div class="errorBoxOuter">
		<div class="errorBoxInner">
			<div class="errorInformation">
				エラーが発生しました。もう一度ログインしてください。
			</div>
			<a href="LogoutServlet">戻る</a>
		</div>
	</div>

</div>
</body>
</html>
