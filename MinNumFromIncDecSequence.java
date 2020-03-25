/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a pattern containing only I’s and D’s. I for increasing and D for 
decreasing. Devise an algorithm to print the minimum number following that 
pattern. Digits from 1-9 and digits can’t repeat.
---------------------------------------------------------------------------
Examples:

Input: D        Output: 21
Input: I        Output: 12
Input: DD       Output: 321
Input: II       Output: 123
Input: DIDI     Output: 21435
Input: IIDDD    Output: 126543
Input: DDIDDIID Output: 321654798 
---------------------------------------------------------------------------
*/
public class MinNumFromIncDecSequence {

	private static void printMinNum(char[] sequence) {

		int maxSoFar = 0;
		int i = -1;

		while (i < sequence.length) {

			int dist = distanceFromNextIncrement(sequence, i);
			maxSoFar += dist;
			i += dist;

			int val = maxSoFar;
			while (dist > 0) {
				System.out.print(val);
				val--;
				dist--;
			}
		}
	}

	private static int distanceFromNextIncrement(char[] sequence, int currIndex) {

		int dist = 1;
		int i = currIndex + 1;

		while (i < sequence.length) {
			if (sequence[i] == 'I') {
				break;
			}
			dist++;
			i++;
		}

		return dist;
	}

	public static void main(String[] args) {

		char[] sequence = { 'D', 'D', 'I', 'D', 'D', 'I', 'I', 'D' };

		System.out.print("Minimum number: ");
		printMinNum(sequence);
	}
}