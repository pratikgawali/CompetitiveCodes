/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given the string croakOfFrogs, which represents a combination of the string 
"croak" from different frogs, that is, multiple frogs can croak at the same 
time, so multiple “croak” are mixed. Return the minimum number of different 
frogs to finish all the croak in the given string.

A valid "croak" means a frog is printing 5 letters ‘c’, ’r’, ’o’, ’a’, ’k’ 
sequentially. The frogs have to print all five letters to finish a croak. 
If the given string is not a combination of valid "croak" return -1.

Example 1:

Input: croakOfFrogs = "croakcroak"
Output: 1 
Explanation: One frog yelling "croak" twice.

Example 2:

Input: croakOfFrogs = "crcoakroak"
Output: 2 
Explanation: The minimum number of frogs is two. 
			 The first frog could yell "crcoakroak".
			 The second frog could yell later "crcoakroak".

Example 3:

Input: croakOfFrogs = "croakcrook"
Output: -1
Explanation: The given string is an invalid combination of "croak" from 
			 different frogs.
Example 4:

Input: croakOfFrogs = "croakcroa"
Output: -1
 

Constraints:

1 <= croakOfFrogs.length <= 10^5
All characters in the string are: 'c', 'r', 'o', 'a' or 'k'.
---------------------------------------------------------------------------
*/
public class MinFrogsCroaking {

	private static int minNumberOfFrogs(String croakOfFrogs) {

		int n = croakOfFrogs.length();

		if (n < 5) {
			return -1;
		}

		// 'c', 'r', 'o', 'a', 'k'
		int[] count = new int[5];

		int maxParallelFrogs = 0;
		int parallelFrogs = 0;

		for (int i = 0; i < n; i++) {

			char c = croakOfFrogs.charAt(i);

			switch (c) {
			case 'c':
				count[0]++;
				parallelFrogs++;
				maxParallelFrogs = Math.max(maxParallelFrogs, parallelFrogs);
				break;
			case 'r':
				if (count[0] > count[1]) {
					count[1]++;
				} else {
					return -1;
				}
				break;
			case 'o':
				if (count[1] > count[2]) {
					count[2]++;
				} else {
					return -1;
				}
				break;
			case 'a':
				if (count[2] > count[3]) {
					count[3]++;
				} else {
					return -1;
				}
				break;
			case 'k':
				if (count[3] > count[4]) {
					count[4]++;
					parallelFrogs--;
				} else {
					return -1;
				}
				break;
			}
		}

		return parallelFrogs != 0 ? -1 : maxParallelFrogs;
	}

	public static void main(String[] args) {

		String croakOfFrogs = "crcoakroak";
		System.out.println(
				"Minimum number of different frogs to finish all the croaks are: " + minNumberOfFrogs(croakOfFrogs));
	}
}