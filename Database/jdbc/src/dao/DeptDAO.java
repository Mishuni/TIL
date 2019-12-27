package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JdbcUtil;
import vo.DeptVO;

public class DeptDAO {

	// Dept와의 연결을 담당하는 DAO (Data Acess Object)
	// insert,select,delete,update 등을 다루는 함수 필요
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
			// ? 값 세팅
			ps.setString(1, dname);
			ps.setString(2, loc);
			row = ps.executeUpdate(); // DML 구문 처리 함수
			// 결과 값 핸들링

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
			// ? 값 세팅
			ps.setInt(1, deptno);
			row = ps.executeUpdate(); // DML 구문 처리 함수
			// 결과 값 핸들링

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
			// ? 값 세팅
			ps.setInt(1, deptno);
			row = ps.executeUpdate(); // DML 구문 처리 함수
			// 결과 값 핸들링

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, con, ps);
		}

	}
	
	// select
	public static List<DeptVO> deptList() {
		// JDBC Template
		// dept를 불러오고 싶을 때, 이 함수를 call 하면 됨
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from dept";

		List<DeptVO> list = new ArrayList<DeptVO>();
		try {
			con = JdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ? 값 세팅
			rs = ps.executeQuery(); // select
			// 결과 값 핸들링
			while(rs.next()) {
				// vo 객체에 내용을 담아서 데이터 저장
				// vo 객체 : data transfer object (dto)
				DeptVO vo = new DeptVO(
										rs.getInt("deptno"), 
										rs.getString("dname"), 
										rs.getString("loc")
									  );
				list.add(vo);
				// 리스트에 행 하나씩 차곡차곡 담기
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, con, ps);
		}
		
		return list;

	}
}
