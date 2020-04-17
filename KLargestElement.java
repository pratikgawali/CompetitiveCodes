import java.util.PriorityQueue;
/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given an array and a number k where k is smaller than size of array, we 
need to find the k’th largest element in the given array. It is given 
that all array elements are distinct.
---------------------------------------------------------------------------
*/
public class KLargestElement {

	public static void main(String[] args) {
		
		int[] arr = {7, 10, 4, 3, 20, 15};
		int k = 2;
		
		System.out.println(findKthLargest(arr, k));
	}

	private static Integer findKthLargest(int[] arr, int k) {
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		for (int i=0; i<k; i++) minHeap.add(arr[i]);
		
		for (int i=k; i < arr.length; i++) {
			Integer root = minHeap.peek();
			if (arr[i] > root) {
				minHeap.poll();
				minHeap.add(arr[i]);
			}
		}	
		
		return minHeap.peek();
	}
}
