package ncs_assignment;

import javax.swing.JOptionPane;

public class Application {

	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		
		UserVO me = null;
		// »ç¿ëÀÚ LOG IN
		while (me == null) {
			String id = JOptionPane.showInputDialog("ID:");
			int pw = Integer.parseInt(JOptionPane.showInputDialog("PW:"));
			me = dao.login(id, pw);
			if (me == null) {
				System.out.println("login fail");
				System.out.println("Wrong id,pw");
			} else {
				System.out.println(" login Success");
				System.out.println(me);
			}
		}
		
		dao.updateUserPW(
				me.getId(),
				Integer.parseInt(JOptionPane.showInputDialog("current PW:")), 
				Integer.parseInt(JOptionPane.showInputDialog("new PW:"))
				);
		dao.deleteUser("z");
		dao.insertUser("z", 55);
		
	}

}
