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

	<!-- 新規作成リンク -->
	<form class="postForm" name="createLink" action="StudentServlet" method="post" >
		<input type="hidden" name="process" value="showCreatePage">
		<a href="javascript:createLink.submit()">新規作成</a>
	</form>

	<!-- 生徒一覧 -->
	<table class="simpleTable">
		<tr><th>名前</th><th>学年</th><th>性別</th><th>学校</th><th>偏差値</th></tr>
		<c:forEach var="student" items="${studentList}">
			<tr>
				<td>
					<form method="post" name="editLink${student.id}" action="StudentServlet">
						<input type="hidden" name="process" value="showEditPage">
						<input type="hidden" name="id" value="${student.id}">
						<a href="javascript:editLink${student.id}.submit()"><c:out value="${student.name}" /></a>
					</form>
				</td>
				<td><c:out value="${student.grade}" />年</td>
				<c:choose>
					<c:when test="${student.gender == 1}">
						<td>男</td>
					</c:when>
					<c:otherwise>
						<td>女</td>
					</c:otherwise>
				</c:choose>
				<td><c:out value="${student.school}" />高校</td>
				<td><c:out value="${student.deviationValue}" /></td>
			</tr>
		</c:forEach>
	</table>

</div>
</body>
</html>