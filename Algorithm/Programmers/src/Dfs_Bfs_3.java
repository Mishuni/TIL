
public class Dfs_Bfs_3 {
	public static boolean[] checked;
	public static int answer;
	public static void main(String[] args) {
		String begin="hit", target = "cog";
		String[] words = {"hot",
				"dot", "dog", "lot", "log", "cog"};
		checked = new boolean[words.length];
		answer = 0;
		// 1. target 이 있나 없나 확인
		for(String word : words) {
			if(word.equals(target)) {
				answer = 1;
				break;
			}
		}
		if(answer!=1) {
			answer = 0 ;
			System.out.println(0);
			return;
		}
		answer = words.length;
		// 2. target 이 배열 안에 있는 경우
		// 일단 begin에서 한 단어만 다른 단어들 찾기
		// 그걸로 이제 dfs방식으로 정답 찾아가기
		
				
		}
	public static void dfs(
			String[] words, 
			String begin,
			String target, int step) {
		
		// 기저 사례 
		if(begin.equals(target)) {
			
		}
		
		// begin 에서 한 단어만 다른 단어
		
		
	}
	}


