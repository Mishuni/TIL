package day10;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Test07 {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Java01", "1234");
		map.put("Java02", "1821");
		map.put("Java03", "4682");
		map.put("Java04", "8895");
		map.put("Java05", "1566");
		map.put("Java06", "3466");
		map.put("Java07", "8989");
		map.put("Java08", "1111");
		map.put("Java09", "2222");
		
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key+":"+map.get(key));
		}
		System.out.println();
		// login process
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("put your ID/PASSWORD");
			String[] tmp = sc.nextLine().trim().split("/");
			String id = tmp[0];
			String pw = tmp[1];
			
			if(!map.containsKey(id)) {
				System.out.println("id x");
				continue;
			}
			else if(map.get(id).equals(pw)) {
				System.out.println("successfully completed");
				break;
			}else {
				System.out.println("failed");
			}
		}
		
		System.out.println("END");
		sc.close();
		sc= null;
	}
	

}
