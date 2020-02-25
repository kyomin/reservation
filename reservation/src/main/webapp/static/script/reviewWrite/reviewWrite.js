let reviewWrite = {
		/* 		Variables	 */
		reservationInfoId : 0,
		numOfStars : 5,
		lengthOfComments : 400,
		numbers : [],
		requestData : {
			"productId" : "",
			"reservationInfoId" : "",
			"score" : 0,
			"comment" : "",
			"commentImage" : null
		},
		currentLen : document.getElementById("current_len"),
		
		/* 		Functions	 */
		makeRating : function(num) {	// 인자로 받은 num의 값만큼 별을 만들 것이다.
			// 템플릿과 바인딩 할 리스트를 만들어 준다.
			for(var i=1; i<=num; i++) {
				let tempObj = {};
				tempObj["num"] = i.toString();
				
				this.numbers.push(tempObj);
			}
			
			drawTemplateToHtml(this.numbers, "", "ratingItem", ["_rating"]);
			
			// 별들을 다 그린 후 점수 표시 영역을 그려준다.
			document.getElementById("_rating").innerHTML += "<span class='star_rank gray_star' id='star_rank_score'>0</span>";
		}
};

/* 		myreservation.jsp 페이지 내에서 발생하는 함수 정의! 	 */
function clickStar(num) {
	reviewWrite.requestData["score"] = num;
	
	// 기존의 하이라이트 된 별들 해제하고
	for(var i=1; i<=reviewWrite.numOfStars; i++) {
		document.getElementById(`rating_rdo_${i}`).checked = false;
	}
	
	// 선택된 별 이하의 값을 가진 별들 하이라이트 시키기!
	for(var i=1; i<=num; i++) {
		document.getElementById(`rating_rdo_${i}`).checked = true;
	}
	
	// 점수 노출
	document.getElementById("star_rank_score").classList.remove("gray_star");
	document.getElementById("star_rank_score").innerText = num;
};

// 별점이 1점 이상 5점 이하, comment 글자수 5자 이상 400자 이하 범위인지 검증한다.
function checkValidation() {
	// 별점 선택 검증
	if(reviewWrite.requestData["score"] === 0) {
		alert("별점을 선택해 주십시오.");
		return false;
	} else if(reviewWrite.requestData["score"]<1 || reviewWrite.requestData["score"]>5) {
		alert("유효하지 않은 별점입니다. \n다시 선택해 주십시오.");
		return false;
	}
	
	// comment 글자수 점검. change 이벤트 리스너에 의해 초과값이 검증되므로 여기서는 최소값만 검증해준다.
	if(reviewWrite.requestData["comment"].length<5) {
		alert("최소 5자 이상 입력해 주십시오.");
		return false;
	}
	
	return true;
};

function handleSubmit() {
	// form 입력 항목이 유효하지 않다면 submit 처리 철회!
	if(!checkValidation()) {
		return;
	}
	
	let formData = new FormData();
	
	formData.append("productId", reviewWrite.requestData["productId"]);
	formData.append("reservationInfoId", reviewWrite.requestData["reservationInfoId"]);
	formData.append("score", reviewWrite.requestData["score"]);
	formData.append("comment", reviewWrite.requestData["comment"]);
	
	if(reviewWrite.requestData["commentImage"] !== null) {
		formData.append("commentImage", reviewWrite.requestData["commentImage"]);
	}
	
	console.log("requestData : ", reviewWrite.requestData);
	
	// POST AJAX Request!
	var xhr = new XMLHttpRequest();

	xhr.onload = function() {
	  if (xhr.status === 200 || xhr.status === 201) {	
		  alert("평점 등록 성공!\n소중한 의견 감사합니다.");
		  location.href="myreservation";
	  } 
	};
	
	xhr.open('POST', `api/reservations/${reviewWrite.reservationInfoId}/comments`);
	xhr.send(formData);		// string화 하지 않고 원본 그대로 보내야 디폴트로 multipart/form-data 설정이 된다!
};


/* 		reviewWrite.jsp 페이지 내에서 발생하는 이벤트 정의! 	 */
document.getElementById("_review_textarea").addEventListener('keyup', function(e) {
	if(e.target.value.length > reviewWrite.lengthOfComments) {
		alert("글자수를 초과하였습니다.");
		e.target.value = e.target.value.substring(0, reviewWrite.lengthOfComments);
		reviewWrite.requestData["comment"] = e.target.value;
		reviewWrite.currentLen.innerText = e.target.value.length;
	} else {
		reviewWrite.requestData["comment"] = e.target.value;
		reviewWrite.currentLen.innerText = e.target.value.length;
	}
});

document.getElementById("reviewImageFileOpenInput").addEventListener("change", function(e) {
	reviewWrite.requestData["commentImage"] = e.target.files[0];
   
	// 이미지 정보 화면에 노출
	document.getElementById("img_thumb_li").style.display = "block";
    document.getElementById("_item_thumb").src = window.URL.createObjectURL(reviewWrite.requestData["commentImage"]);
});

document.getElementById("_ico_del").addEventListener("click", function(e) {
	reviewWrite.requestData["commentImage"] = null;
   
    // 이미지 정보 화면에 노출
	document.getElementById("img_thumb_li").style.display = "none";
});

document.addEventListener("DOMContentLoaded", function() {
	// get url을 파싱하여 object에 담는다.
	let params = getParams(document.location.href);
	
	// 로그인을 하지 않은 채 한줄평 남기기를 시도하는 것을 방지한다.
	if(sessionStorage.getItem("reservationEmail") === null) {
		alert("잘못된 접근입니다. \n로그인 하십시오.");
		location.href="/reservation";
	}
	
	// url을 파싱했을 때, url로 타고 들어온 해당 상품의 id와 예약 정보 id를 파싱하여 request 데이터에 셋팅한다.
	// 만일 하나라도 정보를 갖고 오지 않는다면 잘못된 접근이다.
	if(params.product_id === undefined || params.reservation_info_id === undefined) {
		alert("잘못된 접근입니다.");
		location.href="/reservation";
	} else {
		reviewWrite.requestData["productId"] = params.product_id;
		reviewWrite.requestData["reservationInfoId"] = params.reservation_info_id;
		
		reviewWrite.reservationInfoId = params.reservation_info_id;
	}
	
	// 타이틀 표시
	document.getElementById("head_title").innerText = getParams(document.location.href).title;
	
	// 최대 글자수 표시
	document.getElementById("max_len").innerText = reviewWrite.lengthOfComments;
	
	reviewWrite.makeRating(reviewWrite.numOfStars);
});