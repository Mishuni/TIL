package App;

import java.util.Scanner;

public class MyApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String cmd = null;
		System.out.println("Put your menu number");
		System.out.println("1. input \n2. edit \n3. delete \n4. " + "search\n5. list\n9. exit");
		
		while (true) {
			int n = sc.nextInt();
			cmd = sc.nextLine().trim();
			switch (n) {

			case 1:
				
				break;
			case 2:
				System.out.println();
				break;
			case 3:
				System.out.println();
				break;
			case 4:
				System.out.println();
				break;
			case 5:
				System.out.println();
				break;
			case 9:
				System.out.println("really?(yes)");
				if ((sc.next()).equalsIgnoreCase("yes"))
					return; 
			default:
				System.out.println("Nothing");

			}
		}
	}

}
