import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Bj2668 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] number = new int[N + 1];
		Queue<Integer> content = new PriorityQueue<Integer>();
		Queue<Integer> index = new PriorityQueue<Integer>();
		for (int i = 1; i <= N; i++) {
			number[i] = sc.nextInt();
		}
		sc.close(); sc= null;
		Queue<Integer> choice = new LinkedList<Integer>();
		Queue<Integer> result = new LinkedList<Integer>();
		int max = 0;
		for (int i = 1; i < (1<<N); i++) {
			// 부분 배열
			content.removeAll(content);
			index.removeAll(index);
			for(int j=1; j<=N; j++) {
				if((i&(1<<(j-1)))!=0) {
					content.add(number[j]);
					index.add(j);
				}
			}
			// content 와 index 비교
			if(content.size()>max) {
				choice.removeAll(choice);
				int j = 0;
				boolean check = false;
				while(!content.isEmpty()) {
					int tmp = content.remove();
					if(index.remove()!=tmp) {
						check = true;
						break;
					}
					choice.add(tmp);
				}
				if(!check) {
					max = choice.size();
					result = (Queue<Integer>) ((LinkedList)choice).clone() ;
				}
			}
			
		}
		int size = result.size();
		System.out.println(size);
		for(int i=0; i<size; i++) {
			System.out.println(result.remove());
		}

	}

}

/*
 * 1 2 3 4 5 6
 * 2 3 1 6 5 5
 * -> 4 1 2 3 5 
*/