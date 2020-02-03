package kr.or.connect.reservation.dto;

public class Category {
	int count;
	int id;
	String name;
	
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
	
	@Override
	public String toString() {
		return "Category [count=" + count + ", id=" + id + ", name=" + name + "]";
	}
}
