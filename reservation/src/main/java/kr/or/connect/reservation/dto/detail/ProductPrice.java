package kr.or.connect.reservation.dto.detail;

/*
 * 	상품 가격 모델
 */
public class ProductPrice {
	private String createDate;		// 생성일
	private double discountRate;	// 할인율
	private String modifyDate;		// 수정일
	private int price;				// 가격
	private String priceTypeName;	// 가격 타입명
	private int productId;			// 상품 id
	private int productPriceId;		// 상품 가격 id
	
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public double getDiscountRate() {
		return discountRate;
	}
	
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	
	public String getModifyDate() {
		return modifyDate;
	}
	
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getpriceTypeName() {
		return priceTypeName;
	}
	
	public void setpriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getProductPriceId() {
		return productPriceId;
	}
	
	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}
}