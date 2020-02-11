let comments = {
		/* 		Variables	 */
		comments : [],
		templateId : "reviewItem",
		parentNodeIds : ["review_list"],
		commentsCount : 0,
		
		/* 		Functions	 */
		setComments : function(comments) {
			this.comments = comments;
		},
		
		handleData : function() {
			// 한줄평 개수 등록
			this.commentsCount = this.comments.length;
			document.getElementById("contents_count").innerText = this.commentsCount + "건";
			
			// 가져온 데이터 해당 영역에 그리기!
			drawTemplateToHtml.bind(this)(this.comments, "");
		}
}