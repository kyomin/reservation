var currentUrl = document.location.href; 
var params = getParams(currentUrl);

document.addEventListener("DOMContentLoaded", function() {	
	product_detail.handleSpecificProduct(params.display_info_id);
	sendAjaxForProductDetail();
});