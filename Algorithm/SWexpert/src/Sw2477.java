import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Sw2477 {

	public static void main(String[] args) throws FileNotFoundException {
		// reception : N , ai
		// repair : M , bj
		// recep -> repair -> survey
		// customers : K ( 1 ~ K ), arrived at tK
		// 접수 우선 : 고객 번호 낮은 것, 창구 낮은 번호 부터
		// 정비 우선 : 먼저 기다리는, 접수 창구 번호 낮은것 정비 창구 번호 낮은 곳
		// 이동 시간 : 0
		// 같은 접수 창구, 정비 창구 고객 번호의 합 , 없으면 -1
		
		System.setIn(new FileInputStream("./src/2477.txt"));

		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			// hashmap -> index : middle time
			// a end base and save target list
			// after a, sort by hashmap's value
			// b end base and save target check
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();
			int rct = sc.nextInt(); // reception target
			int rpt = sc.nextInt(); // repair target
			int sum = 0;
			int[] receptions = new int[N];
			int[] repairs = new int[M];
			
			Map<Integer, ArrayList<Integer>> customers 
				= new HashMap<Integer, ArrayList<Integer>>();
			boolean[] check = new boolean[K];
			int[] max_path = new int[N];
			
			receptions = input(N,sc);
			repairs = input(M,sc);
			
			for(int i=0; i<K; ++i) {
				ArrayList<Integer> tmp = new ArrayList<>();
				tmp.add(i);
				tmp.add(sc.nextInt());
				customers.put(i, tmp);
			}
			
			Comparator<Entry<Integer,ArrayList<Integer>>> com 
			=	new Comparator<Entry<Integer,ArrayList<Integer>>>(){

				@Override
				public int compare(Entry<Integer, ArrayList<Integer>> o1, 
						Entry<Integer, ArrayList<Integer>> o2) {
					
					ArrayList<Integer> tmp1 = o1.getValue();
					ArrayList<Integer> tmp2 = o2.getValue();
					if(tmp1.get(1)==tmp2.get(1)) {
						return tmp1.get(0).compareTo(tmp2.get(0));
					}
					return tmp1.get(1).compareTo(tmp2.get(1));
				}
				
			};
			List<Entry<Integer, ArrayList<Integer>>> entries 
			= new ArrayList<Entry<Integer,ArrayList<Integer>>>(customers.entrySet());
		
			Collections.sort(entries,com );
			
			for(int i=0; i<K; ++i) {
				int start = entries.get(i).getValue().get(1);
				int tmp_min = Integer.MAX_VALUE;
				int rep = -1;
				for(int a=0; a<N; ++a) {
					if(max_path[a]<tmp_min) {
						tmp_min = max_path[a];
						rep = a;
					}
					if(max_path[a]<=start) {
						if(a==rct-1) {
							check[i]=true;
						}
						//ArrayList<Integer> tmp = new ArrayList<>();
						//tmp.add(a);
						max_path[a]=start+receptions[a];
						entries.get(i).getValue().remove(1);
						entries.get(i).getValue().add(max_path[a]);
						//tmp.add(max_path[a]);
						//customers.put(i, tmp);
						break;
					}
					else if(a==N-1) {
						max_path[rep]+=receptions[rep];
						//ArrayList<Integer> tmp = new ArrayList<>();
						//tmp.add(rep);
						//tmp.add(max_path[rep]);
						//customers.put(i, tmp);
						entries.get(i).getValue().remove(1);
						entries.get(i).getValue().add(max_path[rep]);
						if(rep==rct-1) {
							check[i]=true;
						}
					}
				}
			}
			// map sort
			Collections.sort(entries,com );
			max_path = new int[M];
			for(Entry<Integer,ArrayList<Integer>> entry : entries) {
				int start = entry.getValue().get(1);
				int tmp_min = Integer.MAX_VALUE;
				int rep = -1;
				for(int b=0; b<M; ++b) {
					if(max_path[b]<tmp_min) {
						tmp_min=max_path[b];
						rep=b;
					}
					if(max_path[b]<=start) {
						if(b==rpt-1 && check[entry.getKey()]) {
							// add result
							sum+=entry.getKey()+1;
						}
						max_path[b]=start+repairs[b];
						break;
					}
					else if(b==M-1) {
						max_path[rep]+=repairs[rep];
						if(rep==rpt-1 && check[entry.getKey()]) {
							sum+=entry.getKey()+1;
						}
					}
					
				}
			}
			sum = (sum==0)? -1: sum;
			System.out.printf("#%d %d%n",test_case,sum);
			
			
		}

	}
	
	public static int[] input(int num, Scanner sc) {
		int[] list = new int[num];
		for(int i=0; i<num; ++i) {
			list[i] = sc.nextInt();
		}
		return list;
	}

}
