package kr.or.connect.todo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/** 
 * 		todo 관련 데이터베이스와 연결하기 위한 클래스이다. 
 */
public class DatabaseUtil {
	/** 
	 * 	DB 연결 정보를 위한 변수들.
	 */
	private static String dburl = "jdbc:mysql://10.113.116.52:13306/intern15";
	private static String dbUser = "intern15";
	private static String dbpasswd = "intern15";
	
	static{
		/** 
		 * 	DB 연결을 위한 드라이버 설정하기!
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dburl, dbUser, dbpasswd);
	}
}
