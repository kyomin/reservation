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