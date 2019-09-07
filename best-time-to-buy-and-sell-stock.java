// 149. Best Time to Buy and Sell Stock
// 中文English
// Say you have an array for which the ith element is the price of a given stock on day i.
//
// If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
//
// Example
// Example 1
//
// Input: [3, 2, 3, 1, 2]
// Output: 1
// Explanation: You can buy at the third day and then sell it at the 4th day. The profit is 2 - 1 = 1
// Example 2
//
// Input: [1, 2, 3, 4, 5]
// Output: 4
// Explanation: You can buy at the 0th day and then sell it at the 4th day. The profit is 5 - 1 = 4
// Example 3
//
// Input: [5, 4, 3, 2, 1]
// Output: 0
// Explanation: You can do nothing and get nothing.


public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        // write your code here
        // 找array当中单调峰值和谷值差值最大的，因为是profit，所以是找峰值差值最大的一段单调上升区间
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // definition
        int n = prices.length;
        // int[] f = new int[n]; // traverse to the last position - the (n - 1)th
        // // initialization
        // f[0] = 0;
        // // DP
        // for (int i = 1; i < n; i++) {
        //     if (prices[i] >= prices[i - 1]) {
        //         f[i] = f[i - 1] + prices[i] - prices[i - 1];
        //     } else {
        //         f[i] = 0;
        //     }
        // }
        // // result
        // int max = 0;
        // for (int i = 0; i < n; i++) {
        //     if (f[i] > max) {
        //         max = f[i];
        //     }
        // }
        // return max;

        // traverse the array and update min price and max profit
        // int min = Integer.MAX_VALUE;
        // int profit = 0;
        // for (int i = 0; i < n; i++) {
        //     if (prices[i] < min) {
        //         min = prices[i];
        //     }
        //     if (prices[i] - min > profit) {
        //         profit = prices[i] - min;
        //     }
        // }
        // return profit;

        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (prices[i] - min > profit) {
                profit = prices[i] - min;
            }
        }
        return profit;
    }
}
