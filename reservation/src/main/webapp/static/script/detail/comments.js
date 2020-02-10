let comments = {
		/* 		Variables	 */
		comments : [],
		commentsCount : 0,
		
		/* 		Functions	 */
		setComments : function(comments) {
			this.comments = comments;
		},
		
		handleData : function() {
			// 한줄평 개수 등록
			this.commentsCount = this.comments.length;
			document.getElementById("contents_count").innerText = this.commentsCount + "건";
		}
}