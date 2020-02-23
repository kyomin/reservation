let promotion = {
		/* 		Variables	 */
		getUrl : "api/promotions",
		promotionCount : 0,
		slideContainer : document.getElementById("promotions"),
		currentSlide : 0,
		
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