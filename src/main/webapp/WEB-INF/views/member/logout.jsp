<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jspf"%>
<div class="contatiner">
	<h2>로그아웃 페이지</h2>
	<form action="${contextPath}/customLogout" method="post">
		<button class="btn btn-primary">로그아웃</button>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	</form>
</div>
<%@include file="../layout/footer.jspf"%>