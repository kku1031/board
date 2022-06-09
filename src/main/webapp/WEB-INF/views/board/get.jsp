<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jspf"%>
<div class="container">
   <form id="getForm">
      <input type="hidden" name="bno" value="${board.bno}">      
      <div>
         <h3>${board.title}</h3>
         <p>작성자 : ${board.writer}</p>
         <p>
            등록일 :
            <fmt:parseDate var="regDate" value="${board.regDate}"
               pattern="yyyy-MM-dd'T'HH:mm:ss" />
            <fmt:formatDate value="${regDate}" pattern="yyyy년 MM월 dd일 HH시 mm분" /><br>
            수정일 :
            <fmt:parseDate var="updateDate" value="${board.updateDate}"
               pattern="yyyy-MM-dd'T'HH:mm:ss" />
            <fmt:formatDate value="${updateDate}"
               pattern="yyyy년 MM월 dd일 HH시 mm분" />
         </p>
         <p>${board.content }</p>
      </div>
      <button class="btn btn-warning modify">수정</button>
      <button class="btn btn-danger remove">삭제</button>
      <button class="btn btn-primary list">목록</button>
   </form>
</div>
<script>
$(function () {
	let getForm = $("#getForm"); //목록
	$('#getForm .list').on('click',function(){
		getForm.empty();
		getForm.attr("action","list");
		getForm.submit();
	
	})
	
	$('#getForm .modify').on('click',function(){ //수정페이지
		getForm.attr("action","modify");
		getForm.submit();
	
	})
	
	$('#getForm .remove'). on('click', function () { //삭제페이지
		getForm.attr("method", "post");
		getForm.attr("action","remove")
		getForm.submit();
	})
		
})

</script>
<%@ include file="../layout/footer.jspf"%>