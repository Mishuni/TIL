import java.util.ArrayList;
import java.util.Scanner;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sw1767 {
	static class Location {
		// 코어가 있는 위치를 저장 할 클래스
		int row;
		int column;

		Location(int a, int b) {
			this.row = a;
			this.column = b;
		}

	}

	static int[][] number;
	static int core_cnt, min_len;
	static ArrayList<Location> core; 		// core 위치들의 목록
	static int[] dx = { -1, 1, 0, 0 };		// 좌우상하
	static int[] dy = { 0, 0, -1, 1 };
	static int N;

	public static void dfs(int idx, int cnt, int len) {
		
		// 기저 사례 시작 (Base case)
		if (core.size() - idx < core_cnt - cnt)
			// 1. 남은 core 갯수가 채워야 할 갯수보다 적은 경우
			// 어차피 최대 갯수의 core를 연결하지 못하니까 종료
			return;

		if (idx == core.size()) {
			// 2. 마지막 core 까지 확인이 끝난 경우 (DFS의 마지막 깊이까지 검사한 경우)
			if (cnt > core_cnt) {
				// 2-1. 연결 코어 갯수가 현재까지의 최대 연결 갯수보다 큰 경우
				core_cnt = cnt;
				min_len = len;
			} else if (len < min_len && cnt == core_cnt) {
				// 2-2. 연결 코어가 최대 연결 갯수와 같은데 
				// 연결 길이가 지금까지의 최소 길이 보다 작은 경우
				min_len = len;
			}
			return;
		}
		// 기저 사례 끝
		
		// 현재 단계 검사 시작
		int x = core.get(idx).column;
		int y = core.get(idx).row;
		
		// 1. 좌우상하 순으로 dfs방식으로 검사
		for (int i = 0; i < 4; ++i) {
			int tmp_len = 0;
			boolean check = false;
			int new_x = x;
			int new_y = y;
			
			// 1-1. 현재 core의 라인을 한 방향으로 검사
			while (true) {
				new_x += dx[i];
				new_y += dy[i];
				if (new_x < 0 || new_x > N - 1 || new_y > N - 1 || new_y < 0)
					// 1-1-1. 검사 끝난 경우
					break;
				if (number[new_y][new_x] == 1) {
					// 1-1-2. 한 라인에 다른 선이나 core를 발견한 경우 종료
					check = true;
					break;
				} else {
					// 1-1-3. 선을 놓을 수 있는 길의 경우 임시 길이 추가
					++tmp_len;
				}
			}
			
			// 1-2. 다음 core검사 호출
			if (check) {
				// 1-2-1. 현재 core는 연결하지 못하니까 바로 다음 core검사 시작
				dfs(idx + 1, cnt, len);
			} else {
				// 1-2-2. 현재 core를 i번째 방향으로 선을 연결하고 다음 core검사 시작
				new_x = x;
				new_y = y;
				for (int j = 0; j < tmp_len; ++j) {
					new_x += dx[i];
					new_y += dy[i];
					number[new_y][new_x] = 1;
				}
				dfs(idx + 1, ++cnt, len + tmp_len);
				// 1-2-3. 다시 다른 방향으로도 검사해보기 위해 지도를 원래대로 돌려놓기
				new_x = x;
				new_y = y;
				for (int j = 0; j < tmp_len; ++j) {
					new_x += dx[i];
					new_y += dy[i];
					number[new_y][new_x] = 0;
				}
				--cnt;
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("1767.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		core = new ArrayList<Location>();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			core_cnt = 0;
			number = new int[N][N];
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					number[i][j] = sc.nextInt();
					if (number[i][j] == 1) {
						if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
							// 벽에 붙은 core
							++core_cnt;
						} else{
							core.add(new Location(i, j));
						}
					}
				}
			}
			min_len = Integer.MAX_VALUE;
			dfs(0, core_cnt, 0);
			System.out.printf("#%d %d\n", t, min_len);
			core.removeAll(core);
		}

		sc.close();
	}

}
