// 해당 스크립트 파일에서 전역적으로 사용할 변수들
const apiProductsUrl = "http://localhost:8080/reservation/api/products";
var startIndex = 0;
var totalCount = 0;
var leftHTML = "";
var rightHTML = "";

// 페이지가 로드되면 자동으로 실행되는 함수이다.
document.addEventListener("DOMContentLoaded", function() {
	sendAjaxForProductsList(apiProductsUrl);
})

function sendAjaxForProductsList(url) {
	var oReq = new XMLHttpRequest();
	console.log(url);
	
	oReq.addEventListener("load", function() {
		// JSON 형식으로 받아온다.
		var data = JSON.parse(oReq.responseText);
		
		startIndex += data.products.length;
		totalCount = data.totalCount;
		
		makeProductList(data);
		
		startIndex >= totalCount && document.querySelector('#more_btn').remove();
	});
	
	oReq.open("GET", url);
	oReq.send();
}

function makeProductList(data) {
	var html = document.getElementById("itemList").innerHTML;	
	var products = data.products;
	
	document.querySelector("#products_count").innerHTML = totalCount + '개';
	
	// 더보기 버튼으로 인해 추가되는 상품들을 DOM에 계속 이어 붙인다.
	products.map( (product, idx) => {
		if(idx%2 === 0) {
			leftHTML += html.replace("{id}", product.productId)
			  .replace("{description}", product.productDescription)
			  .replace("{fileName}", product.productImageUrl)
			  .replace("{description}", product.productDescription)
			  .replace("{placeName}", product.placeName)
			  .replace("{content}", product.productContent);
		} else {
			rightHTML += html.replace("{id}", product.productId)
			  .replace("{description}", product.productDescription)
			  .replace("{fileName}", product.productImageUrl)
			  .replace("{description}", product.productDescription)
			  .replace("{placeName}", product.placeName)
			  .replace("{content}", product.productContent);
		}
	});

	document.querySelector("#left_product").innerHTML = leftHTML;
	document.querySelector("#right_product").innerHTML = rightHTML;
}

function clickMoreBtn() {
	sendAjaxForProductsList(apiProductsUrl + "?start=" + startIndex);
}