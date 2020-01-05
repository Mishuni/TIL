import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sw1491 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("1491.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; ++t) {
			long N = sc.nextLong();
			long A = sc.nextLong();
			long B = sc.nextLong();
			long min = Long.MAX_VALUE;
			
			if ((long) Math.sqrt(N) == Math.sqrt(N)) {
				min = 0;
			} else {
				for (long i = 1; i <= (long)Math.sqrt(N); ++i) {
					for (long j = i; (long)j*i <= N; ++j) {
						// A : ���簢���� ������ �ݿ� (���簢���� �ּ��� ���� Ŀ��)
						// B : N������ ��� �������� �ݿ� (���� ���� ���� ���� Ŀ��)
						long tmp = A * (long) (j - i) + B * (long)(N - (long)i*j);
						if (tmp < min)
							min = tmp;
					}
				}
			}
			System.out.printf("#%d %d\n", t, min);
		}
		sc.close();
	}

}
