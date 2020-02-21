
public class Dfs_4 {

	public static void main(String[] args) {
		int brown = 10, red = 2 ;
		
		// 갈색  : 테두리 갯수
		// 빨간색 : (세로-2) * (가로-2)
		int[] result = new int[2];
		if(red==0) {
			result[0] = red;
			result[1] = 1;
		}
 		int half = brown/2;
		for(int i = 2 ; i < half ; ++i) {
			
			if(brown % i == 0 ) {
				
				if((i-2)*((brown/i)-2) == red) {
					result[0] = (brown/i);
					result[1] = i;
					break;
				}
				
			}			
		}
		System.out.println(result[0]);
		System.out.println(result[1]);
		
		

	}

}

