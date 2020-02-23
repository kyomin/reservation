package kr.or.connect.reservation.dto.detail;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetail {
	private double averageScore;
	private List<Comment> comments;
	private DisplayInfo displayInfo;
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> productPrices;
}