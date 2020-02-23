/* 		detail.jsp 페이지 내에서 발생하는 함수 정의! 	 */
function linkToCommentsDetail() {
	location.href = `review?display_info_id=${getParams(document.location.href).display_info_id}`;
};

function linkToBookingPage() {
	location.href = `reserve?id=${getParams(document.location.href).display_info_id}`;
};


/* 		detail.jsp 페이지 내에서 발생하는 이벤트 정의! 	 */
document.addEventListener("DOMContentLoaded", function() {
	product_detail.setGetUrlByDisplayInfoId(getParams(document.location.href).display_info_id);
	product_detail.sendGetAjax();
	drawMyEmail();
});