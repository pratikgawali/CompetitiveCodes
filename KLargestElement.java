import java.util.Comparator;
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
		
		Integer[] arr = {7, 10, 4, 3, 20, 15};
		int k = 4;
		
		System.out.println(findKLargest(arr, k));
	}

	private static Integer findKLargest(Integer[] arr, int k) {
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return i2.compareTo(i1);
			}
		});

		for (int i=0; i<k; i++) maxHeap.add(arr[i]);
		
		for (int i=k; i < arr.length; i++) {
			Integer root = maxHeap.peek();
			if (arr[i].compareTo(root) == -1) {
				maxHeap.poll();
				maxHeap.add(arr[i]);
			}
		}	
		
		return maxHeap.peek();
	}
}
