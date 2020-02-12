let display_info = {
		/* 		Variables	 */
		displayInfo : {},
		descriptionContainerInCommentList : [],
		
		/* 		Functions	 */
		setDisplayInfo : function(displayInfo) {
			this.displayInfo = displayInfo;
		},
		
		handleData : function() {
			// comment 리스트 부분에 타이틀 넣기
			this.descriptionContainerInCommentList = document.querySelectorAll(".resoc_name");
			
			this.descriptionContainerInCommentList.forEach( description => {
				description.innerText = this.displayInfo.productDescription;
			});
		}
};