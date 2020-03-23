import java.util.Arrays;

public class Greedy_5 {

	public static void main(String[] args) {
		
		int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3},{-5,-4},{16,18}};
		boolean[] check = new boolean[routes.length];
		int[] path = new int[41];
		int max = 0;
		int maxInt = 0 ;
		int cnt = 0 ;
		
		// 1. 겹치는 구간 찾기
		
		for(int[] route : routes) {
			for(int i=route[0]; i<=route[1]; ++i) {
				if(++path[i+20]>max) {
					max = path[i+20];
					maxInt = i+20;
				}
			}
		}
		
		// 2. 제일 많이 겹치는 구간 부터 계산 하기
		for(int r=0; r< routes.length; ++r) {
			int[] route = routes[r];
			if( !check[r]&&route[1]<=maxInt) {
				for(int i=route[0]; i<=route[1]; ++i) {
					--path[i+20];
				}
				check[r] = true;
			}
		}
		++cnt;
		
		System.out.println(Arrays.toString(path));
		
	}

}
