let replyService = (function() {
	function add(reply, callback) {


		$.ajax({
			type: 'post',
			url: contextPath + '/replies/new',
			data: JSON.stringify(reply),
			contentType: 'application/json; charset=utf-8',
			success: function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}
	//댓글 목록
	function getList(param, callback, error) {
		let bno = param.bno;
		let page = param.page || 1;

		let url = contextPath + '/replies/pages/' + bno + '/' + page;
		let success = function(data) {
			if (callback) { callback(data) }
		}

		$.getJSON(url, success).fail(function(xhr, status, err) {
			if (error) { error(err) }

		});
	}
	
	//댓글 삭제
	function remove(rno, callback, error) {

		$.ajax({
			type: 'delete',
			url: contextPath + '/replies/'+rno,
			
			success: function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}

	//댓글 수정
	function update(reply, callback, error) {


		$.ajax({
			type: 'put',
			url: contextPath + '/replies/'+ reply.rno,
			data: JSON.stringify(reply),
			contentType: 'application/json; charset=utf-8',
			success: function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}
	
	return {

	add: add,
	getList: getList,
	remove : remove,
	update : update

}
	/*return {"객체이름(property)" : "객체(value)"}*/
}) ();


console.log(replyService);

//controller랑 비슷하게 구성 자바스크립트

