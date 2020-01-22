import java.util.ArrayList;
import java.util.Arrays;

public class Dfs_1 {
	public static ArrayList<Integer> prime ;
	public static void main(String[] args) {
		String numbers = "012377";
		int cnt = 0 ;
		prime = new ArrayList<Integer>();
		prime.add(2);
		int[] allNum = new int[numbers.length()];
		
		for(int i=0; i<allNum.length; ++i) {
			allNum[i] = numbers.charAt(i)-'0';
			System.out.println(check(allNum[i]));
		}
		Arrays.sort(allNum);

	}
	
	public static boolean check(int num) {
		if(num==2) return true;
		boolean p = false;
		for(int i=2; i<=num; ++i) {
			for(int j=0; j<prime.size(); ++j) {
				if(i%prime.get(j)==0) break;
				if(j==prime.size()-1) {
					prime.add(i);
					if(i==num) {
						p = true;
					}
				}
			}
		}
		return p;
	}

}
