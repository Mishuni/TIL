package day07;

public class StackTest{
	public static void main(String[] args) {
		MyStack stack = new MyStack(10);
		if(stack.isEmpty()){
			System.out.println("스택이 비어있습니다.");
		}
		
		for (int i = 1; i <= 10; i++) {
			stack.push(i);
		}
		
		if(stack.isFull()){
			System.out.println("스택이 가득 찼습니다.");
		}
		
		System.out.println("최상위 숫자 : " + stack.top());
		System.out.println("최상위에서 꺼낸 숫자 : " + stack.pop());
		System.out.println("최상위에서 꺼낸 숫자 : " + stack.pop());
		System.out.println("");
		System.out.println("== 스택 리스트 ==");
		for (int i = 1; i <= 10; i++) {
			int num = stack.pop();
			if(num != -1)
				System.out.println(num);
		}
	}
}

class MyStack{
	// 구현하시오.
	int cnt =0;
	int size;
	int[] list;
	
	public MyStack() {
		size = 10;
		cnt = 0;
		list = new int[size];
	}
	
	public MyStack(int size) {
		this.size = (size<0)? 10: size;
		list = new int[size];
		cnt = 0;
	}
	
	public boolean isEmpty() {
		return cnt==0;
	}
	
	public void push(int a) {
		if(isFull()) {
			size = size * 2;
			int[] tmp = new int[size];
			System.arraycopy(list, 0, tmp, 0, list.length);
			list = tmp;
			tmp = null;
			}
		list[cnt]=a;
		cnt++;
	}
	
	public boolean isFull() {
		return cnt==size;
	}
	
	public int pop() {
		if(isEmpty())
			return -1;
		int a = list[cnt-1];
		cnt--;
		return a;
	}
	
	public int top() {
		if(isEmpty())
			return -1;
		int a = list[cnt-1];
		return a;
	}


}
