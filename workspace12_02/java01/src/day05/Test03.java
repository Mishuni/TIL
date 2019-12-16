package day05;

public class Test03 {

	public static void main(String[] args) {
		int[] num = {1,2,3};
		for(int data :num) { //inhenced for loop
			if(data % 2 != 0) System.out.print(data+" ");
		}
		System.out.println();
		
		char[] c = "ABCD".toCharArray();
		for(char data : c ) {
			System.out.print(data+" ");
		}
		System.out.println();
		
		String[] s = {"hello","java","test","throw"};
		for(String data: s) {
			if(data.charAt(0)=='t')System.out.print(data+" ");
		}
	}

}
