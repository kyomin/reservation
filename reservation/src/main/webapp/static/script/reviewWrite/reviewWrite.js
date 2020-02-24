let reviewWrite = {
		/* 		Variables	 */
		numOfStars : 5,
		numbers : [],
		requestData : {
			"productId" : "",
			"reservationInfoId" : "",
			"score" : "",
			"comment" : ""
		},
		
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

document.addEventListener("DOMContentLoaded", function() {
	reviewWrite.makeRating(reviewWrite.numOfStars);
});