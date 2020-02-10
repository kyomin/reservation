let display_info = {
		/* 		Variables	 */
		display_info : {},
		descriptionContainer : [],
		topContentContainer : document.querySelector(".dsc"),
		topContentMoreOpenBtn : document.querySelector("._open"),	// '펼쳐보기' 버튼
		topContentMoreCloseBtn : document.querySelector("._close"),	// '접기' 버튼
		
		/* 		Functions	 */
		setDisplayInfo : function(display_info) {
			this.display_info = display_info;
		},
		
		handleData : function() {
			// 상단 부분에 상품 content 삽입!
			this.topContentContainer.innerText = this.display_info.productContent;
			this.descriptionContainer = document.querySelectorAll(".visual_txt_dsc");
			
			this.descriptionContainer.forEach( description => {
				description.innerText = this.display_info.productDescription;
			});
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