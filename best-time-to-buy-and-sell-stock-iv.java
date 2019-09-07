// 393. Best Time to Buy and Sell Stock IV
// 中文English
// Given an array prices and the i-th element of it represents the price of a stock on the i-th day.
//
// You may complete at most k transactions. What's the maximum profit?
//
// Example
// Example 1:
//
// Input: k = 2, prices = [4, 4, 6, 1, 1, 4, 2 ,5]
// Output: 6
// Explanation: Buy at 4 and sell at 6. Then buy at 1 and sell at 5. Your profit is 2 + 4 = 6.
// Example 2:
//
// Input: k = 1, prices = [3, 2, 1]
// Output: 0
// Explanation: No transaction.
// Challenge
// O(nk) time. n is the size of prices.
//
// Notice
// You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
//


class Solution {
    /**
     * @param k: An integer
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int k, int[] prices) {
        // write your code here
        if (prices == null || prices.length == 0 || k <= 0) {
            return 0;
        }
        int n = prices.length;
        if (k >= n / 2) {
            return maxProfit2(prices);
        }
        // definition
        int[][] local = new int[n][k + 1];
        int[][] global = new int[n][k + 1];
        // initialization
        // we would initialize all as 0
        // DP
        for (int i = 1; i < n; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                local[i][j] = Math.max(global[i - 1][j - 1] + diff, local[i - 1][j] + diff);
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }
        // result
        return global[n - 1][k];

        // 1D array DP
        // int[] local = new int[k + 1];
        // int[] global = new int[k + 1];

        // for (int i = 1; i < prices.length ; i++) {
        //     int diff = prices[i] - prices[i - 1];

        //     for (int j = k; j > 0; j--) {
        //         local[j] = Math.max(global[j - 1], local[j] + diff);
        //         global[j] = Math.max(global[j], local[j]);
        //     }
        // }

        // return global[k];

    }
    private int maxProfit2(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                sum += prices[i] - prices[i - 1];
            }
        }
        return sum;
    }
};
