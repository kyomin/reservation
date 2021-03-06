// submit variable
let requestData = {
	"reservationEmail" : ""
};


/* 		reserve.jsp 페이지 내에서 발생하는 함수 정의! 	 */
function checkValidation() {
	// 이메일 입력 검증
	if(requestData["reservationEmail"].match(/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/) === null) {
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
	
	// POST AJAX Request!
	var xhr = new XMLHttpRequest();

	xhr.onload = function() {
	  if (xhr.status === 200 || xhr.status === 201) {	// 로그인 성공!
		  // 성공한 로그인 정보를 세션에 저장
		  sessionStorage.setItem("reservationEmail", requestData["reservationEmail"]);
		  
		  // 로그인 한 사람의 예약 정보 페이지로 이동 
		  location.href = "myreservation";
	  } else if(xhr.status === 400){	
		  alert("form 형식 오류!!");
		  location.reload(true);
	  } else {
		  alert("이메일이 조회되지 않습니다. \n다시 한 번 정확히 입력하십시오.");
		  location.reload(true);
	  }
	};
	
	xhr.open('POST', "api/login");
	xhr.setRequestHeader('Content-Type', 'application/json');	// 컨텐츠타입을 json으로
	xhr.send(JSON.stringify(requestData));	// 데이터를 stringify해서 보냄
};


/* 		bookinglogin.jsp 페이지 내에서 발생하는 이벤트 정의! 	 */
document.getElementById("resrv_id").addEventListener('change', function(e) {
	requestData["reservationEmail"] = e.target.value;
	
	if(e.target.value.match(/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/) === null) {
		document.getElementById("email_validation").classList.remove("hide");
	} else {
		document.getElementById("email_validation").classList.add("hide");
	}
});