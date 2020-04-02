package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test01 {
	public static void main(String[] args) {
		System.out.println("JDBC TEST\n");
		// emp ���̺� ���� ��������
		
		// db_info.properties ����
		// �� �������� �����, ���⼭ �ڵ��� ���� �ƴ϶�
		// ȯ�漳�� ������ ���� ��������
		// runtime���� �ƴ϶� compile �� �����ؾ� �ϴ�
		// ������ �������� �����ؾ��� �������̱� ������
		String driver ="oracle.jdbc.OracleDriver"; 
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "SCOTT";
		String pw = "TIGER";
		
		// ojdbc �ȿ� class ���� -> �� �߿� �ϳ��� �޸𸮿� �ø�
		//oracle.jdbc.OracleDriver
		// ó���ϰ� ���� sql ����
		String sql = "select * from dept";
		Connection con = null; // Ư�� db ����
		PreparedStatement ps = null; // sql ���� ����
		ResultSet rs = null; // select ������ �����
		// select �� ������� ResultSet ����
		// update,delete,insert�� ������� ���� (�� �� row)
		
		// db ���� �ڵ�
		// ����ó���� �⺻
		
		try {
			// 1. DriverŬ������ �ε�
			Class.forName(driver);
			// compile Ÿ�ӿ� �����ǰ� ���� �ʱ� ����
			// �̷� ������� ��ü����
			// ���� ���̰� ������ �������� �ʵ���
			// ojdbc6.jar �� 
			// oracle.jdbc.OracleDriver Ŭ������ ����
			
			// 2. �ε��� DriverŬ������ �̿��ؼ�
			//    Connection��û(url, user, pwd)
			con = DriverManager.getConnection(url,user,pw);
			//System.out.println(con);
			
			// 3. ������Connection���κ��� Statement����
			// ó���ؾ� �� ���� �Ѱ��ֱ�
			ps = con.prepareStatement(sql);
			
			// 4. ������ Statement�� �̿��ؼ� sql����
			rs = ps.executeQuery();
			
			// 5. ��� ó��
			while(rs.next()) {
				System.out.print(rs.getString("deptno")+" ");
				System.out.print(rs.getString("dname")+" ");
				System.out.print(rs.getString("loc")+" ");
				System.out.println("\n-----------------------");
			}
			
		}catch(Exception e) {
			
			// 6. SQLException ó��
			System.out.println(e);
			
		}finally {
			// 7. �ڿ�����,�ݳ�(connection, statement, resultset)
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		
		System.out.println("\nJDBC TEST END");
		
	}
}
