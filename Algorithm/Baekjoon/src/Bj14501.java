import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Bj14501 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		List<Day> dayList = new ArrayList<Day>();
		for(int i=0; i<N; i++) {
			int t = sc.nextInt();
			int p = sc.nextInt();
			if(i+t>N) {
				continue;
			}
			else {
			dayList.add(new Day(i+1,t,p));}
		}
		Collections.sort(dayList);
		
		int sum = 0;
		

	}

}

class Day implements Comparable<Day> {
	int start;
	int end;
	int money;
	int money_per_day;
	
	Day(int start, int t, int p){
		this.start = start;
		this.end = start + p;
		this.money = p;
		this.money_per_day = p / t;
	}

	@Override
	public int compareTo(Day d) {
		if(this.end>d.end) {
			return 1;
		}
		else if(this.end==d.end) {
			
		}
		return -1;
	}
	
}
