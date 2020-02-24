
public class Greedy_2 {

	public static void main(String[] args) {
		String name = "JEROEN";
		int answer = 0 ;
		// A는 아무것도 안해도 된다.
		
		// 상하는 A와 차이 절댓값
		for(int i=0; i<name.length(); ++i) {
			if(name.charAt(i)<='M') {
				answer += Math.abs(name.charAt(i)-'A');
			}
			else {
				answer += Math.abs(name.charAt(i)-'Z'+1);
			}
		}
		System.out.println(answer);
	}

}
