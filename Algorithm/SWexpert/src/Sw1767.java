import java.util.Scanner;

public class Sw1767 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		boolean [] connect;
		for(int t=0; t<T; t++) {
			int N = sc.nextInt();
			int[][] number = new int[N][N];
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					number[i][j]=sc.nextInt();
					if(i==0 || j==0 || i==N-1 || j==N-1) {
						
					}
				}
			}
		}
	}

}
