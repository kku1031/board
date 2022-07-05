<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jspf"%>

<div class="container">
<form action="register" method="post" id="registerForm">
	제목 : <input type="text" name="title" ><br>
	내용 :
	<textarea rows="20" cols="50" name="content"></textarea><br>
	작성자 : <input type="text" name="writer" ><br>
	<button>글쓰기</button>
		
</form>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>파일 첨부</h4>
			</div>
			<div class="panel-body">
				<div class="form-group uploadDiv">
					<input type="file" name="uploadFile" multiple="multiple">
				</div>
				<div class="uploadResult">
					<ul></ul>
				</div>
			</div> <!-- panel-body -->
		</div>	<!--  panel end -->	
	</div>	<!-- col end -->
</div>	<!--  row end -->

<input type="checkbox" id="test"> 테스트


</div><!-- container end -->

<script>
let regex = new RegExp("(.*?)\.(exe|sh|js|alz)$")
let maxSize = 5242880;

function checkExtension(fileName, fileSize){
	if(fileSize >= maxSize){
		alert('파일 사이즈 초과');
		return false;
	}
	if(regex.test(fileName)){
		alert('허용되지 않는 확장자')
		return false;
	}
	return true;
}

let uploadResult = $('.uploadResult ul');
function showUploadResult(uploadResultArr){
	
	if(!uploadResultArr || uploadResultArr.length == 0){
		return;
	}
	
	let str = "";
	$(uploadResultArr).each(function(i,obj){
		if(!obj.fileType){ //이미지가 아닌 경우			
			let fileCellPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" +obj.fileName);
			
			str+="<li class='list-group-item' data-path='"+obj.uploadPath+"'"
			str+="data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>"
			str+="<img src='${contextPath}/resources/img/attach.png' style='width:50px;'>"
			str+="<a href='${contextPath}/download?fileName="+fileCellPath+"'>"+obj.fileName+"</a>"
			str+= "<span data-file='"+fileCellPath+"'' data-type='file'>삭제</span>"		
			str+="</li>"
		} else { //이미지인 경우		
			let fileCellPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" +obj.fileName);
			let originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
			originPath = originPath.replace(new RegExp(/\\/g),"/");
			
			str+="<li class='list-group-item' data-path='"+obj.uploadPath+"'"
			str+="data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>"
			str+= "<img src='${contextPath}/display?fileName="+fileCellPath+"'>";
			str+= "<a href='javascript:showImage(\""+originPath+"\")'>이미지원본보기</a>";
			str+= "<br><span data-file='"+fileCellPath+"'' data-type='image'>삭제</span>"		
			str+= "</li>"
		}		
	})
	uploadResult.append(str);
}	
	
$(function(){
	let form = $('#registerForm');
	let submitBtn = $('#registerForm button');
	
	//글쓰기 처리
	submitBtn.on('click',function(e){
		e.preventDefault();
		let str = "";
		$('.uploadResult li').each(function(i,obj){
			let jobj = $(obj);
			//console.log(jobj);
			//console.log(jobj.data('filename'));
			
			str +="<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data('filename')+"'>";
			str +="<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data('uuid')+"'>";
			str +="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data('path')+"'>";
			str +="<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data('type')+"'>";
		})
		form.append(str).submit();
	})
	
	//파일업로드
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
				showUploadResult(result)
			}
		});
	})	
	
	$('.uploadResult ul').on('click','span',function(){
		let targetFile = $(this).data('file');
		let type = $(this).data('type');
		let targetLi = $(this).closest('li');

		$.ajax({
			url : contextPath + '/deleteFile',
			type : 'post',
			data : {fileName : targetFile, type : type},
			dataType : 'text',
			success : function(result){
				alert(result);
				targetLi.remove();
			}
		})
	})
	
	
 	let checked = false;
	
 	$('#test').on('change',function(){
		if(!checked){
			checked = true;
			alert('체크')
		} else {
			checked = false;
			alert('체크 해제')
		}
	})	 
	
}) // document ready end

</script>

<%@ include file="../layout/footer.jspf"%>