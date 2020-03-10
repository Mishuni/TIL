import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Greedy_5 {

	public static void main(String[] args) {
		int n = 4;
		int[][] costs 
		={{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
	
		HashMap< Integer, LinkedList<int[]>> link
		= new HashMap<Integer,LinkedList<int[]>>();
		
		int[] checked = new int[n];
		
		for(int[] cost : costs) {
			try {
				LinkedList<int[]> tmp = link.get(cost[0]);
				int[] a = {cost[1],cost[2]};
				tmp.add(a);
			}catch(Exception e) {
				LinkedList<int[]> tmp = new LinkedList<int[]>();
				int[] a = {cost[1],cost[2]};
				tmp.add(a);
				link.put(cost[0], tmp);
			}
		}
		
		for(int i=0; i<n; ++i) {
			if(link.containsKey(i)) {
			LinkedList<int[]> tmp = link.get(i);
			for(int[] a : tmp) {
				if(checked[a[0]]==0) {
					checked[a[0]]=a[1];
				}
				else if(checked[a[0]]>a[1]) {
					checked[a[0]]=a[1];
				}
			}
			
		}}
		int sum = 0;
		for(int c : checked) {
			sum += c;
		}
		System.out.println(sum);
		
	}

}
