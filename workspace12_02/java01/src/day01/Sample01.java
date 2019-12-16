package day01;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Sample01 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		int K = sc.nextInt();

		boolean[] visited = new boolean[V+1];
		int[] distance = new int[V+1];
		
		
		// path = [(1:[2,3]), ... ]
		// value = [(1:[3,3]), ... ]
		HashMap<Integer, ArrayList<Integer>> path = 
				new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> value = 
				new HashMap<Integer, ArrayList<Integer>>();
		// 초기화
		for(int i=0; i<V+1; i++) {
			distance[i]=Integer.MAX_VALUE;
			path.put(i+1, new ArrayList<Integer>());
			value.put(i+1, new ArrayList<Integer>());
		}
		
		// 1 path 와 value 입력
		for(int i=0; i<E; i++) {
			int s = sc.nextInt();
			int d= sc.nextInt();
			int c = sc.nextInt();
			
			path.get(s).add(d);
			value.get(s).add(c);
			
		}
		
		// 2 최단경로 찾는 다익스트라 알고리즘
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.add(K);
		distance[K] = 0;
		while(!q.isEmpty()) {
			int current = q.poll();
			visited[current] = true;
			ArrayList<Integer> p = path.get(current);
			ArrayList<Integer> v = value.get(current);
			for(int j=0; j<p.size(); j++) {
				// 2-1 current 와 연결된 node 모두 확인
				if(visited[p.get(j)]!=true && distance[p.get(j)]>distance[current]+v.get(j)) {
					// 2-1-1 연결된 곳을 방문하지 않았고, 더 작은 비용이 들면
					distance[p.get(j)]=distance[current]+v.get(j);
					q.add(p.get(j));
				}
			}
		}
		
		// 3 출력
		for(int i=1; i<distance.length; i++) {
			if(distance[i]==Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(distance[i]);
		}
		

	}

}
