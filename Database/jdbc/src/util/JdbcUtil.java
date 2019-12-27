package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// connection 만들고, 반납하고 를 분리
// connection 관리하는 클래스
public class JdbcUtil {
	// two 티어 베이스
	// 보안상 안전정이지는 않음 (주소 노출)
	// 현업에서는 네트워크 형성을 이용해 구현
	public static Connection getConnection() {

		String driver = "oracle.jdbc.OracleDriver";
		// 127.0.0.1 (나 자신)  70.12.60.111
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
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (Exception e2) {
			System.out.println(e2);

		}
	}
}
