import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Bj1068 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Map<Integer, ArrayList<Integer>> tree = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> tmp;
		// tree ���� : (�θ���,[�ڽĳ��1, �ڽĳ��2, ...])
		
		// 1. �Է� �ޱ�
		for (int i = 0; i < N; i++) {
			int parent = sc.nextInt();
			if (parent == -1) {
				// 1-1. ��Ʈ ����� ��� �θ��� �Է����� �ʱ�
				continue;
			} else {
				try {
					// 1-2-1. �̹� �θ��尡 �����ϴ� ��� �ڽ� �߰�
					tree.get(parent).add(i);
				} catch (Exception e) {
					// 1-2-2. �Է¹��� ���� �ڽ� ��尡 �ϳ��� ������ ���, �θ��� ����� �ڽ� �߰�
					tmp = new ArrayList<Integer>();
					tmp.add(i);
					tree.put(parent, tmp);
				}
			}
		}
		
		int start = sc.nextInt(); // ���� ������ ��� ��ȣ
		sc.close();
		sc = null;
		
		// 2. ��� ���� (BFS, �ʺ� �켱 Ž�� ���)
		//    ���� ���� ���� �ڽ��� �����, �ڽĳ�带 ť�� �ø���,
		//    ť�� ����� ��� ������, �� ��尡 �ڽĳ�� ���ϸ� �����
		//    �� �ڽĳ�� ť�� �ٽ� �ø��� �ݺ�
		Queue<Integer> target = new LinkedList<Integer>();
		target.add(start);
		while (!target.isEmpty()) {
			int node = target.remove();
			try {
				tmp = tree.remove(node);
				for (int j = 0; j < tmp.size(); j++) {
					target.add(tmp.get(j));
				}
			} catch (Exception e) {
				continue;
			}
		}
		
		// 3. ���� ��� ����
		Set<Integer> key = tree.keySet(); // �ڽĳ�尡 �ִ� �θ� ��� ���
		int leaf = 0;
		for (int n : key) {
			tmp = tree.get(n);
			for (int i = 0; i < tmp.size(); i++) {
				if (tmp.get(i) == start) {
					// 3-1. ���� �ڽĳ�尡 ���� ���������� ���
					if (tmp.size() == 1) {
						// 3-1-1. ���� ���������� �θ� ���������� �ڽ����� ������ �ִٸ�
						// 		     ���������� ���ŵǰ�, �θ� ������尡 �ǹǷ� ������� �߰�
						leaf++;
					}
				} else {
					// 3-2. �ڽĳ�� ���� ���� ���������� �ƴ� ���
					boolean check = false;
					for (int m : key) {
						if (tmp.get(i) == m) {
							// 3-2-1. �ڽ� ��尡 �ִ� ��� ������尡 �ƴϴϱ� pass
							check = true;
							break;
						}
					}
					if (!check) {
						// 3-3. check �� false��� ���� �ڽ� ��尡 ������ ���ϹǷ� ������� �߰�
						leaf++;
					}
				}
			}
		}
		System.out.println(leaf);
	}
}
/*
 * 7
 * 
 * 3 6 6 -1 0 6 3
 * 
 * 4
 * 
 * ���� : 4
 */

/*
 * 5 -1 0 0 1 1 0 ���� : 0
 */