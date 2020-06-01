import java.util.Comparator;
import java.util.LinkedList;

public class Array_2 {

	public static void main(String[] args) {
		
		int[] numbers = {3, 30, 34, 5, 9,5,553,55,555,56,556};
		
		// 1. 첫 숫자가 제일 큰 거 먼저 도착 
		// 2. 첫 숫자 빼고, 다음 제일 큰거 그 다음 도착
		// 9, 5, 34, 3, 30 
		// (3 ,30 ,34) : 제일 큰게 3이니까, 뒷 숫자가 3보다 크면 그거 아니면 그냥 3!
		// (3 ,30) : 뒷 자리가 3보다 크지 않으니까 3 !
		
		StringBuilder answer = new StringBuilder("");
		LinkedList<String> list = new LinkedList<String>();
	
		for (int number : numbers) {
			String a = Integer.toString(number);
			list.add(a);
		}
		
		list.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				if(o1.length()==o2.length()) {
					if (Integer.parseInt(o1)>Integer.parseInt(o2)){
						return -1;
					}
					else if(Integer.parseInt(o1)<Integer.parseInt(o2)) {
						return 1;
					}
					return 0;
				}
				
				int end = (o1.length()>o2.length())?o2.length() : o1.length();
				
				// 일단, 작은 길이까지 비교
				for(int i=0; i<end; ++i) {
					if(o1.charAt(i)>o2.charAt(i)) {
						return -1;
					}
					else if(o1.charAt(i)<o2.charAt(i)) {
						return 1;
					}
				}
				
				// 길이차가 있는 경우
				if(o1.length() > o2.length()) {
					for(int i=end; i<o1.length(); ++i) {
						if(o2.charAt(end-1)>o1.charAt(i)) {
							return 1;
						}
					}
					
				}
				
				// 길이 차 없고,숫자들 다 같은 경우
				return 0;
			}
		});
		
		System.out.println(list);
		
		for(String num : list) {
			answer.append(num);
		}
		System.out.println(answer.toString()); 

	}
	

}

