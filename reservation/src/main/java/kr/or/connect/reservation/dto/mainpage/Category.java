package kr.or.connect.reservation.dto.mainpage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
	private int count;		// Category에 속한 전시상품 수
	private int id;			// Category id
	private String name;	// Category Name
}