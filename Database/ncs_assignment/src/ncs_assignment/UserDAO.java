package ncs_assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	// insert
	public int insertUser(String id, int pw) {
		Connection con = null;
		PreparedStatement ps = null;
		int row = 0;
		ResultSet rs = null;
		String sql = "insert into users(id,pw) values(?,?)";
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, pw);
			row = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, con, ps);
		}
		return row;
	}

	// delete
	public int deleteUser(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "delete from users where id=?";

		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			row = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, con, ps);
		}

		return row;
	}
	
	// update
	// 비밀번호 변경
	public int updateUserPW(String id, int oldpw, int newpw) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		//update book set title = '~~~~~' where bookno=3;
		String sql = "update users set pw=? "
				+ "where id = ? and pw = ?";
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, newpw);
			ps.setString(2, id);
			ps.setInt(3, oldpw);
			row = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, con, ps);
		}
		return row;
	}
	
	// login
	public UserVO login(String id, int pw) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserVO user = null;
		String sql = "select * from users "
				+ "where id=? and pw=?";
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, pw);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new UserVO(
						rs.getString("id"),
						rs.getInt("pw")
						);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 만약 user 가 null 값이라면 로그인은 실패
		return user;
	}

}
