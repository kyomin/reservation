let product_images = {
		/* 		Variables	 */
		productImages : [],
		slideContainer : document.getElementById("product_image_slide"),
		promotionCount : 0,
		currentSlide : 0,
		
		/* 		Event Elements	 */
		rightBtn : document.getElementById("_nxt"),
		leftBtn : document.getElementById("_prev"),
		
		/* 		Functions	 */
		setData : function(productImages) {
			this.productImages = productImages;
		},
		
		handleData : function() {
			// 전체 슬라이드 개수 표시
			this.promotionCount = this.productImages.length;
			document.getElementById("product_images_count").innerText = this.promotionCount;
			
			// type = 'ma' 이외에 더 볼 이미지가 없다면 화살표 버튼 제거!
			if(this.promotionCount < 2) {
				this.rightBtn.classList.add("hide");
				this.leftBtn.classList.add("hide");
			}
			
			// 슬라이드 컨테이너 셋팅!
			this.slideContainer.style.width = `calc(100% * ${this.promotionCount})`;
			this.slideContainer.style.display = "flex";
			this.slideContainer.style.transition = "1s";
			
			// 가져온 데이터 해당 영역에 그리기!
			drawTemplateToHtml(this.productImages, "", "promotionItem", ["product_image_slide"]);
		},
		
		rightPromotionSlide : function() {
			this.currentSlide = (this.currentSlide + 1) % this.promotionCount; // 장면 선택
			this.slideContainer.style.marginLeft = `${-this.currentSlide * 100}%`; // 장면 전환
			document.getElementById("current_slide").innerText = this.currentSlide + 1; // 현재 슬라이드 표시
		},
		
		leftPromotionSlide : function() {
			this.currentSlide = (this.currentSlide - 1) === -1 ? this.promotionCount - 1 : this.currentSlide - 1;
			this.slideContainer.style.marginLeft = `${-this.currentSlide * 100}%`; // 장면 전환
			document.getElementById("current_slide").innerText = this.currentSlide + 1; // 현재 슬라이드 표시
		}
};

//	프로모션 슬라이드의 오른쪽 버튼 클릭 이벤트 등록
product_images.rightBtn.addEventListener("click", function(e) {
	product_images.rightPromotionSlide();
});

//	프로모션 슬라이드의 왼쪽 버튼 클릭 이벤트 등록
product_images.leftBtn.addEventListener("click", function(e) {
	product_images.leftPromotionSlide();
});