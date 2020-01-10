import java.util.Scanner;
public class Bj1654 {
	public static long max_result;
	public static long[] lines;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int K = sc.nextInt();
		int N = sc.nextInt();
		lines = new long[K];
		max_result = 0;
		for(int i=0; i<K; ++i) {
			lines[i] = sc.nextInt();
			if(max_result<lines[i]) {
				max_result = lines[i];
			}
		}
		sc.close(); sc= null;
		if(N==1) {
			System.out.println(max_result);
			return;
		}
		else {
			max_result = 0 ;
			long left = 0 ;
			long right = lines[lines.length-1];
			while(left<=right) {
				long half = (left+right)/(long)2;
				long sum = 0 ;
				for(int i=0; i<lines.length; ++i) {
					sum += lines[i]/half;
				}
				if(sum<N) {
					right = --half;

				}else {
					if(half>max_result) {
						max_result = half;
					}
					left = ++half; 
				}
			}
		}
		System.out.println(max_result);
	}
	

}
