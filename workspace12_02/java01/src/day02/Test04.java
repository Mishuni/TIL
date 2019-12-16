package day02;

public class Test04 {

	public static void main(String[] args) {
		
		int num = 120;
		int i = 99, j = 77;
		System.out.println(i+":"+j);
		int tem = i ;
		i = j;
		j = tem;
		System.out.println(i+":"+j);
		
		String s1 = "hello", s2 = "java";
		System.out.printf("(s1=%s, s2= %s) \n",s1,s2);
		String temp = s1;
		s1 = s2;
		s2 = temp;
		System.out.printf("(s1=%s, s2= %s) \n",s1,s2);

	}

}
