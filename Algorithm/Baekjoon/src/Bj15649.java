import java.util.LinkedList;
import java.util.Scanner;

public class Bj15649 {
	static boolean[] checked ;
	static LinkedList<Integer> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		checked = new boolean[n+1];
		list = new LinkedList<Integer>();
		dfs(n,m);
		sc.close();
	}
	
	public static void dfs( int n, int m) {
		
		// 기저 사례
		if(m==0) {
			for(int i=0; i<list.size(); ++i ) {
				System.out.print(list.get(i)+" ");
			}
			System.out.println();
			return;
		}

		// 완전 탐색
		for( int i=1; i<=n; ++i ) {
			//if(!checked[i]) {
				// 아직 지나가지 않은 상태
				//checked[i] = true;
				list.add(i);
				dfs(n,m-1);
				list.removeLast();
				//checked[i] = false;
			//}
			
		}
		
	}

}
