import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a 2D grid of characters and a word, find all occurrences of given 
word in grid. A word can be matched in all 8 directions at any point. 
Word is said be found in a direction if all characters match in this 
direction (not in zig-zag form).
---------------------------------------------------------------------------
*/
public class WordInAMatrix {

	private static int[] h = { +1, +1, +0, +0, -1, -1, -1, +1 };
	private static int[] v = { +0, +1, +1, -1, +0, -1, +1, -1 };
	
	static class Cell {
		int row, col;
		
		Cell(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static boolean isValid(char[][] grid, int r, int c) {
		
		int m = grid.length, n = grid[0].length;	// [m x n] matrix
		return r >= 0 && r < m && c >= 0 && c < n;
	}
	
	private static void printCellStack(Deque<Cell> deque) {
		
		Iterator<Cell> iterator = deque.iterator();
		while (iterator.hasNext()) {
			Cell cell = iterator.next();
			System.out.print("(" + cell.row + "," + cell.col + ")");
		}
		
		System.out.println();
	}
	
	private static void findWord(char[][] grid, char[] word) {
		
		int m = grid.length, n = grid[0].length;
		Deque<Cell> deque;
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				deque = new ArrayDeque<Cell>(word.length);
				findWord(grid, word, i, j, 0, deque);
			}
		}
	}

	private static void findWord(char[][] grid, char[] word, int r, int c, int pos, Deque<Cell> deque) {

		if (!isValid(grid, r, c)) return;
		if (grid[r][c] != word[pos]) return;
		
		deque.addLast(new Cell(r, c));
		
		if (pos == word.length-1) {
			System.out.print("Found at:");
			printCellStack(deque);
			deque.removeLast();
			return;
		}
		
		for (int i=0; i<8; i++)
			findWord(grid, word, r+h[i], c+v[i], pos+1, deque);
		
		deque.removeLast();
	}

	public static void main(String[] args) {

		char[][] grid = { 
				{ 'B', 'N', 'E', 'Y', 'S' }, 
				{ 'H', 'E', 'D', 'E', 'S' }, 
				{ 'S', 'G', 'N', 'D', 'E' } };

		char[] word = { 'D', 'E', 'S' };

		findWord(grid, word);
	}
}

/*
---------------------------------------------------------------------------
Note
---------------------------------------------------------------------------
Solution:
	1. Allows cycle.
	2. Prefers space complexity [O(word_len)] over 
	   time complexity [O(word_len x m x n x 8)].
---------------------------------------------------------------------------
*/