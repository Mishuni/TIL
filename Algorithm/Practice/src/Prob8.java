import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Prob8 {

	public static void main(String[] args) {

		int x = 3;
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(2);
		arr.add(2);
		arr.add(4);
		arr.add(8);
		arr.add(5);
		System.out.println(segment(x, arr));

	}

	public static int segment(int x, List<Integer> arr) {

		int max = Collections.max(arr);
		if (x == 1) {
			return max;
		}
		int min = Collections.min(arr);
		int n = arr.size();
		if (x == n || min==max) {
			return min;
		}

		int extra = x;

		List<Integer> subArr = new LinkedList<Integer>();
		for (int i = 0; i < x; ++i) {
			subArr.add(arr.get(i));
		}
		int result = Collections.min(subArr);
		int subResult = result;

		for (; extra < n; ++extra) {
			int rmv = subArr.get(0);
			int apd = arr.get(extra);
			subArr.remove(0);
			subArr.add(apd);

			if (rmv == apd) {
				continue;
			}
			if (rmv != subResult) {
				if (apd >= subResult)
					continue;
				else
					subResult = apd;
			} 
			else {
				if (apd < subResult) {
					subResult = apd;
				} else if (apd == subResult) {
					continue;
				} else {
					subResult = Collections.min(subArr);
				}
			}

			if (subResult > result) {
				result = subResult;
			}
			if (result == max) {
				return result;
			}
		}

		return result;
	}

}
