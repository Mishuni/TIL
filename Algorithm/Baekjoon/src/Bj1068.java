import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Bj1068 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Map<Integer,ArrayList<Integer>> map = 
				new HashMap<Integer,ArrayList<Integer>>();
		// map <부모,자기자신>
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		tmp.add(0);
		map.put(sc.nextInt(),tmp);
		for(int i=1; i<map.size(); i++) {
			if(i%2!=0) {
				tmp = new ArrayList<Integer>();
				tmp.add(i);
				
			}
			else {
				tmp.add(i);
			}
			map.put(sc.nextInt(),tmp);
		}
		int R = sc.nextInt();
		if(map.containsKey(R)) {
			tmp = map.get(R);
		}else {
			tmp = new ArrayList<Integer>();
		}
		map.remove(R);
		while(tmp.size()!=0) {
			R = tmp.remove(0);
			if(map.containsKey(R)) {
				tmp.addAll(map.get(R));
			}
		}
		System.out.println(map.size());

	}

}
