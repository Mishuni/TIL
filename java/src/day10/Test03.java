package day10;

import java.util.Stack;

public class Test03 {

	public static void main(String[] args) {
		Stack<String> s1 = new Stack<String>();
		s1.push("~");
		System.out.println(s1.peek());
		System.out.println(s1.pop());
		System.out.println(s1.isEmpty());
		System.out.println(s1.capacity());
		System.out.println("-------");
		Stack<Integer> s2 = new Stack<Integer>();
		s2.push(90);
		System.out.println(s2.capacity());
		System.out.println(s2.peek());
		System.out.println(s2.pop());
		System.out.println(s2.isEmpty());
		
		
	}

}
