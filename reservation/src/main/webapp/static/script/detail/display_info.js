let display_info = {
		/* 		Variables	 */
		displayInfo : {},
		descriptionContainerInPromotionList : [],	// 프로모션 슬라이드 내의 description
		
		// 상세페이지 - 상단영역의 product content
		topContentContainer : document.getElementById("_dsc"),
		topContentMoreOpenBtn : document.getElementById("_open"),	// '펼쳐보기' 버튼
		topContentMoreCloseBtn : document.getElementById("_close"),	// '접기' 버튼
		
		// 상세페이지 - 하단영역 - 예매자 한줄평
		descriptionContainerInCommentList : [],		// 예매자 한줄평 내의 description
		
		// 상세페이지 - 하단 상세설명
		infoTab : document.getElementById("_info_tab_lst"),
		prevTab : "_detail",
		pairOfTabAndContent : {
			"_detail" : "_detail_area_wrap",
			"_path" : "_detail_location"
		},
		bottomContentContainer : document.getElementById("bottom_dsc"),
		
		/* 		Functions	 */
		setData : function(displayInfo) {
			this.displayInfo = displayInfo;
		},
		
		handleData : function() {
			// 상단 부분에 상품 content 삽입!
			this.topContentContainer.innerText = this.displayInfo.productContent;
			
			// 프로모션 슬라이드 부분에 타이틀 넣기
			this.descriptionContainerInPromotionList = document.querySelectorAll(".visual_txt_tit");
			this.descriptionContainerInPromotionList.forEach( description => {
				description.innerText = this.displayInfo.productDescription;
				// 슬라이드 타이틀 부분 최대한 가운데 배치!
				description.parentNode.style.position = "absolute";
				description.parentNode.style.top = "33%";
			});
			
			// comment 리스트 부분에 타이틀 넣기
			this.descriptionContainerInCommentList = document.querySelectorAll(".resoc_name");
			this.descriptionContainerInCommentList.forEach( description => {
				description.innerText = this.displayInfo.productDescription;
			});
			
			// 하단 부분 '상세정보' 탭에 상품 content 삽입!
			this.bottomContentContainer.innerText = this.displayInfo.productContent;
			
			// 하단 부분 '오시는길'탭 html에 데이터 매핑!
			document.getElementById("display_info_image").src = `static/${display_info_image.displayInfoImage.saveFileName}`;
	    	document.getElementById("product_description").innerText = this.displayInfo.productDescription;
	    	document.getElementById("place_lot").innerText = this.displayInfo.placeLot;
	    	document.getElementById("place_street").innerText = this.displayInfo.placeStreet;
	    	document.getElementById("place_name").innerText = this.displayInfo.placeName;
	    	document.getElementById("telephone").innerText = this.displayInfo.telephone;
		}
};

//	상단 product content의 '펼쳐보기' 버튼 클릭 이벤트 등록
display_info.topContentMoreOpenBtn.addEventListener("click", function(e) {
	document.getElementById("_store_details").classList.remove("close3");
	display_info.topContentMoreOpenBtn.classList.add("hide");
	display_info.topContentMoreCloseBtn.classList.remove("hide");
});

//	상단 product content의 '접기' 버튼 클릭 이벤트 등록
display_info.topContentMoreCloseBtn.addEventListener("click", function(e) {
	document.getElementById("_store_details").classList.add("close3");
	display_info.topContentMoreOpenBtn.classList.remove("hide");
	display_info.topContentMoreCloseBtn.classList.add("hide");
});

// 	'상세정보'와 '오시는길' 탭 클릭 시의 이벤트
display_info.infoTab.addEventListener("click", function(e) {
	// 클릭된 해당 탭의 아이디 가져오기
	var currentTab = e.target.closest("a").getAttribute("id");

	// 클릭된 것이 이전에 클릭된 탭과 다를 경우에만 처리
	if(currentTab !== display_info.prevTab) {
		// 탭 focus 처리!
		document.getElementById(display_info.prevTab).classList.remove("active");
    	document.getElementById(currentTab).classList.add("active");
		
    	// 탭과 대응되는 내용물들 처리!
    	document.getElementById(display_info.pairOfTabAndContent[currentTab]).classList.remove("hide");
    	document.getElementById(display_info.pairOfTabAndContent[display_info.prevTab]).classList.add("hide");
    
    	// 이전 탭 현재 탭으로 셋팅!
    	display_info.prevTab = currentTab;
	}
});