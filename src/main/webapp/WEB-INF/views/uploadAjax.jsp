<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value='${pageContext.request.contextPath}'></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	referrerpolicy="no-referrer"></script>

</head>
<body>
	<h2>파일업로드 : AJAX</h2>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple="multiple">
	</div>
	<button id=uploadBtn>업로드</button>
	
<div class="uploadResult">
	<ul></ul>
</div>	


</body>
<script>
$(function () {
	let contextPath = '${pageContext.request.contextPath}';
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
	
	
	let cloneObj = $(".uploadDiv").clone();
	
	
	$('#uploadBtn').on('click',function(){
		let formData = new FormData();
		
		let inputFile = $('input[name="uploadFile"]');
		let files = inputFile[0].files
		console.log(files);
		
		for(let i=0; i<files.length; i++){
			if(!checkExtension(files[i].name, files[i].size)) return;
			formData.append("uploadFile", files[i])
		}
		$.ajax({
			url : contextPath + '/uploadAjaxAction',
			processData : false,
			contentType : false,
			data : formData,
			type : 'post',
			success : function(result){
				alert("Uploaded");
				console.log(result)
				$(".uploadDiv").html(cloneObj.html());	
				showUploadFile(result);
			}
		})
	})
	
	let uploadResult = $('.uploadResult ul');
	function showUploadFile(uploadResultArr){
	let str = "";
	$(uploadResultArr).each(function(i,obj){
		
		if(!obj.image){ //이미지가 아닌 경우
			str+="<li><img src='${contextPath}/resources/img/attach.png' style='width:50px;'></li>"
		} else { //이미지인 경우
						
			let fileCellPath = encodeURIComponent(obj.uploadPath + "/S_" + obj.uuid + "_" +obj.fileName);
			str+= "<li><img src='${contextPath}/display?fileName="+fileCellPath+"'></li>";
			
		}
		
	})
	uploadResult.append(str);
}
	
})

</script>
</html>