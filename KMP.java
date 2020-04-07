/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given two strings str1 and str2, find all occurrences of str2 into str1.
---------------------------------------------------------------------------
*/

// Algorithm to match a pattern of size m in a text of size n in O(m+n) time complexity & O(m) space complexity.
public class KMP {

	private static void findSubstr(String str1, String str2) {

		int[] prefixMatchArr = generatePrefixMatchArray(str2);

		int i = 0, j = 0, n = str1.length(), m = str2.length();
		while (i < n) {
			if (str1.charAt(i) == str2.charAt(j)) {
				i++;
				j++;
				if (j == m) {
					j = prefixMatchArr[j-1];
					System.out.println(i - m);
				}
			} else if (j != 0) {
				j = prefixMatchArr[j - 1];
			} else {
				i++;
			}
		}
	}

	// each element of the array denotes the number of prefix chars matched at that point
	// visualize each element as prefix window size
	private static int[] generatePrefixMatchArray(String str) {

		int[] prefixMatchArr = new int[str.length()];

		int prefixMatchSize = 0, i = 1;
		while (i < prefixMatchArr.length) {

			if (str.charAt(prefixMatchSize) == str.charAt(i)) {
				prefixMatchArr[i] = prefixMatchSize + 1;
				prefixMatchSize++;
				i++;
			} else if (prefixMatchSize != 0) {
				prefixMatchSize = prefixMatchArr[prefixMatchSize - 1];
			} else {
				i++;
			}
		}

		return prefixMatchArr;
	}

	public static void main(String[] args) {

		String s1 = "AABAACAADAABAABAABA";
		String s2 = "AABA";

		System.out.println("Substring " + s2 + " is found starting at indices:-");
		findSubstr(s1, s2);
	}
}
