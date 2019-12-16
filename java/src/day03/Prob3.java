package day03;

public class Prob3 {
	public static void main(String[] args) {
		int acc = 0;
		int sum = 0;
		for(int i=1; i<=10; i++) {
			acc += i;
			sum += acc;
		}
		System.out.println(acc);
		System.out.println(sum);
	}
}
