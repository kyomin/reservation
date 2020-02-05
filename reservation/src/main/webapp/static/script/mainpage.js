/*
 * 		Objects
 */
let promotion = {
		/* 		Variables	 */
		method : "GET",
		url : "api/promotions",
		templateId : "promotionItem",
		parentNodeIds : ["promotions"],
		promotionCount : 0,
		
		/* 		Functions	 */
		handleResponse : function(jsonResponse) {
			this.promotionCount = jsonResponse.promotions.length;
			drawTemplateToHtml.bind(this)(jsonResponse.promotions, "");
			this.startPromotionSlide();	// 슬라이드 시작!
		},
		
		startPromotionSlide : function() {
			var slideContainer = document.querySelector(".visual_img");
			
			slideContainer.style.width = `calc(100% * ${this.promotionCount})`;
			slideContainer.style.display = "flex";
			slideContainer.style.transition = "1s";
			
			let pos = 0;
			
			setInterval(() => {
				pos = (pos + 1) % this.promotionCount // 장면 선택
				slideContainer.style.marginLeft = `${-pos * 100}%` // 장면 전환
			}, 1500); // 1500 = 1500ms = 1.5sec. 즉, 1.5초 마다 실행
		}
};

let category = {
		/* 		Variables	 */
		method : "GET",
		url : "api/categories",
		templateId : "categoryItem",
		parentNodeIds : ["category_tab"],
		tabMenu : document.querySelector(".section_event_tab"),
		prevCategoryId : 0,
		currentCategoryId : 0,
		defaultCategoryHTML : "<li class='item' data-category='0'><a class='anchor active' id='category0'> <span>전체리스트</span> </a></li>",
		
		/* 		Functions	 */
		handleResponse : function(jsonResponse) {
			drawTemplateToHtml.bind(this)(jsonResponse.categories, this.defaultCategoryHTML);
		}
};

let product = {
		/* 		Variables	 */
		method : "GET",
		url : "api/products",
		templateId : "productItem",
		parentNodeIds : ["left_product", "right_product"],
		startIndex : 0,		// 디폴트는 0번 인덱스부터 시작
		currentCategoryId : 0,		// 디폴트는 (상품)전체보기
		totalCount : 0,
		
		/* 		Event Elements	 */
		moreBtn : document.querySelector("#more_btn"),
		
		/* 		Functions	 */
		handleResponse : function(jsonResponse) {
			drawTemplateToHtml.bind(this)(jsonResponse.products, "");
			this.startIndex += jsonResponse.products.length;
			this.totalCount = jsonResponse.totalCount;
			this.url = this.currentCategoryId===0 ? ("api/products?start=" + this.startIndex) : ("api/products?category_id=" + this.currentCategoryId + "&start=" + this.startIndex);
			
			// 해당 탭의 총 상품 개수 처리
			document.querySelector("#products_count").innerHTML = this.totalCount + '개';
			
			// 더 불러올 상품 데이터가 없으면 '더보기' 버튼 제거!
			this.startIndex >= this.totalCount && this.moreBtn.setAttribute("class", "hide");
		},
		
		handleChangedCategory : function(currentCategoryId) {
			this.startIndex = 0;
			this.currentCategoryId = currentCategoryId;
			this.url = currentCategoryId===0 ? ("api/products") : ("api/products?category_id=" + currentCategoryId);
			removeInnerHtml.bind(this)();
		}
};


/*
 * 		Make Ajax Function For Various Data
 */
const sendAjaxForPromotion = ajax.bind(promotion);
const sendAjaxForCategory = ajax.bind(category);
const sendAjaxForProduct = ajax.bind(product);


/*
 * 		Events
 */

/* 		클라이언트 첫 로드 시의 발생 이벤트! 		*/
document.addEventListener("DOMContentLoaded", function() {	
	sendAjaxForPromotion();
	sendAjaxForProduct();
	sendAjaxForCategory();
})


/*		상품 목록의 '더보기' 버튼 클릭 이벤트		*/
product.moreBtn.addEventListener("click", function(e) {
	sendAjaxForProduct();
});


/* 		카테고리 탭 요소들 클릭 시의 이벤트 		*/
category.tabMenu.addEventListener("click", function(e) {
	// 클릭된 해당 카테고리의 아이디 가져오기
	var currentCategory = e.target.closest("li");
	category.currentCategoryId = currentCategory.getAttribute("data-category");
	
	// 이전 탭에서 '더보기' 버튼이 사라졌다 해도 새로운 탭에서 다시 초기화 해준다!
	product.moreBtn.setAttribute("class", "more");
	
	// 이전 탭과 다른 탭을 클릭한 경우만 처리한다.
	if(category.currentCategoryId !== category.prevCategoryId) {
		// 현재 클릭된 카테고리를 focus 시키고
		currentCategory = e.target.closest("a");
		currentCategory.setAttribute("class", "anchor active");
		
		// 이전 카테고리의 focus를 해제한 후, 다음 작업을 위해 현재 카테고리를 이전 카테고리로 지정한다.
		document.getElementById("category" + category.prevCategoryId).setAttribute("class", "anchor")
		category.prevCategoryId = category.currentCategoryId;
		
		product.handleChangedCategory(parseInt(category.currentCategoryId));
		sendAjaxForProduct();
	}
});