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
	private static int countArrangementOfCoins(int[] coins, int amount) {

		int[] dp = new int[amount + 1];
		dp[0] = 1;

		for (int a = 1; a <= amount; a++) {
			for (int c = 0; c < coins.length; c++) {
				dp[a] = a - coins[c] < 0 ? dp[a] : dp[a] + dp[a - coins[c]];
			}
		}

		return dp[amount];
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
				dp[a][c] = a - coins[c - 1] < 0 ? dp[a][c - 1] : dp[a - coins[c - 1]][c] + dp[a][c - 1];
			}
		}

		return dp[amount][coins.length];
	}

	// order is not important
	private static int minCoinsToPay(int[] coins, int amount) {

		final int INF = Integer.MAX_VALUE;

		int[][] dp = new int[amount + 1][coins.length + 1];

		// initialize dp with infinity since we are finding minimum
		for (int i = 0; i <= amount; i++) {
			for (int j = 0; j <= coins.length; j++) {
				dp[i][j] = INF;
			}
		}

		// no coins are needed to pay zero amount
		for (int c = 0; c <= coins.length; c++) {
			dp[0][c] = 0;
		}

		for (int a = 1; a <= amount; a++) {
			for (int c = 1; c <= coins.length; c++) {
				int coinsNeeded = a-coins[c-1] < 0 ? dp[a][c-1] : dp[a-coins[c-1]][c] + 1;
				dp[a][c] = Math.min(coinsNeeded, dp[a][c-1]);
			}
		}

		return dp[amount][coins.length];
	}

	public static void main(String[] args) {

		int[] coins = { 1, 2, 3 };
		int amount = 4;

		System.out.println("Number of arrangement of coins: " + countArrangementOfCoins(coins, amount));
		System.out.println("Number of ways of paying using the coins: " + countWaysOfPaying(coins, amount));
		System.out.println("Minimum number of coins needed: " + minCoinsToPay(coins, amount));
	}
}
