var params = getParams(document.location.href);

/* 		detail.jsp 페이지 내에서 발생하는 함수 정의! 	 */
function linkToCommentsDetail() {
	location.href = `review?display_info_id=${params.display_info_id}`;
}

document.addEventListener("DOMContentLoaded", function() {	
	product_detail.setUrlByDisplayInfoId(params.display_info_id);
	sendAjaxForProductDetail();
});