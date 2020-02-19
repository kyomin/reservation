let product_detail = {
		/* 		Variables	 */
		method : "GET",
		url : '',
		
		/* 		Functions	 */
		handleResponse : function(jsonResponse) {
			//	상세 페이지를 이루는 각 데이터 셋팅!
			average_score.setData(jsonResponse.averageScore);
			comments.setData(jsonResponse.comments);
			display_info.setData(jsonResponse.displayInfo);
			display_info_image.setData(jsonResponse.displayInfoImage);
			product_images.setData(jsonResponse.productImages);
			product_prices.setData(jsonResponse.productPrices);
			
			//	셋팅된 데이터를 각각 객체가 처리하도록 위임!
			product_images.handleData();
			comments.handleData();
			average_score.handleData();
			display_info.handleData();
		},
		
		setUrlByDisplayInfoId : function(displayInfoId) {
			this.url = `api/products/${displayInfoId}`;
		}
};

//	make ajax function for this data
const sendAjaxForProductDetail = ajax.bind(product_detail);