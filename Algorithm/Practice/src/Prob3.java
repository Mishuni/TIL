
// 13:07 시작 15:33 끝 -> 2시간 30분 걸림 ㅠ 

public class Prob3 {
	// 모양 찾기
	public static int count;

	public static void main(String[] args) {
		int[][] key = { 
				{ 1, 0, 0 }, 
				{ 1, 0, 0 }, 
				{ 1, 0, 0 } };

		int[][] lock = { 
				{ 1, 1, 1, 1 }, 
				{ 1, 1, 1, 1 }, 
				{ 1, 1, 1, 1 }, 
				{ 0, 0, 1, 1 } };

		int M = key.length;
		int N = lock.length;

		// 0 count
		int holl = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (lock[i][j] == 0) {
					++holl;
				}
			}
		}
		int block = 0;
		for (int i = 0; i < M; ++i) {
			for (int j = 0; j < M; ++j) {
				if (key[i][j] == 1) {
					++block;
				}
			}
		}
		if (block < holl) {
			System.out.println(false);
			return;
		}

		if (holl == 0) {
			System.out.println(true);
			return;

		} else if (holl == N * N) {
			if (N > M) {
				System.out.println(false);
				return;
			} else if (N == M && holl == block) {
				System.out.println(true);
				return;
			} else {
				System.out.println(false);
				return;
			}
		}

		for (int r = 0; r < 4; ++r) {
			// start point check
			for (int i = -M; i < N; ++i) {
				for (int j = -M; j < N; ++j) {
					// (i,j) --> (i+M, j+M) -> (i2, j2)
					int i2 = (i+M < N)? i+M-1 : N-1;
					int j2 = (j+M < N)? j+M-1 : N-1;
					if(check(key,lock,i,j,i2,j2) && count == holl) {
						System.out.println(true);
						return;
					}

				}
			}

			if (r != 3) {
				// key rotation (90)
				key = rotation(key, M);
			}
		}
		System.out.println(false);
		// return false;

	}

	public static boolean check(int[][] key, int[][] lock, int i, int j, int i2, int j2) {
		count = 0;
		int r2 =0 ; int c2 = 0;
		for (int row = i; row <= i2; ++row) {
			c2=0;
			for (int col = j; col <= j2; ++col) {
				
				if (row >= 0 && row < lock.length && col >= 0 && col <= lock.length) {
					if (lock[row][col] + key[r2][c2] != 1) {
						return false;
					}
					if (lock[row][col] == 0) {
						++count;
					}
				}
				++c2;
			}
			++r2;
		}
		return true;
	}

	public static int[][] rotation(int[][] map, int n) {
		int[][] tmp = new int[n][n];

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				tmp[i][j] = map[n - 1 - j][i];

			}
		}
		return tmp;
	}

}
