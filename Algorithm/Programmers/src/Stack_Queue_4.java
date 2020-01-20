import java.util.Arrays;

public class Stack_Queue_4 {

	public static void main(String[] args) {
		int[] progresses= {93,30,55}; 
		int[] speeds = {1,30,5};
		int[] release = new int[speeds.length];
		
		int cnt = 1 ;
		int index= 0 ;
		int tmp = (int) Math.ceil((double)(100-progresses[0]) / speeds[0]);
		int pre = tmp;
		
		for(int i=1; i<progresses.length; ++i) {
			tmp = (int) Math.ceil((double)(100-progresses[i]) / speeds[i]);
			if(tmp>pre) {
				pre = tmp ;
				release[index++] = cnt;
				cnt=1;
			}else {
				++cnt;
			}
		}
		release[index] = cnt;
		int[] answer = new int[index+1];
		for(int i=0; i<=index; ++i) {
			answer[i] = release[i];
		}
		System.out.println(Arrays.toString(answer));
	}

}

