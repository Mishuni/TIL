package day03;
import java.util.Scanner;
public class Test01 {

	public static void main(String[] args) {
		// equals() : 객체 비교
		Scanner sc = new Scanner(System.in);
		System.out.println("name");
		String name = sc.nextLine();
		System.out.println("score");
		int score = Integer.parseInt(sc.nextLine());
		System.out.println("Math");
		int score2 = sc.nextInt();
		System.out.println("english");
		int score3 = sc.nextInt();
		sc.nextLine();
		sc.close();
		
		System.out.println(name);
		System.out.println(score);
		double sum = score + score2 + score3;
		double avg = (sum)/2;
		System.out.printf("ave is %2.2f\n",avg);
		System.out.printf((avg>90)?"pass":"fail");
		
		return;
	}

}
