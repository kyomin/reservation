let category = {
		/* 		Variables	 */
		getUrl : "api/categories",
		prevCategoryId : 0,
		currentCategoryId : 0,
		defaultCategoryHTML : "<li class='item' data-category='0'><a class='anchor active' id='category0'> <span>전체리스트</span> </a></li>",
		
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
			drawTemplateToHtml(jsonResponse.categories, this.defaultCategoryHTML, "categoryItem", ["category_tab"]);
		}
};


// 		카테고리 탭 요소들 클릭 시의 이벤트
document.getElementById("_section_event_tab").addEventListener("click", function(e) {
	// 클릭된 해당 카테고리의 아이디 가져오기
	category.currentCategoryId = e.target.closest("li").getAttribute("data-category");
	
	// 이전 탭에서 '더보기' 버튼이 사라졌다 해도 새로운 탭에서 다시 초기화 해준다!
	product.moreBtn.classList.remove("hide");
	
	// 이전 탭과 다른 탭을 클릭한 경우만 처리한다.
	if(category.currentCategoryId !== category.prevCategoryId) {
		// 현재 클릭된 카테고리를 focus 시키고
		e.target.closest("a").classList.add("active");
		
		// 이전 카테고리의 focus를 해제한 후, 다음 작업을 위해 현재 카테고리를 이전 카테고리로 지정한다.
		document.getElementById(`category${category.prevCategoryId}`).classList.remove("active");
		category.prevCategoryId = category.currentCategoryId;
		
		product.handleChangedCategory(parseInt(category.currentCategoryId));
		product.sendGetAjax();
	}
});