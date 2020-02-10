package kr.or.connect.reservation.dto.detail;

import java.util.List;

/*
 * 	상품평
 */
public class Comment {
	private String comment;					// 상품평
	private int commentId;					// 상품평 id
	private List<CommentImage> commentImages;		// 상품평 이미지들 => 추후 이 안에 이너 클래스로 정의해준다. 여기서만 사용한다면.
	private String createDate;				// 생성일
	private String modifyDate;				// 수정일
	private int productId;					// 상품 id
	private String reservationDate;			// 예약일
	private String reservationEmail;		// 예약자 이메일
	private int reservationInfoId;			// 예약자 id
	private String reservationName;			// 예약자 이름
	private String reservationTelephone;	// 예약자 전화번호
	private double score;					// 평점
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getCommentId() {
		return commentId;
	}
	
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public List<CommentImage> getCommentImages() {
		return commentImages;
	}
	
	public void setCommentImages(List<CommentImage> commentImages) {
		this.commentImages = commentImages;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getModifyDate() {
		return modifyDate;
	}
	
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getReservationDate() {
		return reservationDate;
	}
	
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	
	public String getReservationEmail() {
		return reservationEmail;
	}
	
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	
	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	
	public String getReservationName() {
		return reservationName;
	}
	
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	
	public String getReservationTelephone() {
		return reservationTelephone;
	}
	
	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
}