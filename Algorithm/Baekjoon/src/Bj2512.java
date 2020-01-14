import java.util.Scanner;

public class Bj2512 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] req = new int[N];
		int sum = 0;
		int max_value = 0;
		int min_value = 100001;
		for (int i = 0; i < N; ++i) {
			// �Է� �����鼭 ��û ���� ������, �ִ밪, ���� �� �˾Ƴ���
			req[i] = sc.nextInt();
			sum += req[i];
			if (req[i] > max_value)
				max_value = req[i];
			if (req[i] < min_value)
				min_value = req[i];
		}
		int M = sc.nextInt();
		sc.close();
		sc = null;
		if (sum <= M) {
			// ���� M ���� ������ Ž�� �� �� �����Ƿ� ����
			System.out.println(max_value);
			return;
		} else if (min_value >= M) {
			// �ּҰ��� M ���� ũ��, �ִ� ���Ѽ��� M
			System.out.println(M);
			return;
		}
		int left = M / N;
		int right = max_value;
		int max_result = 0;
		int half = 0;
		// �̺� Ž�� ����
		while (left <= right) {
			half = (left + right) / 2;
			if (half == 0) 
				// left =0, right =1 �� ��� ���
				half = 1;
			sum = 0;
			for (int n : req) {

				sum += (n < half) ? n : half;

			}
			if (sum > M) {
				right = half - 1;
			} else {
				left = half + 1;
				if (half > max_result) {
					max_result = half;
				}
			}
		}
		System.out.println(max_result);
	}

}
