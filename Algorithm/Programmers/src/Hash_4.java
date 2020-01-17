import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hash_4 {

	public static void main(String[] args) {
		String[] genres= {"classic", "pop", "classic", 
				"classic", "pop","dance","k-pop","k-pop","k-pop"};
		int[] plays = {500, 600, 150, 800, 2500,1000,2000,3000,500};
		// ����  : [7, 6, 4, 1, 3, 0, 5]
		
		Map<String,Genre> rank = new HashMap<String,Genre>();

		for(int i=0; i<genres.length; ++i) {
			try {
				// ���� Ű ���� ���� ���� ó��
				rank.get(genres[i]).add(plays[i],i);
				
			}catch(Exception e) {
				// Ű �� �߰�
				rank.put(genres[i], new Genre(plays[i],i));
			}
		}
		
		List<Genre> tmp = new ArrayList<Genre>(rank.values());
		// �帣���� �� ��� �� ��� ����
		Collections.sort(tmp);
		
		// �帣�� ��� �� 1,2�� ���� ��ȣ ��ġ�� 
		List<Integer> ans_tmp = new ArrayList<Integer>();
		for(int i=0; i<tmp.size(); ++i) {
			Genre tt = tmp.get(i);
			ans_tmp.add( tt.index[0]);
			if(tt.index[1]!=-1) {
				// �� �帣�� ���ϵ� ���� 2�� �̻��� ���
				ans_tmp.add( tt.index[1]);
			}
		}
		
		// List �� �迭�� ��ȯ �ϱ� ���� �۾�
		int[] answer = new int[ans_tmp.size()];
		for(int i = 0; i<answer.length; ++i) {
			answer[i] = ans_tmp.get(i);
		}
		
		// ��� Ȯ��
		System.out.println(Arrays.toString(answer));
	}

}

class Genre implements Comparable<Genre>{
	// �帣�� ������ ��� Ŭ����
	int[] play;
	int[] index;
	int sum;
	
	Genre(int first, int ind_f){
		play = new int[2];
		index = new int[2];
		play[0] = first;
		index[0] = ind_f;
		index[1] = -1;
		sum = first;
	}
	
	public void add(int value, int ind) {
		sum+=value;
		if(play[0]<value) {
			// �Էµ� ��� ���� ���� 1�� ��� �� ���� Ŭ ��
			// 1���� �Էµ� �� �ְ�, 1���� 2���� ����
			int tmp = play[0];
			play[0] = value;
			play[1] = tmp;
			tmp = index[0];
			index[0] = ind;
			index[1] = tmp;
		}
		else if(play[0]==value && value>play[1]) {
			// 1�� ���� ������ 2�� ������ Ŭ �� 2���� ����
			play[1] = value;
			index[1] = ind;
		}
		else {
			// �Էµ� ��� ���� ���� 1�� ��� �� ���� ���� ��
			if(play[1]<value) {
				// �Էµ� ��� ���� ���� 2�� ��� �� ���� ū ���
				// 2���� ����
				play[1] = value;
				index[1] = ind;
			}
		}
	}

	@Override
	public int compareTo(Genre a) {
		// �帣�� �񱳸� ���� �Լ�, sum ���� ũ�� �� �տ� ����
		if(this.sum>a.sum) {
			return -1;
		}
		return 1;
	}
	
}