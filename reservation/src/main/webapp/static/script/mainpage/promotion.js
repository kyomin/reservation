let promotion = {
		/* 		Variables	 */
		method : "GET",
		url : "api/promotions",
		templateId : "promotionItem",
		parentNodeIds : ["promotions"],
		promotionCount : 0,
		slideContainer : document.getElementById("promotions"),
		currentSlide : 0,
		
		/* 		Functions	 */
		handleResponse : function(jsonResponse) {
			this.promotionCount = jsonResponse.promotions.length;
			drawTemplateToHtml.bind(this)(jsonResponse.promotions, "");
			
			this.slideContainer.style.width = `calc(100% * ${this.promotionCount})`;
			this.slideContainer.style.display = "flex";
			this.slideContainer.style.transition = "1s";
			
			this.startPromotionSlide();	// 슬라이드 시작!
		},
		
		startPromotionSlide : function() {
			setInterval(() => {
				this.currentSlide = (this.currentSlide + 1) % this.promotionCount; // 장면 선택
				this.slideContainer.style.marginLeft = `${-this.currentSlide * 100}%`; // 장면 전환
			}, 1500); // 1500 = 1500ms = 1.5sec. 즉, 1.5초 마다 실행
		}
};

//	make ajax function for this data
const sendAjaxForPromotion = ajax.bind(promotion);