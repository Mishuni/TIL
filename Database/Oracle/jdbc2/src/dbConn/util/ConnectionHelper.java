package dbConn.util;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 연결에 관련된 모든 것을 넣을 클래스
DB 연결 정보 반복적으로 코딩하는 것 해결
다른 클래스에서 아래 코드 구현을 하지 않도록 설계
*/

// ConnectionHelper.getConnecntion("mysql") or ("oracle")
// data source name 으로 어떤 DB와 연결할지 결정

public class ConnectionHelper {
	
	@SuppressWarnings("finally")
	public static Connection getConnection(String dsn) {
		Connection conn = null;
		try {
			if (dsn.equals("mysql")) {
				// mysql인 경우 불러오는 법
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB", "kingsmile", "mysql");

			} else if (dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kingsmile", "oracle");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return conn;
		}
	}

	@SuppressWarnings("finally")
	public static Connection getConnection(String dsn, String user, String pw) {
		Connection conn = null;
		try {
			if (dsn.equals("mysql")) {
				// mysql인 경우 불러오는 법
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB", user, pw);

			} else if (dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", user, pw);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return conn;
		}
	}
}
