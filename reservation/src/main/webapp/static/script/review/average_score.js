let average_score = {
		/* 		Variables	 */
		averageScore : 0,
		fiveStarRating : document.getElementById("five_star_rating"),	// 별점 element
		
		/* 		Functions	 */
		setAverageScore : function(averageScore) {
			// 평점 소수 첫째 자리에서 반올림 해서 담기!
			this.averageScore = Math.round(averageScore*10)/10;
		},
		
		handleData : function() {
			// 별점 셋팅!
			var ratio = (this.averageScore / 5) * 100;
			this.fiveStarRating.style.width = `${ratio}%`;
			
			// 평점 기입!
			document.getElementById("average_score").innerText = this.averageScore;
		}
}