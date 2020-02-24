
public class Greedy_2 {

	public static void main(String[] args) {
		String name = "JEROEN";
		
		// JEAAAEE -> 오1 왼3
		// JEAEEEE -> 왼6
		// JEEAEEE -> 왼6
		// JAAAAAE -> 왼1
		// JEAAAAE -> 오1 왼2
		// AAJEEEE -> 왼5
		// AJAJAJA -> 오5
		// JAEEAJJ -> 왼5
		// AAAEAAA -> 오3
		int answer = 0 ;

		// 상하는 A와 차이 절댓값
		// a b c d e f g h i j k l m
		// n o p q r s t u v w x y z
		for(int i=0; i<name.length(); ++i) {
			if(name.charAt(i)<='M') {
				answer += Math.abs(name.charAt(i)-'A');
			}
			else {
				answer += Math.abs(name.charAt(i)-'Z')+1;
			}
			System.out.println(answer);
		}
		//System.out.println(answer);
	}

}
