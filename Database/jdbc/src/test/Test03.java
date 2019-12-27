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
