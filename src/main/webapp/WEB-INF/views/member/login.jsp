<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jspf"%>
<div class="container">
	<div class="loginForm my-5">
		<h2>로그인 페이지</h2>
		<div class="error">
			<p>${error}</p>
		</div>
		<form action="${contextPath}/member/login" method="post">
			<div class="form-group">
				<input type="text" name="loginId" placeholder="아이디" class="form-control">
			</div>
			<div class="form-group">
				<input type="text" name="loginPw" placeholder="비밀번호" class="form-control">
			</div>
			<div class="form-group">
				<label for="remember-me">Remember Me</label>
				<input type="checkbox" name="remember-me" id="remember-me" class="form-control">
			</div>
			<div class="form-group">				
				<button class="btn btn-primary form-control">로그인</button>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		</form>	
	</div>
</div>
<%@include file="../layout/footer.jspf"%>
<style>
	.loginForm {width:400px; margin:auto;}
</style>