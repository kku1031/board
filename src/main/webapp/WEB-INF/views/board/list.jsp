<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../layout/header.jspf"%>
<div class="container">
	<h2>자유게시판</h2>	
	<a href="register">글쓰기</a>
	<table class="table">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>수정일</th>			
		</tr>
		<c:forEach items="${list}" var="b">
		<tr>
			<td>${b.bno}</td>
			<td>			
			<a href="${contextPath}/board/get?bno=${b.bno}">${b.title}</a>
			</td>
			<td>${b.writer}</td>
			<td>
				<fmt:parseDate var="regDate" value="${b.regDate}" pattern="YYYY-MM-dd'T'HH:mm:ss"/>
				<fmt:formatDate value="${regDate}" pattern="yyyy년 mm월 dd일 HH시 mm분"/>
			</td>
			<td>
				<fmt:parseDate var="updateDate" value="${b.updateDate}" pattern="YYYY-MM-dd'T'HH:mm:ss"/>
				<fmt:formatDate value="${updateDate}" pattern="yyyy년 mm월 dd일 HH시 mm분"/>
			</td>
		</tr>	
		</c:forEach>
	</table>
	<c:if test="${pageMaker.prev}">
		<a href="?page=${pageMaker.startPage - 1}">[이전페이지]</a>
	</c:if>
	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
		<a href="?page=${pageNum}">[${pageNum}]</a>
	</c:forEach>	
	<c:if test="${pageMaker.next}">
		<a href="?page=${pageMaker.endPage + 1}">[다음페이지]</a>
	</c:if>		
	
</div>

<%@include file="../layout/footer.jspf"%>