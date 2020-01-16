import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hash_4 {

	public static void main(String[] args) {
		String[] genres= {"classic", "pop", "classic", 
				"classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		Map<String,ArrayList<Integer>> rank = 
				new HashMap<String,ArrayList<Integer>>();
		
		
		for(int i=0; i<genres.length; ++i) {
			try {
				
				rank.get(genres[i]).add(plays[i]);
				
			}catch(Exception e) {
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				tmp.add(plays[i]);
				rank.put(genres[i],tmp);
			}
		}
		System.out.println(rank);
		
	}

}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        return answer;
    }
}