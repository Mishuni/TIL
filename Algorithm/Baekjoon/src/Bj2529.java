

public class Bj2529 {

	public static void main(String[] args) {
		
		int[] stu1 = {1,2,3,4,5};
		int[] stu2 = {2,1,2,3,2,4,2,5};
		int[] stu3 = {3,3,1,1,2,2,4,4,5,5};
		
		int[] ans = {0,0,0};
		int[] answer = {1,3,2,4,2};
		
		for(int i=0; i<answer.length; i++) {
			if(answer[i]==stu1[i%5]) {
				ans[0] ++ ;
			}
			if(answer[i]==stu2[i%stu2.length]) {
				ans[1] ++ ;
			}
			if(answer[i]==stu3[i%stu3.length]) {
				ans[2] ++ ;
			}
		}
		int max = -1 ;
		
		for(int i=0; i<3; i++) {
			if(ans[i]>max) {
				max = ans[i];
			}
		}
		boolean[] tem = new boolean[3];
		int cnt = 0;
		for(int i=0; i<3; i++) {
			if(ans[i]==max) {
				tem[i]=true;
				cnt++;
			}
		}
		
		if(cnt==3) {
			System.out.println("all");
		}
		else {
			for(int i=0; i<3;i ++) {
				if(ans[i]==max) {
					System.out.println("oo");
				}
			}
		}
		
		
		

	}

}
