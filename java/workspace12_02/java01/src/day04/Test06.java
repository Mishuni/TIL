package day04;

import java.util.Arrays;

public class Test06 {

	public static void main(String[] args) {
		int[] num = new int[10];
		for(int i=0; i<num.length; i++) {
			boolean check = true;
			while(check) {
			int a = (int)(Math.random()*100)+1;
			for(int j=0; j<i;j++) {
				if(a==num[j]) {
					check = true;
					break;
				}		
			}
			check = false;
			num[i] = a;
			}
		}
		System.out.println(Arrays.toString(num));
		int[] temp = new int[2*num.length];

		System.arraycopy(num, 0, temp, 0, num.length);
		System.out.println(Arrays.toString(temp));
		System.out.println();

	}

}
