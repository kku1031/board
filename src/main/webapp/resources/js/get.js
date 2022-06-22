$(function() {
	let bnoValue = $('input[name="bno"]').val();
	let replyUL = $('.chat');

	function showList(page) {
		replyService.getList({ bno: bnoValue, page: page }, function(list) {
			let str = "";
			for (let r of list) {
				str += `
   				<li data-rno='1'>
        				<div>
           					<div class='header'>
                			<strong class= 'primary-font'>${r.replyer}</strong>
                			<small class='pull-right text-method'>${displayTime(r.regDate)}</small>
            			</div>
           	 				<p>${r.reply}</p>
        </div>
    </li>`
			}
			replyUL.html(str);
		});
	}
	showList(1);

	function displayTime(timeValue) {
		/*let test = "abcdefg";
		console.log(test.substr(1));*/
		let timeArr = JSON.stringify(timeValue).substr(1).split(",");
		 return `${timeArr[0]}년 ${timeArr[1]}월 ${timeArr[2]}일`;
	}
	
})


