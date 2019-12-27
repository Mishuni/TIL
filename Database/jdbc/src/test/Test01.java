package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test01 {
	public static void main(String[] args) {
		System.out.println("JDBC TEST");
		// emp 테이블 내용 가져오기
		
		// db_info.properties 내용
		// 밑 정보들은 사실은, 여기서 코딩될 것이 아니라
		// 환경설정 정보로 따로 나가야함
		// runtime때가 아니라 compile 때 관리
		// 별도로 서버에서 관리해야할 정보들이기 때문에
		String driver ="oracle.jdbc.OracleDriver"; 
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "SCOTT";
		String pw = "TIGER";
		
		// ojdbc 안에 class 존재 -> 이 중에 하나를 메모리에 올림
		//oracle.jdbc.OracleDriver
		// 처리하고 싶은 sql 구문
		String sql = "select * from emp";
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
			
			con = DriverManager.getConnection(url,user,pw);
			System.out.println(con);
			
			
			
		}catch(Exception e) {
			
		}finally {
			// 자원 반납
		}
		
		
		System.out.println("JDBC TEST END");
		
		
		
	}
}
