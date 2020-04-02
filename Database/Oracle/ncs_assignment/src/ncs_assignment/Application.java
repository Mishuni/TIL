package ncs_assignment;

import javax.swing.JOptionPane;

public class Application {

	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		int choice = 0;
		UserVO me = new UserVO();
		while (choice != -1) {
			choice = Integer.parseInt(JOptionPane.showInputDialog("1:login:" + "\n2:join to member\n3:change pw\n4:delete account \n-1:quit"));
			if (choice == 1) {
				// »ç¿ëÀÚ LOG IN
				while (me.getId().equalsIgnoreCase("")) {
					String id = JOptionPane.showInputDialog("ID:");
					int pw = Integer.parseInt(JOptionPane.showInputDialog("PW:"));
					me = dao.login(id, pw);
					if(!me.getId().equalsIgnoreCase(" ")) {
						
						System.out.println(" login Success");
						System.out.println(me);
					} else  {
						System.out.println("login fail");
						System.out.println("Wrong id,pw");
					}

				}

			} else if (choice == 2) {
				String id = JOptionPane.showInputDialog("ID:");
				int pw = Integer.parseInt(JOptionPane.showInputDialog("PW:"));
				dao.insertUser(id, pw);
			} else if (choice == 3) {
				dao.updateUserPW(me.getId(), Integer.parseInt(JOptionPane.showInputDialog("current PW:")),
						Integer.parseInt(JOptionPane.showInputDialog("new PW:")));
			} else if (choice == 4) {
				try {
					dao.deleteUser(me.getId());
				} catch (Exception e) {
					System.out.println("login first!");
				}

			}
		}

	}

}
