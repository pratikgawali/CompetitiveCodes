/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a set of denominations of coin with infinite coins available for each
denomination.

Solve all possible questions on coin change.
---------------------------------------------------------------------------
*/

public class CoinChange {

	// order is important
	private static int countArrangementOfCoins(int[] coins, int sum) {

		int[] dp = new int[sum + 1];
		dp[0] = 1;

		for (int s = 1; s <= sum; s++) {
			for (int c = 0; c < coins.length; c++) {
				dp[s] = s - coins[c] < 0 ? dp[s] : dp[s] + dp[s - coins[c]];
			}
		}

		return dp[sum];
	}

	// order is not important
	private static int countWaysOfPaying(int[] coins, int amount) {

		// dp[amount][limit of coin denominations]
		int[][] dp = new int[amount + 1][coins.length + 1];

		// no way to pay if coin denomination limit is 0
		for (int a = 1; a <= amount; a++) {
			dp[a][0] = 0;
		}

		// there is only one way to pay the amount 0
		for (int c = 0; c <= coins.length; c++) {
			dp[0][c] = 1;
		}

		for (int a = 1; a <= amount; a++) {
			for (int c = 1; c <= coins.length; c++) {
				dp[a][c] = a - coins[c-1] < 0 ? dp[a][c-1] : dp[a-coins[c-1]][c] + dp[a][c-1];
			}
		}

		return dp[amount][coins.length];
	}

	public static void main(String[] args) {

		int[] coins = { 1, 2, 3 };
		int sum = 4;

		System.out.println("Number of arrangement of coins: " + countArrangementOfCoins(coins, sum));
		System.out.println("Number of ways of paying using the coins: " + countWaysOfPaying(coins, sum));
	}
}
