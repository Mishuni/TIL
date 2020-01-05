import java.util.ArrayList;
import java.util.Scanner;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sw1767 {
	static class Location {
		// �ھ �ִ� ��ġ�� ���� �� Ŭ����
		int row;
		int column;

		Location(int a, int b) {
			this.row = a;
			this.column = b;
		}

	}

	static int[][] number;
	static int core_cnt, min_len;
	static ArrayList<Location> core; 		// core ��ġ���� ���
	static int[] dx = { -1, 1, 0, 0 };		// �¿����
	static int[] dy = { 0, 0, -1, 1 };
	static int N;

	public static void dfs(int idx, int cnt, int len) {
		
		// ���� ��� ���� (Base case)
		if (core.size() - idx < core_cnt - cnt)
			// 1. ���� core ������ ä���� �� �������� ���� ���
			// ������ �ִ� ������ core�� �������� ���ϴϱ� ����
			return;

		if (idx == core.size()) {
			// 2. ������ core ���� Ȯ���� ���� ��� (DFS�� ������ ���̱��� �˻��� ���)
			if (cnt > core_cnt) {
				// 2-1. ���� �ھ� ������ ��������� �ִ� ���� �������� ū ���
				core_cnt = cnt;
				min_len = len;
			} else if (len < min_len && cnt == core_cnt) {
				// 2-2. ���� �ھ �ִ� ���� ������ ������ 
				// ���� ���̰� ���ݱ����� �ּ� ���� ���� ���� ���
				min_len = len;
			}
			return;
		}
		// ���� ��� ��
		
		// ���� �ܰ� �˻� ����
		int x = core.get(idx).column;
		int y = core.get(idx).row;
		
		// 1. �¿���� ������ dfs������� �˻�
		for (int i = 0; i < 4; ++i) {
			int tmp_len = 0;
			boolean check = false;
			int new_x = x;
			int new_y = y;
			
			// 1-1. ���� core�� ������ �� �������� �˻�
			while (true) {
				new_x += dx[i];
				new_y += dy[i];
				if (new_x < 0 || new_x > N - 1 || new_y > N - 1 || new_y < 0)
					// 1-1-1. �˻� ���� ���
					break;
				if (number[new_y][new_x] == 1) {
					// 1-1-2. �� ���ο� �ٸ� ���̳� core�� �߰��� ��� ����
					check = true;
					break;
				} else {
					// 1-1-3. ���� ���� �� �ִ� ���� ��� �ӽ� ���� �߰�
					++tmp_len;
				}
			}
			
			// 1-2. ���� core�˻� ȣ��
			if (check) {
				// 1-2-1. ���� core�� �������� ���ϴϱ� �ٷ� ���� core�˻� ����
				dfs(idx + 1, cnt, len);
			} else {
				// 1-2-2. ���� core�� i��° �������� ���� �����ϰ� ���� core�˻� ����
				new_x = x;
				new_y = y;
				for (int j = 0; j < tmp_len; ++j) {
					new_x += dx[i];
					new_y += dy[i];
					number[new_y][new_x] = 1;
				}
				dfs(idx + 1, ++cnt, len + tmp_len);
				// 1-2-3. �ٽ� �ٸ� �������ε� �˻��غ��� ���� ������ ������� ��������
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
							// ���� ���� core
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
