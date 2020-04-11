/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given two strings str and pattern, the task is to find the smallest 
substring in str containing all characters of pattern efficiently.

Examples:

Input: str = “this is a test string”, pattern = “tist”
Output: Minimum window is “t stri”
Explanation: “t stri” contains all the characters of pattern.

Input: str = “geeksforgeeks”, pattern = “ork”
Output: Minimum window is “ksfor”
---------------------------------------------------------------------------
*/
public class SmallestWindowWithAllPatternChars {

	private static String smallestSubstr(String str, String pattern) {

		// to store the count of characters in each string
		int[] strCharCount = new int[256];
		int[] patternCharCount = new int[256];

		int strLen = str.length();
		int patternLen = pattern.length();

		// count each character in the given pattern
		for (int i = 0; i < patternLen; i++) {
			patternCharCount[pattern.charAt(i)]++;
		}

		int ansL = -1, ansR = strLen;

		int l = 0, r = 0;		// to identify the current window
		int matchedCount = 0;	// to store the count of characters matched in the current window
		
		while (r < strLen) {

			char c = str.charAt(r);

			strCharCount[c]++;
			if (strCharCount[c] <= patternCharCount[c]) {	
				matchedCount++;		// increment matched count if required char is found
			}

			if (matchedCount == patternLen) {
				
				// check if currently satisfying window is smaller than previously identified window
				if ((r - l) < (ansR - ansL)) {
					ansL = l;
					ansR = r;
				}
				
				// shrink the window from the left until the window stops satisfying
				while (l < r) {

					char leftRemovedChar = str.charAt(l);

					strCharCount[leftRemovedChar]--;
					l++;

					if (strCharCount[leftRemovedChar] < patternCharCount[leftRemovedChar]) {
						matchedCount--;	// current window now does not satisfy
						break;
					}
					else { 
						// current window still satisfies after a shrink from left
						if ((r-l) < (ansR-ansL)) {
							ansL = l;
							ansR = r;
						}
					}
				}
			} 
			
			r++; // expand the window from the right
		}

		return ansL == -1 ? "" : str.substring(ansL, ansR + 1);
	}

	public static void main(String[] args) {

		String str = "this is a test string";
		String pattern = "tist";

		System.out.println(
				"Smallest substring containing all characters of the pattern is: " + smallestSubstr(str, pattern));
	}

}
