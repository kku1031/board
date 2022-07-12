<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jspf"%>
<div class="container">
	<c:if test="${not empty member}">
		${member}
	</c:if>
</div>
<%@include file="../layout/footer.jspf"%>
