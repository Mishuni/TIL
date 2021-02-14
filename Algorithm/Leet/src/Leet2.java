
import java.util.*;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Leet2 {

	public static void main(String[] args) {
		LinkedList<Integer> l1 = new LinkedList();
		LinkedList<Integer> l2 = new LinkedList();
		
		l1.add(5);
		l1.add(4);
		l1.add(3);
		
		l2.add(1);
		l2.add(1);
		//l2.add(1);
		
		int cnt = 0;
		int up = 0;
		
		LinkedList<Integer> answer = new LinkedList();
		while(!l1.isEmpty()|| !l2.isEmpty()) {
			int tmp1 = (l1.isEmpty())?0:l1.remove();
			int tmp2 = (l2.isEmpty())?0:l2.remove();
			int tmp = tmp1 + tmp2 + up;
			up = (tmp>9)? 1:0;
			tmp = (tmp>9)? tmp-10:tmp;
			answer.add(tmp);
		}
		if(up==1) answer.add(1);
		
		System.out.println(answer.toString());
		
		
		

	}

}

