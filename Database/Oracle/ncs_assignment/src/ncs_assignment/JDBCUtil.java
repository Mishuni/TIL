package ncs_assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConnection() {
		String driver = "oracle.jdbc.OracleDriver";
		// 127.0.0.1 (나 자신)
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "SCOTT";
		String pw = "TIGER";
		Connection con = null;
		// 1. Driver클래스를 로딩
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
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
