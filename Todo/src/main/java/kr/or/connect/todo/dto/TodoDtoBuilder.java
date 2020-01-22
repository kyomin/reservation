package kr.or.connect.todo.dto;

/** 
 * 		이 클래스는 DTO의 생성을 위한 빌더 객체이다. 
 */
public class TodoDtoBuilder {
	private long id;
	private String name;
	private String regdate;
	private int sequence;
	private String title;
	private String type;
	
	public TodoDtoBuilder setId(long id) {
		this.id = id;
		return this;
	}
	
	public TodoDtoBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public TodoDtoBuilder setRegdate(String regdate) {
		this.regdate = regdate;
		return this;
	}
	
	public TodoDtoBuilder setSequence(int sequence) {
		this.sequence = sequence;
		return this;
	}
	
	public TodoDtoBuilder setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public TodoDtoBuilder setType(String type) {
		this.type = type;
		return this;
	}
	
	public TodoDto build() {
		TodoDto todoDto = new TodoDto(id, name, regdate, sequence, title, type);
		return todoDto;
	}
}
