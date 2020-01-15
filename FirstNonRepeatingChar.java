/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a string, find its first non-repeating character.
---------------------------------------------------------------------------
*/
public class FirstNonRepeatingChar {

	private static char firstNonRepeatingChar(String str) {
		
		int[] alphaCount = new int[256];
		char firstNonRepeatChar = 0;
		
		for (int i=str.length()-1; i>=0; i--) {
			char c = str.charAt(i);
			if (alphaCount[c] == 0) {
				firstNonRepeatChar = c;
			}
			alphaCount[c]++;
		}
		
		// case: when all chars are repeating
		if (firstNonRepeatChar != 0 && alphaCount[firstNonRepeatChar] > 1) {
			firstNonRepeatChar = 0;
		}
		return firstNonRepeatChar;
	}	
	
	public static void main(String[] args) {
		
		String str = "uyfedkmlsdmceyfu";
		
		char c = firstNonRepeatingChar(str);
		System.out.println("First non repeating character is: " + c);
	}
}