package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import util.JdbcUtil;
// 만들어놓은 JdbcUtil 클래스 사용하기
public class Test02 {
	public static void main(String[] args) {
		System.out.println("JDBC TEST\n");
		// emp 테이블 내용 가져오기

		String driver ="oracle.jdbc.OracleDriver"; 
		
		// ojdbc 안에 class 존재 -> 이 중에 하나를 메모리에 올림
		//oracle.jdbc.OracleDriver
		// 처리하고 싶은 sql 구문
		//String sql = "select * from emp";
		String sql = "select * from emp where deptno = ?";
		// '?' 가  있는 sql 구문은 PreparedStatement가 처리할 것
		Connection con = null; // 특정 db 연결
		PreparedStatement ps = null; // sql 구문 관리
		ResultSet rs = null; // select 구문의 결과값
		// select 의 결과값은 ResultSet 구조
		// update,delete,insert의 결과값은 정수 (몇 개 row)
		
		// db 연동 코드
		// 예외처리는 기본
		try {
			// 1. Driver클래스를 로딩
			Class.forName(driver);
			// compile 타임에 결정되게 하지 않기 위해
			// 이런 방식으로 객체생성
			// 같은 아이가 여러개 생성되지 않도록
			// ojdbc6.jar 속 
			// oracle.jdbc.OracleDriver 클래스가 생성
			
			// 2. 로딩된 Driver클래스를 이용해서
			//    Connection요청(url, user, pwd)
			con = JdbcUtil.getConnection();
			//System.out.println(con);
			
			// 3. 생성된Connection으로부터 Statement생성
			// 처리해야 할 구문 넘겨주기
			ps = con.prepareStatement(sql);
			
			// ? 값 세팅
			//ps.setInt(1, 20); // 첫 번째 물음표에 20이 들어감
			String deptno = JOptionPane.showInputDialog("deptno");
			ps.setInt(1,Integer.parseInt(deptno)); 
			
			// 4. 생성된 Statement를 이용해서 sql수행
			rs = ps.executeQuery();
			
			// 5. 결과 처리
			int n = 0;
			while(rs.next()) {
				n++;
				System.out.printf("%10s",rs.getString("ename")+"|");
				System.out.print(rs.getInt("deptno")+"|");
				System.out.printf("%10s",rs.getString("job")+"|");
				System.out.printf("%5s",rs.getString("sal")+"|");
				System.out.println(rs.getDate("hiredate"));
				System.out.println("\n-----------------------------");
			}
			System.out.printf("총 %d명 입니다.\n",n);
		}catch(Exception e) {
			
			// 6. SQLException 처리
			System.out.println(e);
			
		}finally {
			// 7. 자원정리,반납(connection, statement, resultset)
			JdbcUtil.close(rs, con, ps);
		}
		
		System.out.println("\nJDBC TEST END");
		
	}
}
