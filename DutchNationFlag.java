/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given an array consisting 0s, 1s and 2s. The task is to write a 
function that sorts the given array. The functions should put all 0s first, 
then all 1s and all 2s in last.
---------------------------------------------------------------------------
*/
public class DutchNationFlag {

	private static void sort(int[] arr) {

		int i = 0;
		int j = arr.length - 1;
		int x = 0;

		while (x < j) {
			switch (arr[x]) {
			case 0:
				swap(arr, x, i);
				i++;
				x++;
				break;
			case 1:
				x++;
				break;
			case 2:
				swap(arr, x, j);
				j--;
			}
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {

		int[] arr = { 0, 2, 1, 2, 0, 0, 1, 2, 0, 2, 1, 1, 0 };
		sort(arr);

		for (int i = 0, n = arr.length; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
