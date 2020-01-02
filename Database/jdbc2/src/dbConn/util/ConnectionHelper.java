package dbConn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*연결에 관련된 모든 것을 넣을 클래스
DB 연결 정보 반복적으로 코딩하는 것 해결
다른 클래스에서 아래 코드 구현을 하지 않도록 설계
// 1. Driver 클래스를 로딩
Class.forName("oracle.jdbc.driver.OracleDriver");
// 2. 로딩된 Driver 클래스 이용 connection 요청
conn = DriverManager.getConnection(
		"jdbc:oracle:thin:@localhost:1521:xe",
		"kingsmile",
		"oracle");*/

public class ConnectionHelper {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("load success!!");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kingsmile",
					"oracle");
			System.out.println("connection success!!");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		

	}
}
