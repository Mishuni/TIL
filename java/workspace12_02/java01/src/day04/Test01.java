package day04;

import java.io.FileInputStream;
import java.util.Scanner;

public class Test01 {

	public static void main(String[] args) throws Exception {

		System.out.println("App start");
		String name = null;
		int kor, eng, math;
		Scanner sc = new Scanner(new FileInputStream("c://lib/score.txt"), "UTF-8");
		System.out.println("Name?");
		name = sc.next();
		System.out.println(name);
		System.out.println();
		System.out.println("kor,eng,math");
		kor = sc.nextInt();
		eng = sc.nextInt();
		math = sc.nextInt();
		System.out.printf("%d %d %d", kor, eng, math);
		System.out.println(sc.nextLine());

		double sum = kor + eng + math;
		double avg = sum / 3;
		// grade processing
		char grade = 'F';

		switch((int)avg/10) {
		case 10:
		case 9:
			grade = 'A';
			break;
		case 8:
			grade = 'B';
			break;
		case 7:
			grade = 'C';
			break;
		case 6:
			grade ='D';
			break;
		default:
			grade = 'F';
		
		}
		System.out.printf("%navg : %.2f %n", avg);
		System.out.printf("%ngrade : %c %n", grade);

		if (sc != null) {
			sc.close();
			sc = null;
			sc = null;
		}
		
		switch((int)avg/10) {
		case 10:
		case 9:
			System.out.println("Congraturations!");
			break;
		case 8:
			System.out.println("Soso~");
			break;
		case 7:
			System.out.println("C party");
			break;
		case 6:
			System.out.println("Wow");
			break;
		default:
			System.out.println("fail");
		
		}
		
		return;
	}

}
