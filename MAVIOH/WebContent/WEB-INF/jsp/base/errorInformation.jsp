<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.ErrorInformation" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- エラーメッセージがあれば表示 -->
<div class="errorInformation">
	<c:if test="${not empty errorInformation}">
		<c:forEach var="message" items="${errorInformation.messages}">
			・${message}<br>
		</c:forEach>
	</c:if>
</div>
