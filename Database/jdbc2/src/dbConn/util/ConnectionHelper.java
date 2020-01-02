package dbConn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*���ῡ ���õ� ��� ���� ���� Ŭ����
DB ���� ���� �ݺ������� �ڵ��ϴ� �� �ذ�
�ٸ� Ŭ�������� �Ʒ� �ڵ� ������ ���� �ʵ��� ����
// 1. Driver Ŭ������ �ε�
Class.forName("oracle.jdbc.driver.OracleDriver");
// 2. �ε��� Driver Ŭ���� �̿� connection ��û
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
