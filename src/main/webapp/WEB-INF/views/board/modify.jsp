<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jspf" %>
<div class="container">
<h2>수정 페이지</h2>
<form action="modify" method="post" id="modifyForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<input type="hidden" name="bno" value="${board.bno}">
	게시물 번호 : ${board.bno} <br>
	제목 : <input type="text" name="title" value="${board.title}"><br>
	내용 : 
	<textarea rows="" cols="" name="content">${board.content}</textarea><br>
	작성자 : ${board.writer} <br> 
	<button>수정하기</button>
</form>

<div class="row my-5">
	<div class="col-lg-12">
		<div class="card">
			<div class="card-header">
				<h4>파일 첨부</h4>
			</div>
			<div class="card-body">
				<div class="uploadDiv">
					<input type="file" name="uploadFile" multiple="multiple">
				</div>
				<div class="uploadResult">
					<ul class="list-group"></ul>
				</div>
			</div> <!-- panel-body -->
		</div> <!-- panel end -->
	</div> <!-- col end -->
</div><!-- row end -->
</div> <!-- conainer end -->

<%@ include file="../layout/footer.jspf" %>

<script>
function showUploadResult(uploadResultArr){
	if(!uploadResultArr || uploadResultArr.length == 0) return;
	let str = "";
	$(uploadResultArr).each(function(i,obj){
		if(!obj.fileType){ // 이미지가 아닌 경우 
			let fileCellPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_"+obj.fileName);
			
			str+= "<li class='list-group-item' data-path='"+obj.uploadPath+"' ";
			str+= "data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
			str+= "<img src='"+contextPath+"/resources/img/attach.png' style='width:50px;' >"
			str+= "<span>"+obj.fileName+"</span>"
			str+= "<div class='d-flex justify-content-end'><span data-file='"+fileCellPath+"' data-type='file'>삭제</span></div>"
			str+="</li>"
		} else{ // 이미지인 경우
			let fileCellPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_"+obj.fileName);
			let originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
			originPath = originPath.replace(new RegExp(/\\/g),"/");
			
			str+= "<li class='list-group-item' data-path='"+obj.uploadPath+"' ";
			str+= "data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
			str += "<img src='"+contextPath+"/display?fileName="+fileCellPath+"'>";
			str+= "<span>"+obj.fileName+"</span>"
			str+= "<div class='d-flex justify-content-end'><span data-file='"+fileCellPath+"' data-type='image'>삭제</span></div>"
			str += "</li>" 
		}
	})
	$('.uploadResult ul').append(str);
}

let regex = new RegExp("(.*?)\.(exe|sh|js|alz)$")
let maxSize = 5242880;

function checkExtension(fileName, fileSize){
	if(fileSize >= maxSize ){
		alert('파일 사이즈 초과');
		return false; 
	}
	if(regex.test(fileName)){
		alert('허용되지 않는 확장자')
		return false; 
	}
	return true; 
}

$(function(){
	let bnoValue = "${board.bno}";
	$.getJSON(contextPath+"/board/getAttachList", {bno : bnoValue}, function(attachList){
		showUploadResult(attachList);
	}) // $.getJSON end
	
	$('.uploadResult ul').on('click','span',function(){
		let targetLi = $(this).closest('li');
		targetLi.remove(); 
		
	}) // delete event end
	
	// 파일업로드 
	$('input[type="file"]').change(function(){
		let formData = new FormData(); 
		let inputFiles = $('input[name="uploadFile"]');
		let files = inputFiles[0].files;
		
		for(let f of files){
			if(!checkExtension(f.name, f.size)) return false; 
			formData.append('uploadFile', f);
		}
		
		$.ajax({
			url : contextPath + '/uploadAjaxAction', 
			type : 'post', 
			processData : false, 
			contentType : false, 
			data : formData, 
			dataType : 'json', 
			success : function(result){
				showUploadResult(result);
			}
			
		});
	}) // change event end
	
	let modifyForm = $('#modifyForm');
	let modifyBtn =  $('#modifyForm button');
	modifyBtn.on('click',function(e){
		e.preventDefault();
		// console.log('기본동작금지');
		let str = "";
		$('.uploadResult ul li').each(function(i,obj){
			let jobj = $(obj);
			str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data('filename')+"'>";
			str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data('uuid')+"'>";
			str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data('path')+"'>";
			str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data('type')+"'>";
		});
		modifyForm.append(str).submit();
	})
	
})
0
	

</script>

<%@ include file="../layout/footer.jspf"%>