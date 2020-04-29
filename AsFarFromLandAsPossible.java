import java.util.ArrayDeque;
import java.util.Deque;

/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given an N x N grid containing only values 0 and 1, where 0 represents 
water and 1 represents land, find a water cell such that its distance to 
the nearest land cell is maximized and return the distance.

The distance used in this problem is the Manhattan distance: the distance 
between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

If no land or water exists in the grid, return -1.

Example 1:

Input: [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: 
The cell (1, 1) is as far as possible from all the land with distance 2.

Example 2:

Input: [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: 
The cell (2, 2) is as far as possible from all the land with distance 4.


Note:

1 <= grid.length == grid[0].length <= 100
grid[i][j] is 0 or 1
---------------------------------------------------------------------------
*/
public class AsFarFromLandAsPossible {

	private static int maxDistance(int[][] grid) {
	     
        int H = grid.length;
        int W = grid[0].length;
        
        Deque<Cell> dq = new ArrayDeque<>(Math.max(H, W));
        
        for (int r=0; r<H; r++) {
            for (int c=0; c<W; c++) {
                if (grid[r][c] == 1) {
                    dq.addLast(new Cell(r, c));
                }
            }
        }
        
        // trick to know level change in BFS
        int now = dq.size();
        int next = 0;
        
        int dist = 0;
        
        while (!dq.isEmpty()) {     //BFS
            
            if (now == 0) { // next level reached
                dist++;
                now = next;
                next = 0;
            }
            
            Cell cell = dq.removeFirst();
            now--;
            
            if (cell.r-1 >= 0 && cell.c < W && grid[cell.r-1][cell.c] == 0) {   // top
                grid[cell.r-1][cell.c] = 1;
                dq.addLast(new Cell(cell.r-1, cell.c));
                next++;
            }
            
            if (cell.r+1 < H && cell.c < W && grid[cell.r+1][cell.c] == 0) {   // bottom
                grid[cell.r+1][cell.c] = 1;
                dq.addLast(new Cell(cell.r+1, cell.c));
                next++;
            }
            
            if (cell.r < H && cell.c-1 >= 0 && grid[cell.r][cell.c-1] == 0) {   // left
                grid[cell.r][cell.c-1] = 1;
                dq.addLast(new Cell(cell.r, cell.c-1));
                next++;
            }
            
            if (cell.r < H && cell.c+1 < W && grid[cell.r][cell.c+1] == 0) {   // right
                grid[cell.r][cell.c+1] = 1;
                dq.addLast(new Cell(cell.r, cell.c+1));
                next++;
            } 
        }
        
        return dist == 0 ? -1 : dist;
    }
    
    private static class Cell{
        
        int r;
        int c;
        
        Cell(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
	
	public static void main(String[] args) {
		
		int[][] grid = { 
				{ 1, 0, 1 }, 
				{ 0, 0, 0 }, 
				{ 1, 0, 1 } 
			};
		
		System.out.println("Maximized distance of a water cell from land is: " + maxDistance(grid));
	}
	
	/*
	 * Strategy: BFS
	 * 
	 * Time-complexity : O(nm)
	 * Space-complexity : O(nm)
	 */
}