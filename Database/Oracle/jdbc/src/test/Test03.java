package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JdbcUtil;
import vo.DeptVO;

public class Test03 {

	public static void main(String[] args) {

		for(DeptVO data:deptList()) {
			System.out.printf("%2d|%-10s|%s %n",data.getDeptno(),data.getDname(),data.getLoc());
		}
	}

	// JDBC Template
	public static List<DeptVO> deptList() {
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
