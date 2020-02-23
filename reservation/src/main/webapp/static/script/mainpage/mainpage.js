/* 		클라이언트 첫 로드 시의 발생 이벤트! 		*/
document.addEventListener("DOMContentLoaded", function() {	
	promotion.sendGetAjax();
	category.sendGetAjax();
	product.sendGetAjax();
	drawMyEmail();
});