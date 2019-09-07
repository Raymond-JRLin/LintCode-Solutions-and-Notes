// 151. Best Time to Buy and Sell Stock III
// 中文English
// Say you have an array for which the ith element is the price of a given stock on day i.
//
// Design an algorithm to find the maximum profit. You may complete at most two transactions.
//
// Example
// Example 1
//
// Input : [4,4,6,1,1,4,2,5]
// Output : 6
// Notice
// You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//


class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        // write your code here
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[] left = new int[n];
        int[] right = new int[n];
        // find the max profit from left - based on min price
        int min = prices[0];
        left[0] = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }
        // find the max profit from right - based on max price;
        int max = prices[n - 1];
        right[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (prices[i] > max) {
                max = prices[i];
            }
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }
        // find the total max profit by traverse left and right
        int result = left[0] + right[0];
        for (int i = 1; i < n; i++) {
            if (left[i] + right[i] > result) {
                result = left[i] + right[i];
            }
        }
        return result;
    }
};
