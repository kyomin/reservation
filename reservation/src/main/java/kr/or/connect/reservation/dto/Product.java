package kr.or.connect.reservation.dto;

public class Product {
	// 쿼리문에서 AS를 통해 테이블의 컬럼명을 이렇게 바꿔줘서 서로 바인딩 시켜준다.
	// 그렇지 않으면 JDBC가 테이블의 컬럼과 DTO의 필드를 매핑하지 못한다.
	private int displayInfoId;
	private int productId;
	private String productDescription;
	private String placeName;
	private String productContent;
	private String productImageUrl;
	
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getProductDescription() {
		return productDescription;
	}
	
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public String getPlaceName() {
		return placeName;
	}
	
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
	public String getProductContent() {
		return productContent;
	}
	
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	
	public String getProductImageUrl() {
		return productImageUrl;
	}
	
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	
	@Override
	public String toString() {
		return "Product [displayInfoId=" + displayInfoId + ", productId=" + productId + ", productDescription="
				+ productDescription + ", placeName=" + placeName + ", productContent=" + productContent
				+ ", productImageUrl=" + productImageUrl + "]";
	}
}
