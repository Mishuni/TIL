import java.util.Arrays;
import java.util.Scanner;
public class Bj10816_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] num = new int[N];
		for(int i=0; i<N; ++i) {
			num[i] = sc.nextInt();
		}
		int M = sc.nextInt();
		int[] check = new int[M];
		for(int i=0; i<M; ++i) {
			check[i] = sc.nextInt();
		}
		Arrays.sort(num);
		Arrays.sort(check);
		int left = num[0];
		int right = num[num.length - 1];
		for(int m : check) {
			int half = (left + right) / 2;
		}

	}

}
