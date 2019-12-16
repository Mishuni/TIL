package day10;
import java.util.Scanner;
public class Bj1120 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String A = sc.next();
		String B = sc.next();
		sc.close();
		if(A.equals(B)) {
			System.out.println(0);
			return;
		}
		else if(A.length()==B.length()) {
			int cnt = 0;
			for(int i=0; i<A.length(); i++) {
				if(A.charAt(i)!=B.charAt(i))
					cnt++;
			}
			System.out.println(cnt);
		}
		else {
			int min = B.length();
			for(int i=0; i<B.length()-A.length()+1; i++) {
				int cnt = 0;
				for(int j=i; j<i+A.length(); j++) {
						if(A.charAt(j-i)!=B.charAt(j))
							cnt++;
				}
				if(cnt<min)
					min = cnt;
			}
			System.out.println(min);
		}
	}

}
