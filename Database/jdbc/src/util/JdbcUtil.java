package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// connection �����, �ݳ��ϰ� �� �и�
// connection �����ϴ� Ŭ����
public class JdbcUtil {
	// two Ƽ�� ���̽�
	// ���Ȼ� ������������ ���� (�ּ� ����)
	// ���������� ��Ʈ��ũ ������ �̿��� ����
	public static Connection getConnection() {

		String driver = "oracle.jdbc.OracleDriver";
		// 127.0.0.1 (�� �ڽ�)  70.12.60.111
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "SCOTT";
		String pw = "TIGER";
		Connection con = null;
		// 1. DriverŬ������ �ε�
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pw);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

	public static void close(ResultSet rs, Connection con, Statement ps) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (Exception e2) {
			System.out.println(e2);

		}
	}
}
