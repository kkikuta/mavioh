<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../base/head.jsp"/>
<link rel="stylesheet" href="css/student.css">
</head>
<body>
<jsp:include page="../base/header.jsp" />
<div class="mainContent">

	<!-- メインのリンク -->
	<jsp:include page="../base/mainLinks.jsp" />

	<!-- 新規作成リンク -->
	<form class="createLink" method="post" name="createLink" action="StudentServlet">
		<input type="hidden" name="process" value="showCreatePage">
		<a href="javascript:createLink.submit()">新規作成</a>
	</form>

	<!-- 生徒一覧 -->
	<table class="studentTable">
		<tr><th>名前</th><th>学年</th><th>性別</th><th>学校</th><th>偏差値</th><th>編集</th></tr>
		<c:forEach var="student" items="${studentList}">
			<tr>
				<td><c:out value="${student.name}" /></td>
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
				<td>
					<!-- 編集リンク -->
					<form method="post" name="editLink${student.id}" action="StudentServlet">
						<input type="hidden" name="process" value="showEditPage">
						<input type="hidden" name="id" value="${student.id}">
						<a href="javascript:editLink${student.id}.submit()">編集</a>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

</div>
</body>
</html>