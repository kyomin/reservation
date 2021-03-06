let product = {
		/* 		Variables	 */
		getUrl : "api/products",
		startIndex : 0,		// 디폴트는 0번 인덱스부터 시작
		currentCategoryId : 0,		// 디폴트는 (상품)전체보기
		totalCount : 0,
		
		/* 		Event Elements	 */
		moreBtn : document.getElementById("more_btn"),
		
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
			drawTemplateToHtml(jsonResponse.products, "", "productItem", ["left_product", "right_product"]);
			
			this.startIndex += jsonResponse.products.length;
			this.totalCount = jsonResponse.totalCount;
			this.getUrl = this.currentCategoryId===0 ? (`api/products?start=${this.startIndex}`) : (`api/products?category_id=${this.currentCategoryId}&start=${this.startIndex}`);
			
			// 해당 탭의 총 상품 개수 처리
			document.getElementById("products_count").innerHTML = this.totalCount + '개';
			
			// 더 불러올 상품 데이터가 없으면 '더보기' 버튼 제거!
			this.startIndex >= this.totalCount && this.moreBtn.classList.add("hide");
		},
		
		handleChangedCategory : function(currentCategoryId) {
			this.startIndex = 0;
			this.currentCategoryId = currentCategoryId;
			this.getUrl = currentCategoryId===0 ? ("api/products") : (`api/products?category_id=${currentCategoryId}`);
			removeInnerHtml(["left_product", "right_product"]);
		}
};


//	상품 목록의 '더보기' 버튼 클릭 이벤트 등록
product.moreBtn.addEventListener("click", function(e) {
	product.sendGetAjax();
});