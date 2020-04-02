package APP;

import dao.DeptDAO;
import vo.DeptVO;

public class Application {

	public static void main(String[] args) {
		
		DeptDAO dao = new DeptDAO();
		for(DeptVO data : dao.deptList()) {
			System.out.println(data);
		}
		System.out.println("END");
		
	}

}
