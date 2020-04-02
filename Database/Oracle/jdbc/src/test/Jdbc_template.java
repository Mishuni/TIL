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
			// ? �� ����
			rs = ps.executeQuery();   // select
			row = ps.executeUpdate(); // DML ���� ó�� �Լ�
			// ��� �� �ڵ鸵
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs, con, ps);
		}
		
	}

}
