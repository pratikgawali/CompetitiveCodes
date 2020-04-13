import java.util.HashMap;
import java.util.Map;

/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a non-empty array arr[] of size n containing integers. The problem is 
to find the length of the longest sub-array having sum equal to the given 
value k.

Examples:

Input : arr[] = { 10, 5, 2, 7, 1, 9 }, 
        k = 15
Output : 4
The sub-array is {5, 2, 7, 1}.

Input : arr[] = {-5, 8, -14, 2, 4, 12},
        k = -5
Output : 5
---------------------------------------------------------------------------
*/
public class LongestSubarrayWithSumK {
	
	// logic: cumulativeSum[j] - cumulativeSum[i] == K implies sub-array i to j index sums to K.

	private static void longestSubarrayWithSumK(int[] arr, int k) {

		int[] cumulativeSum = new int[arr.length];

		// calculate cumulative sum at each index of arr
		cumulativeSum[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			cumulativeSum[i] = cumulativeSum[i - 1] + arr[i];
		}

		int startIndex = 0, endIndex = -1; // store result indexes
		Map<Integer, Integer> cumulativeSumIndexMap = new HashMap<>();	// to remember first index of each unique cumulative sum.

		for (int i = 0; i < cumulativeSum.length; i++) {

			// to record only the first index of unique cumulative sum seen
			if (!cumulativeSumIndexMap.containsKey(cumulativeSum[i])) {
				cumulativeSumIndexMap.put(cumulativeSum[i], i);
			}

			// if a cumulative sum lesser by exactly K value is already seen
			if (cumulativeSumIndexMap.containsKey(cumulativeSum[i] - k)) {

				// update result indexes if current sub-array with sum K is greater than the previously found
				if (i - cumulativeSumIndexMap.get(cumulativeSum[i] - k) > endIndex - startIndex + 1) {

					startIndex = cumulativeSumIndexMap.get(cumulativeSum[i] - k) + 1;
					endIndex = i;
				}
			} else {

				// cumulative sum with value K will always be the greater sub-array than the
				// currently max encountered.
				if (cumulativeSum[i] == k) {
					startIndex = 0;
					endIndex = i;
				}
			}
		}

		printArray(arr, startIndex, endIndex);
	}

	private static void printArray(int[] arr, int l, int r) {

		for (int i = l; i <= r; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static void main(String[] args) {

		int[] arr = { 10, 5, 2, 7, 1, 9 };
		int k = 15;

		System.out.print("Longest subarray having sum " + k + ": ");
		longestSubarrayWithSumK(arr, k);
	}
}