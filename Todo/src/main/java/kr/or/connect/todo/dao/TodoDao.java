package kr.or.connect.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.todo.dto.TodoDto;
import kr.or.connect.todo.utils.DatabaseUtil;


/** 
 * 		이 클래스는 todo 테이블의 데이터를 조회 및 조작하기 위한 클래스이다. 
 */
public class TodoDao {	
	/** 
	 * 		브라우저로부터 할 일을 입력받아 DB의 todo 테이블에 삽입
	 * 		@return		Insert에 성공한 행의 개수
	 */
	public int addTodo(TodoDto todo) {
		int insertCount = 0;
		
		String sql = "INSERT INTO todo(title, name, sequence) VALUES (?, ?, ?)";
		
		/** 
		 * 		try 소괄호 내부에 연결 스트림 설정!
		 * 		이 문법을 사용하면 연결 스트림을 닫는 코드가 불필요해진다.
		 */
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			
			insertCount = ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} 
		
		return insertCount;
	}
	
	/** 
	 * 		Select * from todo Table!
	 * 		DB의 todo 테이블로부터 저장된 할 일을 모두 불러오기
	 * 		@return		모든 todo 리스트
	 */
	public List<TodoDto> getTodos() {
		List<TodoDto> list = new ArrayList<TodoDto>();
		
		//	type으로 오름차순 정렬 후에 각 타입 내부에서 regdate로 오름차순 정렬한다.
		String sql = "SELECT id, title, name, sequence, type, regdate "
				+ "FROM todo ORDER BY type, regdate";
		
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			 
			//	rs 객체에는 쿼리의 결과가 담긴다.	 
			try (ResultSet rs = ps.executeQuery()) { 
			
				//	불러온 결과의 각 row를 조회하면서
				while(rs.next()) {
					//	DTO 객체를 생성해서 뽑아낸 column 값을 생성자를 통해 담은 후 리스트에 추가!	
					TodoDto todo = new TodoDto(rs.getLong("id"), rs.getString("name"), rs.getString("regdate"), 
							rs.getInt("sequence"), rs.getString("title"), rs.getString("type")); 
					
					list.add(todo);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} 
		
		return list;
	}
	
	/** 
	 * 		DB의 todo 테이블의 row를 업데이트 한다.
	 * 		@return		Update에 성공한 행의 개수
	 */
	public int updateTodo(TodoDto todo) {
		int updateCount = 0;
		
		String sql = "UPDATE todo SET type = ? WHERE id = ?";
		
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			String type = todo.getType();
			long id = todo.getId();
			
			switch(type) {
			case "TODO":
				ps.setString(1, "DOING");
				ps.setLong(2, id);
				break;
				
			case "DOING":
				ps.setString(1, "DONE");
				ps.setLong(2, id);
				break;
			}
	
			updateCount = ps.executeUpdate();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} 
		
		return updateCount;
	}
}
