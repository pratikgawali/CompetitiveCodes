import java.util.ArrayDeque;
import java.util.Deque;
/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a string, find its kth non-repeating character.
---------------------------------------------------------------------------
*/
public class KNonRepeatingChar {

	private static char kNonRepeatingChar(String str, int k) {
		
		Deque<Character> q = new ArrayDeque<>(k);	
		int[] alphaCount = new int[256];
		
		for (int i=str.length()-1; i>=0; i--) {
			char c = str.charAt(i);
			if (alphaCount[c] == 0) {
				if (q.size() == k) q.removeLast(); // since we need a stack of size k only
				q.push(c);
			}
			alphaCount[c]++;
		}
		
		if (q.size() < k) return 0;
		
		// pop k-1 elements to have kth non repeating char on top of the stack
		while (--k>0) q.pop();
		
		// check if top of stack char has occurred only once
		return alphaCount[q.peek()] == 1 ? q.pop() : 0;
	}	
	
	public static void main(String[] args) {
		
		String str = "ksuqmkzajsaowsnkxo";
		int k = 4;
		
		char c = kNonRepeatingChar(str, k);
		System.out.println(k + "th non repeating character is: " + c);
	}
}