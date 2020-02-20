// submit variable
var reservationEmail = "";


/* 		reserve.jsp 페이지 내에서 발생하는 함수 정의! 	 */
function checkValidation() {
	// 이메일 입력 검증
	if(reservationEmail.match(/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/) === null) {
		alert("형식에 맞춰 이메일을 작성해 주세요!");
		return false;
	}
	
	return true;
};

function handleSubmit() {
	// form 입력 항목이 유효하지 않다면 submit 처리 철회!
	if(!checkValidation()) {
		return;
	}
	
	// GET AJAX Request!
	var xhr = new XMLHttpRequest();

	xhr.onload = function() {
	  if (xhr.status === 200 || xhr.status === 201) {	
		  // 응답 객체 받고 세션에 셋팅
		  sessionStorage.setItem("reservationsResponse", xhr.responseText);
		  sessionStorage.setItem("reservationEmail", reservationEmail);
		  		  
		  location.href = "myreservation";
	  } else if(xhr.status === 400){	
		  location.reload(true);
		  alert("form 형식 오류!!");
	  } else {
		  location.reload(true);
		  alert("이메일이 조회되지 않습니다. \n다시 한 번 정확히 입력하십시오.");
	  }
	};
	
	xhr.open('GET', `api/reservations?reservationEmail=${reservationEmail }`);
	xhr.send();
};


/* 		bookinglogin.jsp 페이지 내에서 발생하는 이벤트 정의! 	 */
document.getElementById("resrv_id").addEventListener('change', function(e) {
	reservationEmail = e.target.value;
	
	if(e.target.value.match(/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/) === null) {
		document.getElementById("email_validation").classList.remove("hide");
	} else {
		document.getElementById("email_validation").classList.add("hide");
	}
});