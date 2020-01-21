package kr.or.connect.todo.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/** 
 * 		이 클래스는 todo 테이블의 데이터를 전달하기 위한 목적의 클래스이다. 
 */
public class TodoDto {
	private long id;
	private String name;
	private String regdate;
	private int sequence;
	private String title;
	private String type;
	
	public TodoDto(long id, String name, String regdate, int sequence, String title, String type) {
		this.id = id;
		this.name = name;
		this.regdate = regdate;
		this.sequence = sequence;
		this.title = title;
		this.type = type;
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRegdate() {
		return regdate;
	}
	
	public int getSequence() {
		return sequence;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "TodoDto [id=" + id + ", name=" + name + ", regdate=" + regdate
				+ ", sequence=" + sequence + ", title=" + title + ", type="
				+ type + "]";
	}
}
