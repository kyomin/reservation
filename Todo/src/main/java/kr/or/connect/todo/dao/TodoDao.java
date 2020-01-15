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
	 * 	DB 조작 및 연결을 위한 객체
	 */
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/** 
	 * 	DB의 todo 테이블의 데이터를 전달하기 위한 객체
	 */
	private TodoDto todoDto;
	
	
	/** 
	 * 	DB 연결 스트림을 null로 초기화 및 드라이버 설정 메소드 
	 */
	public void initConnection() {
		conn = null;
		ps = null;
		rs = null;
		todoDto = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/** 
	 * 	DB 연결 스트림을 닫는 메소드 
	 */
	public void closeConnection() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/** 
	 * 	브라우저로부터 할 일을 DB의 todo 테이블에 삽입
	 */
	public int addTodo(TodoDto todo) {
		/** 
		 * 	Insert에 성공한 row의 개수를 담을 것이다.  
		 */
		int insertCount = 0;
		
		/** 
		 * 	연결 스트림 null로 설정 및 드라이버 셋팅 
		 */
		initConnection();
		
		String sql = "INSERT INTO todo(title, name, sequence) VALUES (?, ?, ?)";
		
		try {
			/** 
			 * 	연결 스트림 설정 
			 */
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			
			insertCount = ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			/** 
			 * 	최종적으로 열었던 스트림을 닫아준다.
			 */
			closeConnection();
		}
		
		return insertCount;
	}
	
	
	/** 
	 * 	DB의 todo 테이블로부터 저장된 할 일을 모두 불러오기
	 */
	public List<TodoDto> getTodos() {
		List<TodoDto> list = new ArrayList<TodoDto>();
		
		initConnection();
		
		String sql = "SELECT title, name, sequence, type, regdate FROM todo order by sequence";
		
		try {
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			ps = conn.prepareStatement(sql);
			
			/** 
			 * 	쿼리의 결과가 담긴다.
			 */
			rs = ps.executeQuery();
			
			
			/** 
			 * 	불러온 결과의 각 row를 조회하면서
			 */
			while(rs.next()) {
				
				/** 
				 * 	column 값을 추출한다.
				 */
				String title = rs.getString("title");
				String name = rs.getString("name");
				int sequence = rs.getInt("sequence");
				String type = rs.getString("type");
				String regdate = rs.getString("regdate");
				
				/** 
				 * 	DTO 객체를 생성해서 뽑아낸 데이터를 담은 후 리스트에 추가!
				 */ 
				TodoDto todo = new TodoDto(name, regdate, sequence, title, type); 
				list.add(todo);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			/** 
			 * 	최종적으로 열었던 스트림을 닫아준다.
			 */
			closeConnection();
		}
		
		return list;
	}
	
	
	/** 
	 * 	DB의 todo 테이블의 row를 업데이트 한다.
	 */
	public int updateTodo(TodoDto todo) {
		return 1;
	}
}
