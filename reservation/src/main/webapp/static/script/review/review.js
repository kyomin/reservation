var params = getParams(document.location.href);

let reviews = {
		/* 		Variables	 */
		method : "GET",
		url : '',
		
		/* 		Functions	 */
		handleResponse : function(jsonResponse) {
			//	리뷰 상세 페이지를 이루는 각 데이터 셋팅!
			average_score.setAverageScore(jsonResponse.averageScore);
			comments.setComments(jsonResponse.comments);
			display_info.setDisplayInfo(jsonResponse.displayInfo);
			
			//	셋팅된 데이터를 각각 객체가 처리하도록 위임!
			comments.handleData();
			average_score.handleData();
			display_info.handleData();
		},
		
		setUrlByDisplayInfoId : function(displayInfoId) {
			this.url = `api/products/${displayInfoId}`;
		}
}

//	make ajax function for this data
const sendAjaxForReviewDetail = ajax.bind(reviews);

document.addEventListener("DOMContentLoaded", function() {	
	reviews.setUrlByDisplayInfoId(params.display_info_id);
	sendAjaxForReviewDetail();
});