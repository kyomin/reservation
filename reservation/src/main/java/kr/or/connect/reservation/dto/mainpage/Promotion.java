package kr.or.connect.reservation.dto.mainpage;

/*
 * 	프로모션 모델 
 */
public class Promotion {
	private int id;					// 프로모션 id
	private int productId;			// 상품 id
	private String productImageUrl;	// 상품 썸네일 이미지 URL
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getProductImageUrl() {
		return productImageUrl;
	}
	
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + ", productImageUrl=" + productImageUrl + "]";
	}
}
