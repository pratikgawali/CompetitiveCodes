/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given that integers are read from a data stream. Find median of elements 
read so for in an efficient way. For simplicity assume there are no 
duplicates.
---------------------------------------------------------------------------
*/
import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianInIntegerStream {
	
	private static void addNumber(int num, PriorityQueue<Integer> lowerBucket, PriorityQueue<Integer> higherBucket) {
		
		if (lowerBucket.size() == 0 || num < lowerBucket.peek()) {
			lowerBucket.add(num);
		}
		else {
			higherBucket.add(num);
		}
	}
	
	private static void rebalance(PriorityQueue<Integer> lowerBucket, PriorityQueue<Integer> higherBucket) {
		
		PriorityQueue<Integer> smallerBucket = lowerBucket.size() <= higherBucket.size() ?  lowerBucket : higherBucket;
		PriorityQueue<Integer> biggerBucket = lowerBucket.size() > higherBucket.size() ?  lowerBucket : higherBucket;
		
		if (biggerBucket.size() - smallerBucket.size() > 1) {
			smallerBucket.add(biggerBucket.poll());
		}
	}
	
	private static double getMedian(PriorityQueue<Integer> lowerBucket, PriorityQueue<Integer> higherBucket) {
		
		if (lowerBucket.size() == higherBucket.size()) {
			return ((double) lowerBucket.peek() + higherBucket.peek())/2;
		}
		else {
			PriorityQueue<Integer> biggerBucket = lowerBucket.size() > higherBucket.size() ?  lowerBucket : higherBucket;
			return biggerBucket.peek();
		}
	}
	
	public static void main(String[] args) {
		
		PriorityQueue<Integer> higherBucket = new PriorityQueue<Integer>();
		PriorityQueue<Integer> lowerBucket = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return i2.compareTo(i1);
			}
		});
		
		int[] stream = {5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4};
		
		for(int i = 0; i < stream.length; i++) {
			addNumber(stream[i], lowerBucket, higherBucket);
			rebalance(lowerBucket, higherBucket);
			double median = getMedian(lowerBucket, higherBucket);
			System.out.println(median);
		}
	}
}
