<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../layout/head.jsp" />
</head>
<body>
<div class="mainContent">

	<!-- テスト本体 -->
	<h1>英単語テスト</h1>

	<table>
		<c:forEach var="wordData" items="${wordDataList}">
			<tr>
				<td><c:out value="${wordData.number}" /></td>
				<td><c:out value="${wordData.word}" /></td>
				<td><c:out value="______________________" /></td>
			</tr>
		</c:forEach>
	</table>

	<!-- 解答へのリンク -->
	<form action="TestViewServlet" method="post">
		<input type="hidden" name="process" value="showAnswerPage">
		<input type="submit" value="解答へ">
	</form>

	<!-- 戻るリンク -->
	<a href="TestViewServlet">戻る</a>

</div>
</body>
</html>