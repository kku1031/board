<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jspf"%>

<div class="container">
<form action="register" method="post">
	제목 : <input type="text" name="title" ><br>
	내용 :
	<textarea rows="20" cols="50" name="content"></textarea><br>
	작성자 : <input type="text" name="writer" ><br>
	<button>글쓰기</button>
</form>
</div>
<%@ include file="../layout/footer.jspf"%>