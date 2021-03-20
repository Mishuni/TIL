import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sw1206 {

	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("/Users/yumiseon/TIL/Algorithm/SWexpert/src/1206.txt"));

		Scanner sc = new Scanner(System.in);
		int T=10;
				
		for(int test_case = 1; test_case <= T; test_case++)
		{	
			LinkedList<Integer> queue = new LinkedList<>();
			int total = sc.nextInt();
			int sum = 0;
			
			// 0. 전체 갯수가 4보다 작은 경우는
			if(total<=4) {
				
			}
			else {
				
				// 5개 먼저 받기
				int i = 0;
				for(; i<5; ++i) {
					queue.add(sc.nextInt());
				}
				
				while(i<total+1 && queue.size()==5) {
					int current = queue.get(2); // middle
					int left_one = queue.get(0);
					int left_two = queue.get(1);
					int one = queue.get(3);
					int two = queue.get(4);
					
					if(i==total) {
						if(current>=one && current>=two && current>=left_one && current>=left_two) {
							int[] around = { left_one, left_two, one, two };
							Arrays.sort(around);
							sum += current - around[around.length-1];
						}
						break;
					}
					
					if(current>=one && current>=two && current>=left_one && current>=left_two) {
						int[] around = { left_one, left_two, one, two };
						Arrays.sort(around);
						sum += current - around[around.length-1];
						if(total-i>3) {
							for(int cnt=0; cnt<3; ++cnt) {
								queue.remove();
								queue.add(sc.nextInt());
							}
							i+=3;
						}else {
							queue.remove();
							queue.add(sc.nextInt());
							++i;
						}
						
					}
					else {
						queue.remove();
						queue.add(sc.nextInt());
						++i;
					}
					
				}
				
			}
			
			System.out.printf("#%d %d%n ",test_case, sum);
			
		}

	}
	

}
