<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jspf"%>
<div class="container">
<sec:authentication property="principal.memberVO.userId" var="userId"/>
	<h2>회원 페이지</h2>
	<a href="${contextPath}/customLogout">로그아웃하러가자</a><br>
	<a href="${contextPath}/anno/myPage/${userId}">마이페이지</a><br>
	${userId}
</div>
<%@include file="../layout/footer.jspf"%>
