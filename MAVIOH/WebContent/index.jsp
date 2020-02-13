<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="WEB-INF/jsp/layout/head.jsp" />
<link rel="stylesheet" href="css/common.css">
</head>
<body>
<jsp:include page="WEB-INF/jsp/layout/header.jsp" />
<div class="mainContent">

	<!-- ログインフォーム -->
	<div class="centerBoxOuter">
		<form class="centerBoxInner" action="LoginServlet" method="post">
			<jsp:include page="WEB-INF/jsp/error/errorInformation.jsp" />
			ユーザー名 <input type="text" name="userName"><br>
			パスワード <input type="password" name="password"><br>
			<input type="submit" value="ログイン">
		</form>
	</div>

</div>
</body>
</html>
