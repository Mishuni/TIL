package day05;

import java.util.Arrays;

public class Test02 {

	public static void main(String[] args) {
		int [][] t2 = {{1,2,3},{4,5,6,7,8,10},{1},{9,8,7}};		

		/*char[] c = {'A','B','C','D'};
		String a ="skjrentetjkelt";
		char[] aa = a.toCharArray();
		System.out.println(Arrays.toString(args));
		
		if(args.length == 0) {
			System.out.println("parameter");
			return ;
		}
		System.out.println(args[0].charAt(0)+"**");*/
		
		int[] num = new int[] {9,7,1,8,6};
		System.out.println(Arrays.toString(num));
		int [] num3 = num.clone();
		int[] num2 = num;
		num2[2] = 99;
		
		System.out.println(Arrays.toString(num));
		System.out.println(Arrays.toString(num2));
		System.out.println(Arrays.toString(num3));
		
		String url = "http://myweb/admin/login.do";
		System.out.println(url.substring(0, 7));
		System.out.println(url.substring(19));
		System.out.println(url.subSequence(0, 10));
		
		String r = url.substring(url.lastIndexOf('/'));
		System.out.println(r
				);
	}

}
