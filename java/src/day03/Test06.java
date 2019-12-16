package day03;

public class Test06 {

	public static void main(String[] args) {
		
		for( int i=1 ; i<=3 ; i++) {
			
			System.out.println("Hello java"+i);
		}
		
		System.out.println("-------------------");
		
		int sum = 0;
		for(int i=1;i<=100;i++) {
			if(i%5==0) {
			//System.out.print(i+" ");
			sum += i;
			}
		}
		
		System.out.println();
		System.out.println(sum);
		
		String msg = "hello java";
		for(int i=msg.length()-1; i>=0; i--) {
			System.out.print(msg.charAt(i));
		}
		
		
	}

}
