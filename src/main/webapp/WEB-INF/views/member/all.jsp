<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jspf"%>

<div class="container">
<sec:authorize access="isAnonymous()">
	<a href="${contextPath}/customLogin">로그인</a>	
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.memberVO" var="member"/>
	${member.userName}님 로그인 중 ..<br>
	<form action="${contextPath}/customLogout" method="post">
		<button class="btn btn-primary">로그아웃</button>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	</form>	
</sec:authorize>
</div>
<%@include file="../layout/footer.jspf"%>
