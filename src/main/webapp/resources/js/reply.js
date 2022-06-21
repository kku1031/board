let replyService = (function(){
	function add(reply, callback){
		
		
		$.ajax ({						
			type : 'post',
			url : contextPath + '/replies/new',
			data : JSON.stringify(reply),
			contentType : 'application/json; charset=utf-8',
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});		
	}	
	return {add : add}	
	/*return {"객체이름(property)" : "객체(value)"}*/
})();

console.log(replyService);

//controller랑 비슷하게 구성 자바스크립트