/*
     	폼 화면(todoForm.jsp)에서 POST 요청한 것이 서블릿에서 잘 처리되어 삽입이 완료되었는지 판단해주는 함수.
     	잘 처리되면 메인 화면으로 리다이렉트, 처리되지 않았으면 알림 메시지!
    	
*/
function isInserted() {
	
	console.log(this.responseText);
	
	if(this.responseText === "success") {
		window.location.replace("/Todo/main");
    } else {
    	alert("삽입 실패");
    }
}