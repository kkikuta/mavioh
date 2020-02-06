<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="WEB-INF/jsp/base/head.jsp" />
<link rel="stylesheet" href="css/index.css">
</head>
<body>
<jsp:include page="WEB-INF/jsp/base/header.jsp" />
<div class="mainContent">

	<!-- ログインフォーム -->
	<div class="loginFormOuter">
		<form class="loginFormInner" action="LoginServlet" method="post">
			<jsp:include page="WEB-INF/jsp/base/errorInformation.jsp" />
			ユーザー名 <input type="text" name="userName"><br>
			パスワード <input type="password" name="password"><br>
			<input type="submit" value="ログイン">
		</form>
	</div>

</div>
</body>
</html>
