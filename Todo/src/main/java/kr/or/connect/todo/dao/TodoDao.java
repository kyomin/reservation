package kr.or.connect.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.todo.dto.TodoDto;


/** 
 * 		이 클래스는 todo 테이블의 데이터를 조회 및 조작하기 위한 클래스이다. 
 */
public class TodoDao {
	/** 
	 * 	DB 연결 정보를 위한 변수들.
	 */
	private static String dburl = "jdbc:mysql://10.113.116.52:13306/intern15";
	private static String dbUser = "intern15";
	private static String dbpasswd = "intern15";
	
	
	/** 
	 * 	드라이버 설정 메소드 
	 */
	private void settingDriver() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/** 
	 * 	Insert Into todo Table!
	 * 	브라우저로부터 할 일을 DB의 todo 테이블에 삽입
	 */
	public int addTodo(TodoDto todo) {
		/** 
		 * 	Insert에 성공한 row의 개수를 담을 것이다.  
		 */
		int insertCount = 0;
		
		/** 
		 * 	MySQL 드라이버 설정!  
		 */
		settingDriver();
		
		/** 
		 * 	쿼리문 작성!  
		 */
		String sql = "INSERT INTO todo(title, name, sequence) VALUES (?, ?, ?)";
		
		/** 
		 * 	try 소괄호 내부에 연결 스트림 설정!
		 * 	이 문법을 사용하면 연결 스트림을 닫는 코드가 불필요해진다.
		 */
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
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
	 * 	Select * from todo Table!
	 * 	DB의 todo 테이블로부터 저장된 할 일을 모두 불러오기
	 */
	public List<TodoDto> getTodos() {
		List<TodoDto> list = new ArrayList<TodoDto>();
		
		/** 
		 * 	MySQL 드라이버 설정!  
		 */
		settingDriver();
		
		/** 
		 * 	쿼리문 작성  
		 */
		String sql = "SELECT title, name, sequence, type, regdate FROM todo ORDER BY sequence";
		
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			/** 
			 * 	쿼리의 결과가 담긴다.
			 */
			try (ResultSet rs = ps.executeQuery()) { 
			
			/** 
			 * 	불러온 결과의 각 row를 조회하면서
			 */
				while(rs.next()) {
					/** 
					 * 	DTO 객체를 생성해서 뽑아낸 column 값을 생성자를 통해 담은 후 리스트에 추가!
					 */ 
					TodoDto todo = new TodoDto(rs.getString("name"), rs.getString("regdate"), rs.getInt("sequence"), rs.getString("title"), rs.getString("type")); 
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
	 * 	DB의 todo 테이블의 row를 업데이트 한다.
	 * 	추후 작업 요함!
	 */
	public int updateTodo(TodoDto todo) {
		return 1;
	}
}
