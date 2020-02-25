let reserve = {
		/* 		Variables	 */
		method : "GET",
		getUrl : '',
		requestData : {
		  "id" : null,
		  "displayInfoId": parseInt(getParams(document.location.href).id),
		  "prices": [],
		  "productId": "",
		  "reservationEmail": "",
		  "reservationName": "",
		  "reservationTelephone": "",
		  "reservationYearMonthDay": ""
		},
		totalCount : 0,				// 예매 갯수
		isTermsAgreed : false,		// 약관 동의 여부
		
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
			//	예약 페이지를 이루는 각 데이터 셋팅!
			display_info.setData(jsonResponse.displayInfo);
			product_prices.setData(jsonResponse.productPrices);
			
			//	셋팅된 데이터를 각각 객체가 처리하도록 위임!
			display_info.handleData();
			product_prices.handleData();
		},
		
		setGetUrlByDisplayInfoId : function(displayInfoId) {
			this.getUrl = `api/products/${displayInfoId}`;
		},
		
		// 페이지 내의 현재 날짜가 표시되는 element를 조작한다.
		makeCurrentDate : function() {
			let today = new Date();   

			let year = today.getFullYear();
			let month = today.getMonth() + 1;
			let date = today.getDate();
			
			document.getElementById("reservationDate").innerText = year + "." + month + "." + date;
		}
};


/* 		reserve.jsp 페이지 내에서 발생하는 함수 정의! 	 */
function handleTicketPlus(id) {
	// 화면에 현재 특정 가격 타입의 총 가격 표시
	product_prices.pairOfIdAndTotalPrice[id] += product_prices.pairOfIdAndPrice[id];
	document.getElementById(`total_price_${id}`).innerText = product_prices.pairOfIdAndTotalPrice[id].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	
	// +를 시키는 순간 0이 아닌 것은 자명하므로 조건 처리 없이 바로 '-' 버튼 활성화
	document.getElementById(`minus_btn_${id}`).classList.remove("disabled");
	product_prices.pairOfIdAndIsTotalPriceZero[id] = false;
	
	// 서버로 보낼 price 데이터의 count 및 totalCount 조작 
	product_prices.requestPrices[id].count += 1;
	reserve.totalCount += 1;
	document.getElementById("totalCount").innerText = reserve.totalCount; 
	
	// 화면 상에 보여지는 수량 조절
	document.getElementById(`count_control_input_${id}`).setAttribute("value", product_prices.requestPrices[id].count);
};

function handleTicketMinus(id) {
	// 감소시키려는 해당 가격 타입의 카운트가 이미 0개 라면 감소하는 행위를 하지 않고 함수 종료!
	if(product_prices.pairOfIdAndIsTotalPriceZero[id])
		return;
	
	// 화면에 현재 특정 가격 타입의 총 가격 표시
	product_prices.pairOfIdAndTotalPrice[id] -= product_prices.pairOfIdAndPrice[id];
	document.getElementById(`total_price_${id}`).innerText = product_prices.pairOfIdAndTotalPrice[id].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	
	// 해당 타입 가격 수량의 합이 0이 되는 순간 '-' 버튼 비활성화! 
	if(product_prices.pairOfIdAndTotalPrice[id] === 0) {
		product_prices.pairOfIdAndIsTotalPriceZero[id] = true;
		document.getElementById(`minus_btn_${id}`).classList.add("disabled");
	}
	
	// 서버로 보낼 price 데이터의 count 및 totalCount 조작
	product_prices.requestPrices[id].count -= 1;
	reserve.totalCount -= 1;
	document.getElementById("totalCount").innerText = reserve.totalCount;
	
	// 화면 상에 보여지는 수량 조절
	document.getElementById(`count_control_input_${id}`).setAttribute("value", product_prices.requestPrices[id].count);
};

function handleAgreement(id) {
	if(!document.getElementById(`agreement_${id}`).classList.contains("open")) {
		document.getElementById(`agreement_${id}`).classList.add("open");
		document.getElementById(`btn_text_${id}`).innerText = "접기";
		document.getElementById(`fn_${id}`).classList.remove("fn-down2");
		document.getElementById(`fn_${id}`).classList.add("fn-up2");
	} else {
		document.getElementById(`agreement_${id}`).classList.remove("open");
		document.getElementById(`btn_text_${id}`).innerText = "보기";
		document.getElementById(`fn_${id}`).classList.remove("fn-up2");
		document.getElementById(`fn_${id}`).classList.add("fn-down2");
	}
};

// 예매 갯수가 1개이상, 예매자 입력, 연락처 입력, 이메일 입력, 약관 동의 된 상태라면 모든 값이 유효한상태이다.
function checkValidation() {
	// 예매 갯수 검증
	if(reserve.totalCount === 0) {
		alert("티켓을 1개 이상 선택해 주세요!");
		return false;
	}
	
	// 예매자 입력 검증
	if(reserve.requestData["reservationName"].length <= 1) {
		alert("예매자 이름을 2자 이상 입력해 주세요!");
		return false;
	}
	
	// 연락처 입력 검증
	if(reserve.requestData["reservationTelephone"].match(/01[01789]-\d{3,4}-\d{4}/) === null) {
		alert("형식에 맞춰 핸드폰 번호를 작성해 주세요!");
		return false;
	}
	
	// 이메일 입력 검증
	if(reserve.requestData["reservationEmail"].match(/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/) === null) {
		alert("형식에 맞춰 이메일을 작성해 주세요!");
		return false;
	}
	
	// 약관 동의 여부 검증
	if(!reserve.isTermsAgreed) {
		alert("약관에 동의해 주세요!");
		return false;
	}
	
	return true;
};

function handleSubmit() {
	// form 입력 항목이 유효하지 않다면 submit 처리 철회!
	if(!checkValidation()) {
		return;
	}
	
	// display_info 객체로부터 productId 얻기
	reserve.requestData.productId = display_info.displayInfo.productId;
	
	// 예매일 얻기 (오늘 포함해서 1 ~ 5일 랜덤값으로 설정) 및 JAVASCRIPT Date 형식 MySQL 형식으로 변환
	reserve.requestData.reservationYearMonthDay = new Date();
	reserve.requestData.reservationYearMonthDay.setDate(reserve.requestData.reservationYearMonthDay.getDate() + (Math.floor(Math.random() * 5) + 1));
	reserve.requestData.reservationYearMonthDay = reserve.requestData.reservationYearMonthDay.toMySQLDateFormat();
	
	// request 데이터의 prices 리스트를 JSON object로부터 만든다.
	Object.keys(product_prices.requestPrices).forEach(key => {
		// 티켓이 선택된 것만 DB에 저장할 것이다.
		if(product_prices.requestPrices[key].count > 0) {
			reserve.requestData.prices.push(product_prices.requestPrices[key]);
		}
	});
	
	// POST AJAX Request!
	var xhr = new XMLHttpRequest();

	xhr.onload = function() {
	  if (xhr.status === 200 || xhr.status === 201) {	
		  alert("예약 성공!");
		  location.href = "/reservation";
	  } else if(xhr.status === 500){	
		  location.reload(true);
		  alert("예약 실패! \n서버 내부 오류!");
	  } else if(xhr.status === 400) {
		  location.reload(true);
		  alert("예약 실패! \nform 형식 오류!");
	  }
	};
	
	xhr.open('POST', 'api/reservations');
	xhr.setRequestHeader('Content-Type', 'application/json');	// 컨텐츠타입을 json으로
	xhr.send(JSON.stringify(reserve.requestData));	// 데이터를 stringify해서 보냄
};


/* 		reserve.jsp 페이지 내에서 발생하는 이벤트 정의! 	 */
document.getElementById("reservationName").addEventListener('keyup', function(e) {
	reserve.requestData[e.target.name] = e.target.value;
});

document.getElementById("reservationTelephone").addEventListener('keyup', function(e) {
	reserve.requestData[e.target.name] = e.target.value;
	
	if(e.target.value.match(/01[01789]-\d{3,4}-\d{4}/) === null) {
		document.getElementById("phone_validation").classList.remove("hide");
	} else {
		document.getElementById("phone_validation").classList.add("hide");
	}
});

document.getElementById("reservationEmail").addEventListener('keyup', function(e) {
	reserve.requestData[e.target.name] = e.target.value;
	
	if(e.target.value.match(/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/) === null) {
		document.getElementById("email_validation").classList.remove("hide");
	} else {
		document.getElementById("email_validation").classList.add("hide");
	}
});

document.getElementById("chk3").addEventListener('click', function(e) {
	reserve.isTermsAgreed = !reserve.isTermsAgreed;
	
	// 약관정보 동의에 체크가 되면 예약하기 버튼 활성화!
	if(reserve.isTermsAgreed) {
		document.getElementById("_bk_btn_wrap").classList.remove("disable");
	} else {
		document.getElementById("_bk_btn_wrap").classList.add("disable");
	}
});

document.addEventListener("DOMContentLoaded", function() {	
	reserve.setGetUrlByDisplayInfoId(getParams(document.location.href).id);
	reserve.makeCurrentDate();
	reserve.sendGetAjax();
	drawMyEmail();
});