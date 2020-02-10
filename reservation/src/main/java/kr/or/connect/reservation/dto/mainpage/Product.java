package kr.or.connect.reservation.dto.mainpage;

/*
 * 	상품 모델 
 */
public class Product {
	// 쿼리문에서 AS를 통해 테이블의 컬럼명을 이렇게 바꿔줘서 서로 바인딩 시켜준다.
	// 그렇지 않으면 JDBC가 테이블의 컬럼과 DTO의 필드를 매핑하지 못한다.
	private int displayInfoId;			// 전시 id
	private int productId;				// 상품 id
	private String productDescription;	// 상품 설명
	private String placeName;			// 전시 장소 명
	private String productContent;		// 상품 상세 설명
	private String productImageUrl;		// 상품 썸네일 이미지 URL
	
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
}
