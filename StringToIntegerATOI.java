/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary 
until the first non-whitespace character is found. Then, starting from this 
character, takes an optional initial plus or minus sign followed by as many 
numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the 
integral number, which are ignored and have no effect on the behavior of 
this function.

If the first sequence of non-whitespace characters in str is not a valid 
integral number, or if no such sequence exists because either str is empty 
or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers 
within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical 
value is out of the range of representable values, INT_MAX (231 − 1) or 
INT_MIN (−231) is returned.

Example 1:

Input: "42"
Output: 42

Example 2:

Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus 
			 sign. Then take as many numerical digits as possible, which 
			 gets 42.

Example 3:

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a 
			 numerical digit.
			 
Example 4:

Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a 
			 numerical digit or a +/- sign. Therefore no valid conversion 
			 could be performed.
			 
Example 5:

Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit 
			 signed integer. Therefore INT_MIN (−231) is returned.
---------------------------------------------------------------------------
*/
public class StringToIntegerATOI {

	private static int myAtoi(String str) {

		int n = str.length();

		if (n == 0) { // empty string -> no conversion
			return 0;
		}

		int i = 0;

		// identify first non-space index
		while (i < n && str.charAt(i) == ' ') {
			i++;
		}

		if (i == n) { // means only spaces -> no conversion
			return 0;
		}

		boolean isPositive = true;

		// check optional sign char
		if (str.charAt(i) == '-') {
			isPositive = false;
			i++;
		} else if (str.charAt(i) == '+') {
			i++;
		}

		// ignore leading zeroes
		while (i < n && str.charAt(i) == '0') {
			i++;
		}

		int e = i; // end index to mark end of integral value (not inclusive)
		while (e < n && str.charAt(e) >= '0' && str.charAt(e) <= '9') {
			e++;
		}

		String intStr = str.substring(i, e); // integral string
		int len = intStr.length();

		if (len == 0) { // no integral string found -> no conversion
			return 0;
		}

		if (len > 10) { // integer is out of range
			return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		} else if (len == 10) { // when comparable to max limit

			if (isPositive) {
				if (intStr.compareTo("2147483647") >= 0) {
					return Integer.MAX_VALUE;
				}
			} else {
				if (intStr.compareTo("2147483648") >= 0) {
					return Integer.MIN_VALUE;
				}
			}
		}

		int intVal = Integer.valueOf(intStr);

		return isPositive ? intVal : -1 * intVal;
	}

	public static void main(String[] args) {

		String str = "    -000091283472332";

		System.out.println("Integer conversion of '" + str + "' is: " + myAtoi(str));
	}
}