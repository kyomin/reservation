let comments = {
		/* 		Variables	 */
		comments : [],
		commentsCount : 0,
		
		/* 		Functions	 */
		setData : function(comments) {
			this.commentsCount = comments.length;
			this.comments = comments.slice(0, 3);
			
			this.comments.map( (comment) => {
				comment.score = comment.score.toFixed(1);
			})
		},
		
		handleData : function() {
			// 한줄평 개수 등록
			document.getElementById("contents_count").innerText = this.commentsCount + "건";
			
			// 가져온 데이터 해당 영역에 이미지 없이 그리기! (detail 페이지에서의 comment는 3개 까지만 노출)
			drawTemplateToHtml(this.comments, "", "reviewItem", ["review_list"]);
			
			// 이미지를 가지는 것들만 골라서 삽입하기!
			let reviews = document.querySelectorAll(".review_area");
			
			this.comments.map( (comment, idx) => {
				if(comment.commentImages.length !== 0) {
					let reviewImage = new DOMParser().parseFromString(`
					<div class="thumb_area">
                	<a class="thumb" title="이미지 크게 보기"> 
						<img width="90" height="90" class="img_vertical_top" src="static/${comment.commentImages[0].saveFileName}" alt="리뷰이미지"> 
					</a> 
					<span class="img_count">${comment.commentImages.length}</span>                                                
					</div>`, 'text/html').body.firstChild;
					
					reviews[idx].insertBefore(reviewImage, reviews[idx].firstElementChild);
					reviews[idx].classList.remove("no_img");
				}
			});
		}
};