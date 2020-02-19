let product_prices = {
		/* 		Variables	 */
		productPrices : [],
		pairOfIdAndPrice : {},				// 가격 아이디와 해당하는 가격의 짝
		pairOfIdAndTotalPrice : {},			// 가격 아이디와 해당하는 total 가격의 짝
		pairOfIdAndIsTotalPriceZero : {},	// 가격 아이디와 해당하는 total 가격의 0값 여부의 짝
		pairOfTypeAndName : {				// 가격 타입과 해당 이름(하드 코딩)
			"A" : "성인",
			"B" : "유아",	
			"Y" : "청소년",
			"S" : "세트",
			"R" : "R석",
		},
		requestPrices : {},
		templateId : "ticketInfoItem",
		parentNodeIds : ["_ticket_body"],
		
		/* 		Functions	 */
		setData : function(productPrices) {
			this.productPrices = productPrices;
			
			this.productPrices.forEach( productPrice => {
				// 각각의 가격을 id 이용하여 관리!
				this.pairOfIdAndPrice[productPrice.productPriceId] = productPrice.price;
				
				// 각각의 사용자가 선택한 total 가격을 id 이용하여 관리!
				this.pairOfIdAndTotalPrice[productPrice.productPriceId] = 0;
				
				// 각각의 사용자가 선택한 total 가격이 0인지를 id 이용하여 관리!
				this.pairOfIdAndIsTotalPriceZero[productPrice.productPriceId] = true;
				
				// 리스트의 각 가격에 대해 천 단위로 콤마 찍어주는 작업 수행
				productPrice.price = productPrice.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				
				// 서버에 request를 위한 각 가격에 대한 객체를 만들어 리스트로 관리!
				var requestPrice = {
					"count": 0,
					"productPriceId": productPrice.productPriceId,
					"reservationInfoId": null,
				    "reservationInfoPriceId": null
				};
				
				this.requestPrices[productPrice.productPriceId] = requestPrice;
			});
		},
		
		handleData : function() {
			drawTemplateToHtml.bind(this)(this.productPrices, "");
			
			// 상단 상품정보 부분에 가격정보 그리기
			this.productPrices.forEach( productPrice => {
				document.getElementById(`product_price_type_${productPrice.priceTypeName}`).classList.remove("hide");
				document.getElementById(`product_price_${productPrice.priceTypeName}`).innerText = productPrice.price;
			}); 
			
			// 티켓 수 선택 영역에 가격 타입 이름 매핑
			this.productPrices.forEach( productPrice => {
				document.getElementById(`price_type_name_${productPrice.priceTypeName}`).innerText = this.pairOfTypeAndName[productPrice.priceTypeName];
			});
		}
};