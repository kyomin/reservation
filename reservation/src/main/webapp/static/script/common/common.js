/* 		템플릿이 있는 모든 페이지가 공용으로 사용할 수 있는 함수이다. 	 */
function drawTemplateToHtml(datas, defaultTemplate) {
	var template = document.getElementById(this.templateId).innerHTML;
	var bindTemplate = Handlebars.compile(template);
	var parentNodeCount = this.parentNodeIds.length;
	this.parentNodeIds.forEach( (parentNodeId, parentNodeIndex) => {
		var parentNode = document.getElementById(parentNodeId);
		var parentNodeHTML = parentNode.innerHTML + defaultTemplate;
		parentNode.innerHTML = datas.filter( (data, dataIndex) => {
			if (dataIndex % parentNodeCount === parentNodeIndex) {
				return true;
			}
		}).reduce( (prevHTML, data) => {
			return prevHTML + bindTemplate(data);
		}, parentNodeHTML);
	});
};

function removeInnerHtml() {
	this.parentNodeIds.forEach( (parentNodeId) => {
		var parentNode = document.getElementById(parentNodeId);
		parentNode.innerHTML = "";
	});
};


/* 		object로 관리하는 변수 내에서 ajax 통신을 지원해주는 함수이다. 	 */
function ajax() {
	var oReq = new XMLHttpRequest();
	
	oReq.addEventListener("load", function() {
		var jsonResponse = JSON.parse(oReq.responseText);
		this.handleResponse(jsonResponse);
	}.bind(this));
	
	oReq.open(this.method, this.url);
	oReq.send();
};


/* 		현재 페이지의 url을 파싱하여 key : value를 얻는다. 	 */
function getParams(str) {
	  var params = {};
	  var keyValPairs = str.split("?")[1] && str.split("?")[1].split("&");

	  if (keyValPairs !== undefined) {
	    for (var i = 0; i < keyValPairs.length; i++) {
	      params[keyValPairs[i].split("=")[0]] = decodeURI(
	        keyValPairs[i].split("=")[1],
	        "UTF-8"
	      );
	    }
	  }

	  return params;
};


/* 		자바스크립트 date 타입을 MySQL date 타입으로 변환시켜 준다. 	 */
(function() {
    Date.prototype.toMySQLDateFormat = Date_toYMD;
    function Date_toYMD() {
        var year, month, day, hours, minutes, seconds;        
        year = String(this.getFullYear());
        
        month = String(this.getMonth() + 1);
        if(month.length === 1) {
            month = "0" + month;
        }
        
        day = String(this.getDate());
        if(day.length === 1) {
            day = "0" + day;
        }
        
        hours = String(this.getHours());
        if(hours.length === 1) {
        	hours = "0" + hours;
        }
        
        minutes = String(this.getMinutes());
        if(minutes.length === 1) {
        	minutes = "0" + minutes;
        }
        
        seconds = String(this.getSeconds());
        if(seconds.length === 1) {
        	seconds = "0" + seconds;
        }
        
        return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
    }
})();


/* 		대부분 페이지의 상단에 그려지는 이메일과 로그아웃 버튼을 핸들링한다. 	 */
function drawMyEmail() {
	// 세션에 이메일 정보가 있다면(로그인이 되어 있다면) 이메일 계졍을 로그아웃 버튼과 함께 화면에 그려준다.
	if(sessionStorage.getItem("reservationEmail") !== null) {
		document.getElementById("my_email").innerText = sessionStorage.getItem("reservationEmail");
		document.getElementById("_btn_my").style.right = "70px";
		document.getElementById("_btn_my").style.paddingRight = "0";
		document.getElementById("_logout_btn").classList.remove("hide");
	} else {
		document.getElementById("my_email").innerText = "예약확인";
		document.getElementById("_btn_my").style.right = "0";
		document.getElementById("_btn_my").style.paddingRight = "17.5";
		document.getElementById("_logout_btn").classList.add("hide");
	}
};

//현재 이메일로 접속되어 있는지의 여부에 따라 로그인 페이지와 나의 예매 페이지로 분기되어 링크를 탄다.
function handleLinkToBookingLoginPageAndMyReservationPage() {	
	if(sessionStorage.getItem("reservationEmail") !== null) {
		location.href  = "myreservation";
	} else {
		location.href  = "bookinglogin";
	}
}
 
function handleLogout() {
	// 현재 세션에 저장되어 있는 사용자 계정 정보 및 관련 데이터들 삭제!
	sessionStorage.removeItem("reservationsResponse");
	sessionStorage.removeItem("reservationEmail");
	
	// 그 후 예약 서비스 홈으로 이동!
	location.href = "/reservation";
}