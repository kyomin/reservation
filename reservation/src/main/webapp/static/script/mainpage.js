/*
 * 		Variables
 */

//	for API URL
const apiProductsUrl = "http://localhost:8080/reservation/api/products";
const apiCategoryUrl = "http://localhost:8080/reservation/api/categories";

//	for Event Element
var tabMenu = document.querySelector(".section_event_tab");
var moreBtn = document.querySelector("#more_btn");

var startIndex = 0;
var totalCount = 0;

var leftProductsHTML = "";
var rightProductsHTML = "";
var categoryHTML = "<li class='item' data-category='0'><a class='anchor active' id='category0'> <span>전체리스트</span> </a></li>";

var prevCategoryId = "0";
var currentCategoryId = "0";


/*
 * 		Events
 */

/* 클라이언트 첫 로드 시의 발생 이벤트! */
document.addEventListener("DOMContentLoaded", function() {	
	sendAjaxForCategoryList(apiCategoryUrl);
	sendAjaxForProductsList(apiProductsUrl);
})

/* 카테고리 탭 요소들 클릭 시의 이벤트 */
tabMenu.addEventListener("click", function(e) {
	// 클릭된 해당 카테고리의 아이디 및 개수 가져오기
	var currentCategory = e.target.closest("li");
	var currentCategoryCount = currentCategory.getAttribute("data-count");
	currentCategoryId = currentCategory.getAttribute("data-category");
	
	// 이전 탭에서 '더보기' 버튼이 사라졌다 해도 새로운 탭에서 다시 초기화 해준다!
	document.getElementById('more_btn').setAttribute("class", "more");
	
	// 이전 탭과 다른 탭을 클릭한 경우만 처리한다.
	if(currentCategoryId !== prevCategoryId) {
		// 현재 클릭된 카테고리를 focus 시키고
		currentCategory = e.target.closest("a");
		currentCategory.setAttribute("class", "anchor active");
		
		// 이전 카테고리의 focus를 해제한 후, 다음 작업을 위해 현재 카테고리를 이전 카테고리로 지정한다.
		document.getElementById("category"+prevCategoryId).setAttribute("class", "anchor")
		prevCategoryId = currentCategoryId;
		
		// 그리고 선택한 해당 탭의 카테고리에 대한 상품 리스트를 불러오는 작업을 한다.
		if(currentCategoryId !== "0") {	// 전체리스트가 아닐 경우!
			// 현재 탭에서의 총 상품 개수 셋팅!
			totalCount = currentCategoryCount;
			
			// 현재 클릭한 탭에 대한 상품 목록을 불러온다.
			init().then( () => {
				sendAjaxForProductsList(apiProductsUrl + "?category_id=" + currentCategoryId);
			});
		} else {	// 전체리스트일 경우!
			init()
			.then( () => {
				sendAjaxForProductsList(apiProductsUrl);
			});
		}
	}
});

/* '더보기' 버튼 클릭 이벤트 */
moreBtn.addEventListener("click", function() {
	if(currentCategoryId !== "0") {	// 전체리스트가 아닐 경우
		sendAjaxForProductsList(apiProductsUrl + "?category_id=" + currentCategoryId + "&start=" + startIndex);
	} else {	// 전체리스트일 경우
		sendAjaxForProductsList(apiProductsUrl + "?start=" + startIndex);
	}
});


/*
 * 		Ajax
 */	

function sendAjaxForProductsList(url) {
	var oReq = new XMLHttpRequest();
	
	oReq.addEventListener("load", function() {
		// JSON 형식으로 데이터를 받아온다.
		var data = JSON.parse(oReq.responseText);
	
		startIndex += data.products.length;
		
		// 전체 리스트일 경우에만 총 테이블 카운트를 할당한다.
		if(data.totalCount != -1) {
			totalCount = data.totalCount;
		}
		
		makeProductList(data);
		
		// 더 불러올 상품 데이터가 없으면 '더보기' 버튼 제거!
		startIndex >= totalCount && document.getElementById('more_btn').setAttribute("class", "hide");
	});
	
	oReq.open("GET", url);
	oReq.send();
}

function sendAjaxForCategoryList(url) {
	var oReq = new XMLHttpRequest();
	
	oReq.addEventListener("load", function() {
		// JSON 형식으로 받아온다.
		var data = JSON.parse(oReq.responseText);
	
		makeCategoryList(data);
	});
	
	oReq.open("GET", url);
	oReq.send();
}


/*
 * 		Functions
 */

/* 새로운 카테고리의 ajax 응답을 받기 위한 변수 초기 함수 */
function init() {	
	// 초기화 후에 ajax 통신을 보내야 하므로 순서를 맞추기 위함이다.
	return new Promise(function(resolve,reject){
		startIndex = 0;
		leftProductsHTML = "";
		rightProductsHTML = "";
		
		resolve();
	});
}

function makeProductList(data) {
	var html = document.getElementById("itemList").innerHTML;	
	var products = data.products;
	
	document.querySelector("#products_count").innerHTML = totalCount + '개';
	
	// 더보기 버튼으로 인해 추가되는 상품들을 DOM에 계속 이어 붙인다.
	products.map( (product, idx) => {
		if(idx%2 === 0) {
			leftProductsHTML += html.replace("{id}", product.productId)
			  .replace("{description}", product.productDescription)
			  .replace("{fileName}", product.productImageUrl)
			  .replace("{description}", product.productDescription)
			  .replace("{placeName}", product.placeName)
			  .replace("{content}", product.productContent);
		} else {
			rightProductsHTML += html.replace("{id}", product.productId)
			  .replace("{description}", product.productDescription)
			  .replace("{fileName}", product.productImageUrl)
			  .replace("{description}", product.productDescription)
			  .replace("{placeName}", product.placeName)
			  .replace("{content}", product.productContent);
		}
	});

	document.querySelector("#left_product").innerHTML = leftProductsHTML;
	document.querySelector("#right_product").innerHTML = rightProductsHTML;
}

function makeCategoryList(data) {
	var html = document.getElementById("categories").innerHTML;
	var categories = data.categories;
	
	categories.map( (category, idx) => {
		categoryHTML += html.replace("{id}", category.id)
			.replace("{count}", category.count)
			.replace("{id}", category.id)
			.replace("{name}", category.name);
	});
	
	document.querySelector("#category_tab").innerHTML = categoryHTML;
}