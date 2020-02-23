let reviews = {
		/* 		Variables	 */
		method : "GET",
		getUrl : '',
		
		/* 		Functions	 */
		sendGetAjax : function() {
			var oReq = new XMLHttpRequest();
			
			oReq.addEventListener("load", function() {
				this.handleGetResponse(JSON.parse(oReq.responseText));
			}.bind(this));
			
			oReq.open("GET", this.getUrl);
			oReq.send();
		},
		
		handleGetResponse : function(jsonResponse) {
			//	리뷰 상세 페이지를 이루는 각 데이터 셋팅!
			average_score.setData(jsonResponse.averageScore);
			comments.setData(jsonResponse.comments);
			display_info.setData(jsonResponse.displayInfo);
			
			//	셋팅된 데이터를 각각 객체가 처리하도록 위임!
			comments.handleData();
			average_score.handleData();
			display_info.handleData();
		},
		
		setGetUrlByDisplayInfoId : function(displayInfoId) {
			this.getUrl = `api/products/${displayInfoId}`;
		}
};

document.addEventListener("DOMContentLoaded", function() {	
	reviews.setGetUrlByDisplayInfoId(getParams(document.location.href).display_info_id);
	reviews.sendGetAjax();
});