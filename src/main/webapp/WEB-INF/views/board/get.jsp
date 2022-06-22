<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jspf"%>
<script src="${contextPath}/resources/js/get.js"></script>
<div class="container">
	<div class="getData">
		<input type="hidden" name="page" id="page" value="${param.page}">
		<input type="hidden" name="type" id="type" value="${param.type}">
		<input type="hidden" name="keyword" id="keyword"
			value="${param.keyword}">
	</div>
	<form id="getForm">
		<input type="hidden" name="bno" value="${board.bno}">
		<div>
			<h3>${board.title}</h3>
			<p>작성자 : ${board.writer}</p>
			<p>
				등록일 :
				<fmt:parseDate var="regDate" value="${board.regDate}"
					pattern="yyyy-MM-dd'T'HH:mm:ss" />
				<fmt:formatDate value="${regDate}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				<br> 수정일 :
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
	
	<!-- 댓글 -->
	<div class="row">
		<div class="col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>댓글 달아주세요</h4>
				</div>
				<div class="panel-body">
					<ul class="chat">
						<li data-rno='1'>
							<div>
								<div class='header'>
									<strong class= 'primary-font'>홍길동</strong>
									<small class='pull-right text-muted'>2022-02-22</small>
								</div>
								<p>	댓글 내용 </p>
							</div>
						</li>
					</ul>
				</div> 
			</div><!-- pannel end -->
		</div><!-- col end -->
	</div><!-- row end -->
</div><!-- container end -->

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
		getForm.attr("method", "post");
		getForm.attr("action","remove")
		getForm.submit();
	});
});

	

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