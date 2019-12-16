package day03;
import java.io.FileInputStream;
import java.util.Scanner;

public class Test04 {

	public static void main(String[] args) throws Exception {

		System.out.println("App start");
		String name = null;
		int kor,eng,math;
		Scanner sc = 
				new Scanner(new FileInputStream("c://lib/score.txt"));
		name = sc.next();
		kor = sc.nextInt();
		eng = sc.nextInt();
		math = sc.nextInt();
		System.out.printf("%d %d %d",kor,eng,math);
		System.out.println(sc.nextLine());
		
		double sum = kor + eng + math;
		double avg = sum/3;
		// grade processing
		char grade ='F';
		
		if(avg >=90) {
			grade = 'A';
		}
		else if(avg >= 80) {
			grade = 'B';
		}
		else if(avg >= 70) {
			grade = 'C';
		}
		else if(avg >= 60) {
			grade = 'D';
		}
		else {
			grade = 'F';
		}
		
		System.out.printf("%navg : %.2f %n",avg);
		System.out.printf("%ngrade : %c %n",grade);
		
		if(sc!=null) {
			sc.close(); sc = null;
			sc = null;
		}
		
		return;
	}

}
