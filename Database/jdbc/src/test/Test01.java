package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test01 {
	public static void main(String[] args) {
		System.out.println("JDBC TEST");
		// emp ���̺� ���� ��������
		
		// db_info.properties ����
		// �� �������� �����, ���⼭ �ڵ��� ���� �ƴ϶�
		// ȯ�漳�� ������ ���� ��������
		// runtime���� �ƴ϶� compile �� ����
		// ������ �������� �����ؾ��� �������̱� ������
		String driver ="oracle.jdbc.OracleDriver"; 
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "SCOTT";
		String pw = "TIGER";
		
		// ojdbc �ȿ� class ���� -> �� �߿� �ϳ��� �޸𸮿� �ø�
		//oracle.jdbc.OracleDriver
		// ó���ϰ� ���� sql ����
		String sql = "select * from emp";
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
			
			con = DriverManager.getConnection(url,user,pw);
			System.out.println(con);
			
			
			
		}catch(Exception e) {
			
		}finally {
			// �ڿ� �ݳ�
		}
		
		
		System.out.println("JDBC TEST END");
		
		
		
	}
}
