let display_info = {
		/* 		Variables	 */
		displayInfo : {},
		
		/* 		Functions	 */
		setData : function(displayInfo) {
			this.displayInfo = displayInfo;
		},
		
		handleData : function() {
			document.getElementById("_preview_txt_tit").innerText = this.displayInfo.productDescription;
			document.getElementById("place_lot").innerText = this.displayInfo.placeLot;
			document.getElementById("opening_hours").innerText = this.displayInfo.openingHours;
		}
};