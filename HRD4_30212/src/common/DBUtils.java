package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
	//DB연결
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("오류 발생rs: " + e);
			}
			
		}
		close(conn, ps);
		
	}

	public static void close(Connection conn, PreparedStatement ps) {
		
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("오류발생ps: " + e);
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류발생conn: " + e);
			}
		}
		
	}
	


}

