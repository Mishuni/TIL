package day04;

public class Test02 {

	public static void main(String[] args) {
		for(int i=0; i<3; i++) {
			for(int j=0; j<5; j++) {
				System.out.printf("%d, %d  ",i,j);
			}
			System.out.println();
		}
		System.out.println("============================");
		
		boolean flag = true;
		while(flag) {
			int num = (int)(Math.random()*10);
			System.out.println(num);
			if(num%3==0) flag = !flag;
		}

	}

}
