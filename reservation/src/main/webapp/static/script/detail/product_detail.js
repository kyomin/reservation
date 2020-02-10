let product_detail = {
		/* 		Variables	 */
		method : "GET",
		url : '',
		display_info_id : -1,
		
		/* 		Functions	 */
		handleResponse : function(jsonResponse) {
			
		},
		
		handleSpecificProduct : function(displayInfoId) {
			this.display_info_id = displayInfoId;
			this.url = `api/products/${this.display_info_id}`;
		}
}

//make ajax function for this data
const sendAjaxForProductDetail = ajax.bind(product_detail);