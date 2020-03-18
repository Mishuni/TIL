import java.util.*;

public class Solv {

	public static int partition(int[] arr, int left, int right) {

		int pivot = (left + right) / 2;
		
		// 작은 거는 pivot의 왼쪽, 큰 거는 오른쪽
		while (left < right) {
			while (left <= right && arr[left] <= arr[pivot])
				++left;
			while (left <= right && arr[right] > arr[pivot])
				--right;

			if (left <= right) {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				// 오른쪽 검사가 다 끝난 경우는 right=pivot
				// right은 arr[pivot]과 같은 값도 멈추기 때문
				if (right == pivot) {
					// left 와 pivot을 swap 한 경우이므로
					// 기준 점을 left로 수정
					return left;
				}
			}
		}
		// left는 검사가 끝났는데, right는 안 끝난 경우
		// pivot의 기준점을 right으로 바꾸기
		if (right != pivot) {
			int temp = arr[right];
			arr[right] = arr[pivot];
			arr[pivot] = temp;
		}
		return right;

	}

	public static void quickSort(int[] ori, int left, int right) {

		if (left < right) {
			
			int newPivot = partition(ori, left, right);
			// 기준의 왼쪽 배열들
			quickSort(ori, left, newPivot - 1);
			// 기준의 오른쪽 값들
			quickSort(ori, newPivot + 1, right);
			
		}
	}
	
	// 정렬 실행해보기
	public static void main(String[] args) {

		int[] a = { 1, 3, 56, 8, 9, 3, 2, 1, 23, 11, 10 };
		quickSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));

	}
}