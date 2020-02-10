let product_detail = {
		/* 		Variables	 */
		method : "GET",
		url : '',
		display_info_id : -1,
		
		/* 		Functions	 */
		handleResponse : function(jsonResponse) {
			// 상세 페이지를 이루는 각 데이터 셋팅!
			average_score.setAverageScore(jsonResponse.averageScore);
			comments.setComments(jsonResponse.comments);
			display_info.setDisplayInfo(jsonResponse.displayInfo);
			display_info_image.setDisplayInfoImage(jsonResponse.displayInfoImage);
			product_images.setProductImages(jsonResponse.productImages);
			product_prices.setProductPrices(jsonResponse.productPrices);
			
			// 셋팅된 데이터를 각각 객체가 처리하도록 위임!
			product_images.handleData();
			display_info.handleData();
			comments.handleData();
			average_score.handleData();
		},
		
		handleSpecificProduct : function(displayInfoId) {
			this.display_info_id = displayInfoId;
			this.url = `api/products/${this.display_info_id}`;
		}
}

//make ajax function for this data
const sendAjaxForProductDetail = ajax.bind(product_detail);