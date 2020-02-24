let myreservation = {
		/* 		Variables	 */
		getUrl : "",
		responseData : [],
		reservations : [],
		size : 0,
		prevCategoryId : 0,
		currentCategoryId : 0,
		pairOfCategoryAndCount : {		// 각 카테고리에 대핸 데이터 갯수의 짝
			"전체" : 0,
			"이용예정" : 0,
			"이용완료" : 0,
			"취소·환불" : 0
		},	
		pairOfCategoryAndData : {		// 각 카테고리에 대한 데이터 리스트의 짝
			"전체" : [],
			"이용예정" : [],
			"이용완료" : [],
			"취소·환불" : []
		},
		defaultTemplates : {
				// 예약 확정
				1 : "<div class='link_booking_details'><div class='card_header'><div class='left'></div><div class='middle'><i class='spr_book2 ico_check2'></i><span class='tit'>예약 확정</span></div><div class='right'></div> </div> </div>",
				// 이용 완료
				2 : "<div class='link_booking_details'><div class='card_header'><div class='left'></div><div class='middle'><i class='spr_book2 ico_check2'></i><span class='tit'>이용 완료</span></div><div class='right'></div></div></div>",
				// 취소된 예약
				3 : "<div class='link_booking_details'><div class='card_header'><div class='left'></div><div class='middle'><i class='spr_book2 ico_cancel'></i><span class='tit'>취소된 예약</span></div><div class='right'></div></div></div>"
		},
		countByCategoryList : [],		// 카테고리 별 데이터 개수 object 리스트
		dataByCategoryList : [],		// 카테고리 별 데이터 object 리스트
		
		/* 		Functions	 */
		sendGetAjax : function() {
			var oReq = new XMLHttpRequest();
			
			oReq.addEventListener("load", function() {
				this.handleGetResponse(JSON.parse(oReq.responseText));
			}.bind(this));
			
			oReq.open("GET", this.getUrl);
			oReq.send();
		},
		
		setGetUrlByReservationEmail : function(reservationEmail) {
			this.getUrl = `api/reservations?reservationEmail=${reservationEmail}`;
		},
		
		handleGetResponse : function(jsonResponse) {
			this.setData(jsonResponse);
		},
		
		setData : function(jsonResponse) {
			// 서버로부터 받아온 데이터 셋팅
			this.reservations = jsonResponse.reservations;
			this.size = jsonResponse.size;
			
			// 예약 리스트의 각 총 가격에 대해 천 단위로 콤마 찍어주는 작업 수행
			this.reservations.forEach( reservation => {
				reservation.totalPrice = reservation.totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			})
			
			// 데이터 셋팅 완료 후에 해당 데이터를 가공해 화면에 뿌려주는 작업 수행
			this.handleData();
		},
		
		handleData : function() {
			this.classifyData();
			this.makeCategoryTab();
			this.makeAllReservationsList();
		},
		
		classifyData : function() {
			// 일단 '전체' 카테고리에 대한 데이터 분류 처리를 해준다.
			this.pairOfCategoryAndData["전체"] = this.reservations;
			this.pairOfCategoryAndCount["전체"] = this.size;
			
			// 조건에 따라 나머지 카테고리에 대한 분류 시작
			this.reservations.forEach( (reservation) => {
				// 현재 날짜를 얻어서 reservationDate와 비교할 것이다.
				let today = new Date();
				today= today.toMySQLDateFormat();
				
				// 취소된 상품이라면
				if(reservation.cancelYn) {
					this.pairOfCategoryAndData["취소·환불"].push(reservation);
					this.pairOfCategoryAndCount["취소·환불"] += 1;
					
					return;
				}
				
				if(reservation.reservationDate < today) {	// 현재 날짜가 상영 날짜보다 지난 경우
					this.pairOfCategoryAndData["이용완료"].push(reservation);
					this.pairOfCategoryAndCount["이용완료"] += 1;
				} else {
					this.pairOfCategoryAndData["이용예정"].push(reservation);
					this.pairOfCategoryAndCount["이용예정"] += 1;
				}
			});
			
			// 위로부터 카테고리 별로 분류된 데이터 및 그 개수를 object화 하여 리스트에 넣는다(화면에 템플릿으로 뿌려주기 위해). 
			Object.keys(this.pairOfCategoryAndData).forEach( (key, idx) => {
				let obj = {};
				obj["id"] = idx;
				obj["category"] = key;
				obj["data"] = this.pairOfCategoryAndData[key];
				
				this.dataByCategoryList.push(obj);
			});
			
			Object.keys(this.pairOfCategoryAndCount).forEach( (key, idx) => {
				let obj = {};
				obj["id"] = idx;
				obj["category"] = key;
				obj["count"] = this.pairOfCategoryAndCount[key];
				
				this.countByCategoryList.push(obj);
			});
		},
		
		makeCategoryTab : function() {
			drawTemplateToHtml(this.countByCategoryList, "", "reserveCategoryItem", ["_summary_board"]);
			// 초기화로 '전체' 탭에 대해서 focus 시켜준다.
			document.getElementById("reserve_category_0").classList.add("on");
		},
		
		makeAllReservationsList : function() {		// 전체 예약 리스트 그리기
			this.makeConfirmedReservationsList();
			this.makeUsedReservationsList();
			this.makeCanceledReservationsList();
		},
		
		makeConfirmedReservationsList : function() {	// 예약 확정 리스트 그리기
			if(this.dataByCategoryList[1].data.length === 0) {
				document.getElementById("_confirmed").innerHTML += this.defaultTemplates[1];
				document.getElementById("err_confirmed").classList.remove("hide");
			} else {
				drawTemplateToHtml(this.dataByCategoryList[1].data, this.defaultTemplates[1], "reservationItem", ["_confirmed"]);
			}
			
			// '취소' 버튼을 넣어주는 작업 및 그 기능에 대한 셋팅이다.
			this.dataByCategoryList[1].data.forEach( (data) => {
				let cancelBtnElement = document.getElementById(`booking_cancel_${data.reservationInfoId}`);
				cancelBtnElement.classList.remove("hide");
				cancelBtnElement.setAttribute("onclick", `javascript:clickCancelBtn(${data.reservationInfoId})`);
			});
		},
		
		makeUsedReservationsList : function() {		// 이용 완료된 리스트 그리기
			if(this.dataByCategoryList[2].data.length === 0) {
				document.getElementById("_used").innerHTML += this.defaultTemplates[2];
				document.getElementById("err_used").classList.remove("hide");
			} else {
				drawTemplateToHtml(this.dataByCategoryList[2].data, this.defaultTemplates[2], "reservationItem", ["_used"]);
			}
			
			// '예약자 리뷰 남기기' 버튼을 넣어주는 작업 및 그 기능에 대한 셋팅이다.
			this.dataByCategoryList[2].data.forEach( (data) => {
				let reviewBtnElement = document.getElementById(`booking_cancel_${data.reservationInfoId}`);
				reviewBtnElement.classList.remove("hide");	
				reviewBtnElement.setAttribute("onclick", "javascript:linkToReviewWritePage()");
				
				let reviewBtnText = document.getElementById(`btn_text_${data.reservationInfoId}`);
				reviewBtnText.innerText = "예약자 리뷰 남기기";
			});
		},
		
		makeCanceledReservationsList : function() {		// 취소된 예약 리스트 그리기
			if(this.dataByCategoryList[3].data.length === 0) {
				document.getElementById("_cancel").innerHTML += this.defaultTemplates[3];
				document.getElementById("err_cancel").classList.remove("hide");
			} else {
				drawTemplateToHtml(this.dataByCategoryList[3].data, this.defaultTemplates[3], "reservationItem", ["_cancel"]);
			}
		},
		
		handleChangedCategory : function(currentCategoryId) {
			// 화면을 깨끗이 지우고
			this.removeAllReservationsList();
			
			// 해당 탭에 대한 부분만 그려준다.
			switch(currentCategoryId) {
			case 0:		// 전체
				this.makeAllReservationsList();
				break;
			case 1:		// 이용예정
				this.makeConfirmedReservationsList();
				break;
			case 2:		// 이용완료
				this.makeUsedReservationsList();
				break;
			case 3: 	// 취소·환불
				this.makeCanceledReservationsList();
				break;				
			}
		},
		
		removeAllReservationsList : function() {
			// 템플릿으로 그려진 부분들을 모두 제거해준다.
			removeInnerHtml(["_confirmed", "_used", "_cancel"]);
			
			// 그 후 예약 리스트가 없음을 표시하는 부분을 모두 지워준다.
			document.getElementById("err_confirmed").classList.add("hide");
			document.getElementById("err_used").classList.add("hide");
			document.getElementById("err_cancel").classList.add("hide");
		}
};

/* 		myreservation.jsp 페이지 내에서 발생하는 함수 정의! 	 */
function clickCancelBtn(id) {
	// 해당 상품에 대한 취소 팝업 활성화
	document.getElementById(`cancel_${id}`).style.display = "block";
};

// 해당 상품에 대한 취소 여부 팝업창 종료
function exitCancelPopup(id) {
	document.getElementById(`cancel_${id}`).style.display = "none";
};

function handleCancel(id) {
	//send put request
	var request = new XMLHttpRequest();
	
	request.addEventListener("load", () => {
		if(request.status !== 200){
			alert("취소되었습니다.");
			location.reload(true);
		} else {
			alert("서버 내부 오류로 취소 작업에 실패했습니다. \n다시 시도해 주십시오.");
			location.reload(true);
		}
	});
	
	request.open("PUT", `api/reservations/${id}`);
	request.send();
};

function linkToReviewWritePage() {
	location.href = 'reviewWrite';
};


/* 		myreservation.jsp 페이지 내에서 발생하는 이벤트 정의! 	 */
document.getElementById("_my_summary").addEventListener("click", function(e) {
	// 클릭된 해당 카테고리의 아이디 가져오기
	myreservation.currentCategoryId = e.target.closest("li").getAttribute("data-category");
	
	// 이전 탭과 다른 탭을 클릭한 경우만 처리한다.
	if(myreservation.currentCategoryId !== myreservation.prevCategoryId) {
		// 현재 클릭된 카테고리를 focus 시키고
		e.target.closest("a").classList.add("on");
		
		// 이전 카테고리의 focus를 해제한 후, 다음 작업을 위해 현재 카테고리를 이전 카테고리로 지정한다.
		document.getElementById(`reserve_category_${myreservation.prevCategoryId}`).classList.remove("on");
		myreservation.prevCategoryId = myreservation.currentCategoryId;
		
		myreservation.handleChangedCategory(parseInt(myreservation.currentCategoryId));
	}
});

document.addEventListener("DOMContentLoaded", function() {
	myreservation.setGetUrlByReservationEmail(sessionStorage.getItem("reservationEmail"));
	myreservation.sendGetAjax();
	drawMyEmail();
});