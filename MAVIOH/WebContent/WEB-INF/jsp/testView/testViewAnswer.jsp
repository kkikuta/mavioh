<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../base/head.jsp"/>
</head>
<body>
<h1>英単語テスト解答</h1>
<div class="mainContent">

	<!-- メインのリンク -->
	<jsp:include page="../base/mainLinks.jsp" />

	<!-- テストを表示 -->
	<table>
		<c:forEach var="wordData" items="${wordDataList}">
			<tr>
				<td><c:out value="${wordData.number}" /></td>
				<td><c:out value="${wordData.word}" /></td>
				<td><c:out value="${wordData.meaning}" /></td>
			</tr>
		</c:forEach>
	</table>

	<!-- 問題へのリンク -->
	<form action="TestViewServlet" method="post">
		<input type="hidden" name="process" value="showQuestion">
		<input type="submit" value="問題へ">
	</form>

	<!-- 戻るリンク -->
	<a href="TestViewServlet">戻る</a>

</div>
</body>
</html>