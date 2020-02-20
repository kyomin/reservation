let myreservation = {
		/* 		Variables	 */
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
		setData : function() {
			// 서버로부터 받아온 데이터 셋팅 후
			this.reservations = JSON.parse(sessionStorage.getItem("reservationsResponse")).reservations;
			this.size = JSON.parse(sessionStorage.getItem("reservationsResponse")).size;
			
			// 예약 리스트의 각 총 가격에 대해 천 단위로 콤마 찍어주는 작업 수행
			this.reservations.forEach( reservation => {
				reservation.totalPrice = reservation.totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			})
			
			// 데이터 분류
			this.classifyData();
		},
		
		classifyData : function() {
			// 일단 '전체' 카테고리에 대한 데이터 분류 처리를 해준다.
			this.pairOfCategoryAndData["전체"] = this.reservations;
			this.pairOfCategoryAndCount["전체"] = this.size;
			
			// 조건에 따라 나머지 카테고리에 대한 분류 시작
			this.reservations.forEach( (reservation) => {
				// 현재 날짜를 얻어서 reservationDate와 비교할 것이다.
				let today = new Date();
				today.setDate = today.toMySQLDateFormat();
				
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
			
			this.handleData();
		},
		
		handleData : function() {
			this.makeCategoryTab();
			this.makeReservationsList();
		},
		
		makeCategoryTab : function() {
			drawTemplateToHtml(this.countByCategoryList, "", "reserveCategoryItem", ["_summary_board"]);
			// 초기화로 '전체' 탭에 대해서 focus 시켜준다.
			document.getElementById("reserve_category_0").classList.add("on");
		},
		
		makeReservationsList : function() {
			// 예약 확정 리스트 그리기
			if(this.dataByCategoryList[1].data.length === 0) {
				document.getElementById("err_confirmed").classList.remove("hide");
			} else {
				drawTemplateToHtml(this.dataByCategoryList[1].data, this.defaultTemplates[1], "reservationItem", ["_confirmed"]);
			}
			
			// 이용 완료된 리스트 그리기
			if(this.dataByCategoryList[2].data.length === 0) {
				document.getElementById("err_used").classList.remove("hide");
			} else {
				drawTemplateToHtml(this.dataByCategoryList[2].data, this.defaultTemplates[2], "reservationItem", ["_used"]);
			}
			
			// 취소된 예약 리스트 그리기
			if(this.dataByCategoryList[3].data.length === 0) {
				document.getElementById("err_cancel").classList.remove("hide");
			} else {
				drawTemplateToHtml(this.dataByCategoryList[3].data, this.defaultTemplates[3], "reservationItem", ["_cancel"]);
			}
		}
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
	}
});

document.addEventListener("DOMContentLoaded", function() {	
	myreservation.setData();
	drawMyEmail();
});