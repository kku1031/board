<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jspf"%>
<script src="${contextPath}/resources/js/get.js"></script>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username" var="userId"/>
</sec:authorize>

<div class="container">
	<div class="getData">
		<input type="hidden" name="page" id="page" value="${param.page}">
		<input type="hidden" name="type" id="type" value="${param.type}">
		<input type="hidden" name="keyword" id="keyword" value="${param.keyword}">
		<input type="hidden" name="writer" id="writer" value="${board.writer}">
	</div>
	<form id="getForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="hidden" name="bno" value="${board.bno}">
		<div>
			<h3>${board.title}</h3>
			<p>작성자 : ${board.writer}</p>
			<p>
				등록일 :
				<fmt:parseDate var="regDate" value="${board.regDate}"
					pattern="yyyy-MM-dd'T'HH:mm:ss" />
				<fmt:formatDate value="${regDate}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				수정일 :
				<fmt:parseDate var="updateDate" value="${board.updateDate}"
					pattern="yyyy-MM-dd'T'HH:mm:ss" />
				<fmt:formatDate value="${updateDate}"
					pattern="yyyy년 MM월 dd일 HH시 mm분" />
				조회수 : ${board.viewCount}	
			</p>
			<p>${board.content}</p>
		</div>	
		<button class="btn btn-warning modify">수정</button>
			<button class="btn btn-danger remove">삭제</button>		
		<c:if test="${userId eq board.writer}">	
			
		</c:if>
		<button class="btn btn-primary list">목록</button>
	</form>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>파일 첨부</h4>
				</div>
				<div class="panel-body">					
					<div class="uploadResult">
						<ul></ul>
					</div>
				</div> <!-- panel-body -->
			</div>	<!--  panel end -->	
		</div>	<!-- col end -->
	</div>	<!--  row end -->


	<!-- 댓글 등록 -->
	<sec:authorize access="isAuthenticated()">
	<button id="addReplyBtn" type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#replyForm">댓글 등록
		</button>		
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
		댓글을 등록하시려면 로그인하세요
	</sec:authorize>
	<div>
		댓글 수 ${board.replyCnt}
	</div>

	<!-- 댓글 -->
	<div class="row">
		<div class="col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="test">댓글 달아주세요</h4>
				</div>
				<div class="panel-body">
					<ul class="chat"></ul>
				</div>
			</div>
			<!-- pannel end -->
		</div>
		<!-- col end -->
	</div>
	<!-- row end -->
</div>
<!-- container end -->

<!-- Modal -->
<div class="modal fade" id="replyForm" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLongTitle">댓글 달기</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="reply">내용입력</label>
					<input class="form-control" name="reply">
				</div>
				<div class="form-group">
					<label for="replyer">작성자</label>
					<input class="form-control" name="replyer" id="replyer">
				</div>
				<div class="form-group">
					<label for="regDate">등록일</label>
					<input class="form-control" name="regDate" id="regDate">
				</div>			
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-warning" id="modalModBtn">수정</button>
				<button type="button" class="btn btn-danger" id="modalRemoveBtn">삭제</button>
				<button type="button" class="btn btn-primary" id="modalRegisterBtn">등록</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
				
			</div>
		</div>
	</div>
</div>

<script>



$(function(){
	let getForm = $("#getForm"); //목록
	$('#getForm .list').on('click',function(){
		getForm.empty();
		getForm.append($('#page'));
		getForm.append($('#type'));
		getForm.append($('#keyword'));
		getForm.attr("action","list");
		getForm.submit();
	
	})
	
	$('#getForm .modify').on('click',function(){ //수정페이지
		getForm.attr("action","modify");
		getForm.submit();
	
	})
	
	$('#getForm .remove'). on('click', function () { //삭제페이지
		getForm.append($('#writer'))
		getForm.attr("method", "post");
		getForm.attr("action","remove");
		getForm.submit();
		
	});	

})	
	

// 테스트 
$(function(){
	// 목록 테스트 
	function listTest(){
		let bnoValue = $('input[name="bno"]').val();
		replyService.getList({bno:bnoValue},function(list){
			for(let reply of list){
				console.log(reply)
			}
		})
	}

	//수정테스트
	function updateTest(){
		replyService.update({
			rno : 7,
			bno : 1,
			reply : "수정 완료"
		},function(result){
			alert(result)
		})
	}
	// updateTest();
	function deleteTest(){
		replyService.remove(1, function(result){alert(result)}, function(){alert('실패함')});
	}
	// deleteTest(); 
	
	//댓글 등록 테스트
	
	function registerTest(){
		let bnoValue = $('input[name="bno"]').val();
		let reply = {
				bno : bnoValue,
				reply : "ajax 댓글 등록 테스트",
				replyer : "테스터"			
		};
		let callback = function(result){
			alert("결과 : " + result)
		}
		
		replyService.add(reply, callback);
	}
})


</script>

<%@ include file="../layout/footer.jspf"%>