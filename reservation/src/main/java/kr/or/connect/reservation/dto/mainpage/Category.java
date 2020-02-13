package kr.or.connect.reservation.dto.mainpage;

import lombok.Data;

@Data
public class Category {
	private int count;		// Category에 속한 전시상품 수
	private int id;			// Category id
	private String name;	// Category Name
}