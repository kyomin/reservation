package kr.or.connect.reservation.dto.detail;

import java.util.List;

import lombok.Data;

@Data
public class ProductDetail {
	private Double averageScore;
	private List<Comment> comments;
	private DisplayInfo displayInfo;
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> productPrices;
}
