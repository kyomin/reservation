var currentUrl = document.location.href; 
var params = getParams(currentUrl);

function ajaxDisplayInfo() {
	var oReq = new XMLHttpRequest();
	
	oReq.addEventListener("load", function() {
		var jsonResponse = JSON.parse(oReq.responseText);
	});
	
	oReq.open("GET", `api/products/${params.display_info_id}`);
	oReq.send();
};

document.addEventListener("DOMContentLoaded", function() {	
	ajaxDisplayInfo();
});