package kr.or.connect.reservation.dto.mainpage;


/*
 * 	Category 모델
 */
public class Category {
	private int count;		// Category에 속한 전시상품 수
	private int id;			// Category id
	private String name;	// Category Name
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
