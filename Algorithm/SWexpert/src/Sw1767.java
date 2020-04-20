import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Sw1767 {

	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream(".\\src\\1767.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			
			int N = sc.nextInt();
			int minCore = 0;
			
			int[][] map = new int[N][N];
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					map[i][j]=sc.nextInt();
					// 1. 가에 코어가 존재하는 경우
					if(map[i][j]==1 && (i==0||j==0||i==N-1||j==N-1)) {
						++minCore;
					}
				}
			}
			
			
			System.out.println(minCore);

		}

	}

}