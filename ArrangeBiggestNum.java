import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given an array of numbers, arrange them in a way that yields the largest 
value. For example, if the given numbers are {54, 546, 548, 60}, the 
arrangement 6054854654 gives the largest value. And if the given numbers 
are {1, 34, 3, 98, 9, 76, 45, 4}, then the arrangement 998764543431 gives 
the largest value.
---------------------------------------------------------------------------
*/
public class ArrangeBiggestNum {

	private static void printLargestValue(int[] arr) {

		List<String> strList = Arrays.stream(arr).boxed().map(String::valueOf).collect(Collectors.toList());
		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String num1, String num2) {
				return (num2 + num1).compareTo(num1 + num2);
			}
		};

		Collections.sort(strList, comparator);
		strList.forEach(num -> System.out.print(num));
	}

	public static void main(String[] args) {

		int[] arr = { 1, 34, 3, 98, 9, 76, 45, 4 };

		System.out.print("Largest value possible: ");
		printLargestValue(arr);
	}
}