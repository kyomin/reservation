let myreservation = {
		/* 		Variables	 */
		reservations : [],
		size : 0,
		
		/* 		Functions	 */
		setData : function() {
			this.reservations = JSON.parse(sessionStorage.getItem("reservationsResponse")).reservations;
			this.size = JSON.parse(sessionStorage.getItem("reservationsResponse")).size;
		}
};

document.addEventListener("DOMContentLoaded", function() {	
	myreservation.setData();
	drawMyEmail();
});