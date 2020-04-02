package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JdbcUtil;
import vo.DeptVO;

public class DeptDAO {

	// Dept���� ������ ����ϴ� DAO (Data Acess Object)
	// insert,select,delete,update ���� �ٷ�� �Լ� �ʿ�
	// JDBC Template
	
	// insert
	public void insertDept(String dname, String loc) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "insert into dept(deptno,dname,loc)" + 
		" values((select nvl(max(deptno),0)+10 from dept),?,?)";

		try {
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? �� ����
			ps.setString(1, dname);
			ps.setString(2, loc);
			row = ps.executeUpdate(); // DML ���� ó�� �Լ�
			// ��� �� �ڵ鸵

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, con, ps);
		}

	}
	
	//delete
	public void deleteDept(int deptno) {
		// JDBC Template

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;

		String sql = "delete from dept where deptno = ?";

		try {
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? �� ����
			ps.setInt(1, deptno);
			row = ps.executeUpdate(); // DML ���� ó�� �Լ�
			// ��� �� �ڵ鸵

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, con, ps);
		}

	}
	
	//update
	public void updateDept(int deptno) {
		// JDBC Template

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		//update book set title = '~~~~~' where bookno=3;
		String sql = "update dept set dname = 'Travel' where deptno=?";

		try {
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? �� ����
			ps.setInt(1, deptno);
			row = ps.executeUpdate(); // DML ���� ó�� �Լ�
			// ��� �� �ڵ鸵

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, con, ps);
		}

	}
	
	// select
	public static List<DeptVO> deptList() {
		// JDBC Template
		// dept�� �ҷ����� ���� ��, �� �Լ��� call �ϸ� ��
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from dept";

		List<DeptVO> list = new ArrayList<DeptVO>();
		try {
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? �� ����
			rs = ps.executeQuery(); // select
			// ��� �� �ڵ鸵
			while(rs.next()) {
				// vo ��ü�� ������ ��Ƽ� ������ ����
				// vo ��ü : data transfer object (dto)
				DeptVO vo = new DeptVO(
										rs.getInt("deptno"), 
										rs.getString("dname"), 
										rs.getString("loc")
									  );
				list.add(vo);
				// ����Ʈ�� �� �ϳ��� �������� ���
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, con, ps);
		}
		
		return list;

	}
}
