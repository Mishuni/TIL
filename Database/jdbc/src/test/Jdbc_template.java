package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JdbcUtil;

public class Jdbc_template {

	public static void main(String[] args) {
		
		
		
	}
	
	// JDBC Template
	public void temp() {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		
		String sql = "select * from emp";
		
		try {
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅
			rs = ps.executeQuery();   // select
			row = ps.executeUpdate(); // DML 구문 처리 함수
			// 결과 값 핸들링
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs, con, ps);
		}
		
	}

}
