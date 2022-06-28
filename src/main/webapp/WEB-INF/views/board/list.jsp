<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../layout/header.jspf"%>
<div class="container">
	<div class="listData">
		<input type="hidden" name="bno" id="bno" value="">
		<input type="hidden" name="page" id="page" value="${pageMaker.criteria.page}">
		<input type="hidden" name="type" id="type" value="${pageMaker.criteria.type}">
		<input type="hidden" name="keyword" id="keyword" value="${pageMaker.criteria.keyword}">
	</div>
	
	<form action="${contextPath}/board/list" id="listForm">
		<div>
			<select name="type">
				<option value="">===</option>
				<option value="T" ${pageMaker.criteria.type eq 'T' ? 'selected':''}>제목</option>
				<option value="C" ${pageMaker.criteria.type eq 'C' ? 'selected':''}>내용</option>
				<option value="W" ${pageMaker.criteria.type eq 'W' ? 'selected':''}>작성자</option>				
			</select>
			<input type="text" name="keyword" value="${pageMaker.criteria.keyword}">
			<button>검색</button>
		</div>
	</form>
	
	
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
			<a href="${b.bno}" class="get">
				${b.title}<b>[${b.replyCnt}]</b>
			</a>
			</td>
			<td>${b.writer}</td>
			<td>
				<fmt:parseDate var="regDate" value="${b.regDate}" pattern="YYYY-MM-dd'T'HH:mm:ss"/>
				<fmt:formatDate value="${regDate}" pattern="yyyy-mm-dd"/>
			</td>
			<td>
				<fmt:parseDate var="updateDate" value="${b.updateDate}" pattern="YYYY-MM-dd'T'HH:mm:ss"/>
				<fmt:formatDate value="${updateDate}" pattern="yyyy-mm-dd"/>
			</td>
		</tr>	
		</c:forEach>
	</table>
	<div class="pagination">
		<c:if test="${pageMaker.prev}">
			<a href="${pageMaker.startPage - 1}">[이전페이지]</a>
		</c:if>
		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
			<a href="${pageNum}" class="${pageMaker.criteria.page == pageNum ? 'on':''}">[${pageNum}]</a>
		</c:forEach>	
		<c:if test="${pageMaker.next}">
			<a href="${pageMaker.endPage + 1}">[다음페이지]</a>
		</c:if>
	</div>	
	
	
</div>

<%@include file="../layout/footer.jspf"%>


<script>
$(function () {
	let listForm = $('#listForm');
	$('.pagination a').on('click', function(e) {
		e.preventDefault();
		$('.listData').find('#page').val($(this).attr('href'));
		
		if(listForm.find('input[name="keyword"]').val()==''){
			listForm.find('input[name="keyword"]').remove();
			listForm.find('input[name="type"]').remove();
		}
		
		listForm.append($('#page'));
		listForm.submit();
	});
	
	$('.get').on('click',function(e){
		e.preventDefault();
		let bno = $(this).attr('href');
		$('#bno').val(bno);
		listForm.append($('#bno'));
		listForm.append($('#page'));
		listForm.attr("action","get");
		listForm.submit(); 
	})

})
</script>
<style>
.on {font-weight : 700; color: red}
</style>

