package dbConn.util;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 ���ῡ ���õ� ��� ���� ���� Ŭ����
DB ���� ���� �ݺ������� �ڵ��ϴ� �� �ذ�
�ٸ� Ŭ�������� �Ʒ� �ڵ� ������ ���� �ʵ��� ����
*/

// ConnectionHelper.getConnecntion("mysql") or ("oracle")
// data source name ���� � DB�� �������� ����

public class ConnectionHelper {
	
	@SuppressWarnings("finally")
	public static Connection getConnection(String dsn) {
		Connection conn = null;
		try {
			if (dsn.equals("mysql")) {
				// mysql�� ��� �ҷ����� ��
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
				// mysql�� ��� �ҷ����� ��
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
