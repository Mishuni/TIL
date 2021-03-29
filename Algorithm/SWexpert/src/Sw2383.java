import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Sw2383 {
	// 기다리지 않으면 걸리는 시간 : 거리 + 계단 시간 -> x-x. + y-y. + s + [기다리는 시간]
	public static ArrayList<ArrayList<Integer>> stairs;
	public static ArrayList<ArrayList<Integer>> people;
	public static int[][] time;
	public static int[][] distance; 
	public static int N;
	public static int min_result;

	public static void main(String[] args) throws FileNotFoundException {
		
		
		System.setIn(new FileInputStream("./src/2384_2.txt"));

		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		stairs = new ArrayList<>();
		people = new ArrayList<>();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			N = sc.nextInt();
			stairs.clear();
			people.clear();
			
			for(int y=0; y<N; ++y) {
				for(int x=0; x<N; ++x) {
					int tmp = sc.nextInt();
					if(tmp>1) {
						ArrayList<Integer> cor = new ArrayList();
						cor.add(y);
						cor.add(x);
						cor.add(tmp);
						stairs.add(cor);
					}
					else if(tmp==1) {
						ArrayList<Integer> cor = new ArrayList();
						cor.add(y);
						cor.add(x);
						people.add(cor);
					}
				}
			}
			
			time = new int[2][people.size()];
			distance = new int[2][people.size()];
			int j= 0; 
			for(ArrayList<Integer> stair : stairs) {
				for(int i=0; i<people.size(); ++i) {
					ArrayList<Integer> tmp = people.get(i);
					distance[j][i] = Math.abs(tmp.get(0)-stair.get(0))+Math.abs(tmp.get(1)-stair.get(1));
				}
				//System.out.println(Arrays.toString(distance[j]));
				++j;
			}
			min_result = Integer.MAX_VALUE;
			dfs(0);
			System.out.printf("#%d %d%n",test_case,min_result);
			
			
		}
	}
	
	public static void dfs(int step) {
		
		if(step==people.size()) {
			
			int[][]time_copy = new int[2][people.size()] ;
			time_copy[0] = time[0].clone();
			time_copy[1] = time[1].clone();
			Arrays.sort(time_copy[0]);
			Arrays.sort(time_copy[1]);
			
			
			Queue<Integer> q1 = new LinkedList();
			Queue<Integer> q2 = new LinkedList();
			ArrayList<Queue<Integer>> all = new ArrayList();
			all.add(q1);
			all.add(q2);
			int[] sum = new int[2];
			
			for(int s=0; s<2; ++s) {
				Queue<Integer> queue = all.get(s);
				for(int i=0; i<people.size(); ++i) {
					
					if(time_copy[s][i]>0) {
					// 들렀을 때
						if(queue.size()>2) {
							int first = queue.poll();
							int a =  time_copy[s][i];
							if(first<=a) {
								a +=stairs.get(s).get(2)+1;
							}
							else {
								a = first + stairs.get(s).get(2)+1 ;
							}
							queue.add(a);
							sum[s] = a ;
						}
						else {
							int a =  time_copy[s][i]+stairs.get(s).get(2)+1;
							int limit = queue.size();
//							for(int q = 0; q<limit; ++q) {
//								if(queue.peek()>=a) {
//									break;
//								}
//								else {
//									queue.remove();
//								}
//							}
							sum[s] = a ;
							//System.out.println(sum[s]);
							queue.add(a);
						}
						//System.out.println(sum[s]);
						if(sum[s]>min_result) {
							return;
						}
					}
					
				}
			}
			//System.out.println(sum[0]+", "+sum[1]);
			
			int res = (sum[0]>sum[1])? sum[0] : sum[1];
			//if(res<min_result) {
				System.out.println(res);
				System.out.println(sum[0]+","+sum[1]);
				System.out.println("0: "+Arrays.toString(time[0]));
				System.out.println("1: "+Arrays.toString(time[1]));
				
			//}
			min_result = (res<min_result)? res : min_result;
			// 11 11 13 -> 11 13 19 -> 
			
			return;
			
		}
		
		time[1][step] = 0;
		time[0][step] = distance[0][step];
		dfs(step+1);
		time[0][step] = 0;
		time[1][step] = distance[1][step];
		dfs(step+1);
		
		
		
		
	}

}
