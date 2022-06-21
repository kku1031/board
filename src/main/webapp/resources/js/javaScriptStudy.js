console.log('하이');

//즉시 실행 함수
(function (){
	console.log("즉시 실행 함수 양식");
})();

//자바스크립트 객체 양식

var obj = {
	"bno" : "1",
	"reply" : "댓글",
	"replyer" : "작성자",
	add : function(){
		console.log("??????????!!!!!!!!!!")
	}
}

console.log(obj);

//객체 접근
console.log(obj.bno);
console.log(obj.reply);
console.log(obj.replyer);

//객체를 함수로 변환
function myObjFun(bno,reply,replyer){
	return{
		"bno" : bno,
		"reply" : reply,
		"replyer" : replyer
	};
}
let reply1 = myObjFun(10,"댓글","댓글러");
console.log(reply1);
