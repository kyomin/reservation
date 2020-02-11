let display_info = {
		/* 		Variables	 */
		displayInfo : {},
		descriptionContainerInPromotionList : [],
		topContentContainer : document.querySelector(".dsc"),
		topContentMoreOpenBtn : document.querySelector("._open"),	// '펼쳐보기' 버튼
		topContentMoreCloseBtn : document.querySelector("._close"),	// '접기' 버튼
		descriptionContainerInCommentList : [],
		bottomContentContainer : document.getElementById("bottom_dsc"),
		
		/* 		Functions	 */
		setDisplayInfo : function(displayInfo) {
			this.displayInfo = displayInfo;
		},
		
		handleData : function() {
			// 상단 부분에 상품 content 삽입!
			this.topContentContainer.innerText = this.displayInfo.productContent;
			
			// 프로모션 슬라이드 부분에 타이틀 넣기
			this.descriptionContainerInPromotionList = document.querySelectorAll(".visual_txt_dsc");
			this.descriptionContainerInPromotionList.forEach( description => {
				description.innerText = this.displayInfo.productDescription;
			});
			
			// comment 리스트 부분에 타이틀 넣기
			this.descriptionContainerInCommentList = document.querySelectorAll(".resoc_name");
			this.descriptionContainerInCommentList.forEach( description => {
				description.innerText = this.displayInfo.productDescription;
			});
			
			// 하단 부분에 상품 content 삽입!
			this.bottomContentContainer.innerText = this.displayInfo.productContent;
		}
};

//	상단 product content의 '펼쳐보기' 버튼 클릭 이벤트 등록
display_info.topContentMoreOpenBtn.addEventListener("click", function(e) {
	let storeDetails = document.querySelector(".store_details");
	storeDetails.setAttribute("class", "store_details");
	display_info.topContentMoreOpenBtn.setAttribute("class", "bk_more _open hide");
	display_info.topContentMoreCloseBtn.setAttribute("class", "bk_more _close");
});

//	상단 product content의 '접기' 버튼 클릭 이벤트 등록
display_info.topContentMoreCloseBtn.addEventListener("click", function(e) {
	let storeDetails = document.querySelector(".store_details");
	storeDetails.setAttribute("class", "store_details close3");
	display_info.topContentMoreOpenBtn.setAttribute("class", "bk_more _open");
	display_info.topContentMoreCloseBtn.setAttribute("class", "bk_more _close hide");
});