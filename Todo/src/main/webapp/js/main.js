/*
    특정 엘리먼트를 클릭했을 때,
    해당 id와 type을 받아서
    이를 변경 처리하는 서블릿으로 보내는 ajax 함수이다. 
*/
function ajax(id, type) {
    var oReq = new XMLHttpRequest();
    
    oReq.addEventListener("load", function(){
        if(this.responseText == 1) {
        	moveRight(id, type.toLowerCase());
        } else {
        	alert("변경 실패");
        }
        
        console.log("id: ", id);
        console.log("type: ", type);
    });
    
    // TodoTypeServlet으로 GET REQUEST!
    oReq.open("GET", "/Todo/type?id=" + id + "&type=" + type);
    
    // 컨텐츠 타입을 헤더에 설정하여 서버에 알린다.
    oReq.setRequestHeader("Content-Type", "application/x-www-from-urlencoded");
    
    oReq.send();
};

/*
	오른쪽 버튼 클릭 시 해당 엘리먼트를 오른쪽으로 이동시키는 함수이다.
*/
function moveRight(id, type) {
	var leftChild = document.getElementById(id);
	var rightType;
	
	if(type === "todo"){
		rightType = "doing";
	} else {
		rightType = "done";
	}
	
	var leftParent = document.getElementById(type);
	var rightParent = document.getElementById(rightType);
	
	console.log("btn"+id);
	console.log(rightType);
	
	// 옮겨지는 아이템 내의 버튼의 속성을 바꾼다. 
	var leftChildBtn = document.getElementById("btn"+id);
	leftChildBtn.setAttribute("onClick", `ajax('${id}', '${rightType.toUpperCase()}');`);
	
	// 왼쪽에서 제거 및 오른쪽에 추가 작업!
	leftParent.removeChild(leftChild);
	rightParent.appendChild(leftChild);
	
	
	// 해당 태그 내부에 원소가 하나라도 배열로 가져오기 때문에!
	var rightBtn = leftChild.getElementsByTagName("button")[0];

	// 오른쪽으로 이동할 영역이 마지막 DONE이라면 버튼 없애주기
	if(rightType === "done") {
		rightBtn.remove();
	} 
}