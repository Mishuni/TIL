import java.util.HashMap;
import java.util.Map;

public class Hash_3 {

	public static void main(String[] args) {
		String[][] clothes = {
				{"yellow_hat", "headgear"}, 
				{"green_turban", "headgear"},
				
				{"blue_sunglasses", "eyewear"}, 
				
				{"green_turban", "top"},
				
				{"ss","pants"},
				{"ss","pants"}
		};
		//int sum = clothes.length;
		int sum = 1;
		int index = 0 ;
		int[] num = new int[30];
		Map<String,Integer> cnt = new HashMap<String,Integer>();
		for(int i=0; i<clothes.length; ++i) {
			String wear = clothes[i][1];
			try {
				
				++num[cnt.get(wear)];
				
			}catch(Exception e) {
				
				cnt.put(wear,index);
				num[index++] = 1;
			}
		}
		for(int i=0; i<cnt.size(); ++i) {
			sum *= num[i]+1;
		}
		System.out.println(--sum);

	}

}

/*
 * ������ ��� ������ ���� ���ؼ� ����� �ʿ䰡 �����ϴ�. 
 * ������� �Ӹ�:3, ��:2, ��:1 �̶�� �� ������ ������
 * ���� �̴ϱ�, 0�� ��츸 �߰��ؼ� ���ָ� ��.
 * (3+1) * (2+1)*(1+1) -1 = 13
 */