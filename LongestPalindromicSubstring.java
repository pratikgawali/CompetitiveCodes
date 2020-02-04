/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a non-empty string, find the longest palindromic substring.
---------------------------------------------------------------------------
*/
public class MaxPalindromeSubstring {

	private static String maxPalindromeSubstrDP(String s) {
		
		int n = s.length();
		int startIndex=0, endIndex=0, maxSubstrLen=1;
		int[][] palindrome = new int[n][n];
		
		// all chars are palindrome
		for (int i=0; i<n; i++) palindrome[i][i] = 1;
		
		// check for palindromic substring of length 2
		for (int i=0; i<n-1; i++) {
			if (s.charAt(i) == s.charAt(i+1)) {
				palindrome[i][i+1] = 2;
				startIndex = i;
				endIndex = i+1;
			}
		}
		
		// check from substring of length 3
		for (int i=2; i<n; i++) {
			for (int j=0, k=i; k<n; j++, k++) {
				
				if (s.charAt(j) == s.charAt(k) && palindrome[j+1][k-1] > 0) 
					palindrome[j][k] = palindrome[j+1][k-1] + 2;
				
				if (palindrome[j][k] > maxSubstrLen) {
					startIndex = j;
					endIndex = k;
					maxSubstrLen = palindrome[j][k];
				}
			}
		}
		
		return s.substring(startIndex, endIndex+1);
	}

	private static String maxPalindromeSubstr(String str) {

		int n = str.length();
		int startIndex = 0, endIndex = 0, maxSubstrLen = 1;

		// for odd length palindrome
		for (int mid = 0; mid < n; mid++) {
			int i = 1, count = 1;
			while (mid - i >= 0 && mid + i < n && str.charAt(mid - i) == str.charAt(mid + i)) {
				count++;
				i++;
			}
			if (count > maxSubstrLen) {
				maxSubstrLen = count;
				startIndex = mid - i + 1;
				endIndex = mid + i - 1;
			}
		}

		// for even length palindrome
		for (int mid = 1; mid < n; mid++) {
			int i = 1, count = 1;
			while (mid - i >= 0 && mid + i - 1 < n && str.charAt(mid - i) == str.charAt(mid + i - 1)) {
				count++;
				i++;
			}
			if (count > maxSubstrLen) {
				maxSubstrLen = count;
				startIndex = mid - i + 1;
				endIndex = mid + i - 2;
			}
		}

		return str.substring(startIndex, endIndex + 1);
	}

	public static void main(String[] args) {

		String str = "alkjabccbaksiiskab";
		System.out.println("Maximum Palindrome Substring is: " + maxPalindromeSubstr(str));
	}
}
