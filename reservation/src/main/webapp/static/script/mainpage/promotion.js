let promotion = {
		/* 		Variables	 */
		method : "GET",
		url : "api/promotions",
		promotionCount : 0,
		slideContainer : document.getElementById("promotions"),
		currentSlide : 0,
		
		/* 		Functions	 */
		handleResponse : function(jsonResponse) {
			drawTemplateToHtml(jsonResponse.promotions, "", "promotionItem", ["promotions"]);
			
			this.promotionCount = jsonResponse.promotions.length;
			this.slideContainer.style.width = `calc(100% * ${this.promotionCount})`;
			this.slideContainer.style.display = "flex";
			this.slideContainer.style.transition = "2s";
			
			this.startPromotionSlide();	// 슬라이드 시작!
		},
		
		startPromotionSlide : function() {
			setInterval(() => {
				this.currentSlide = (this.currentSlide + 1) % this.promotionCount; // 장면 선택
				this.slideContainer.style.marginLeft = `${-this.currentSlide * 100}%`; // 장면 전환
			}, 2000); // 2000 = 2000ms = 2.0sec. 즉, 2초 마다 실행
		}
};

//	make ajax function for this data
const sendAjaxForPromotion = ajax.bind(promotion);