// 798. Backpack VII
// 中文English
// Assume that you have n yuan. There are many kinds of rice in the supermarket. Each kind of rice is bagged and must be purchased in the whole bag. Given the weight, price and quantity of each type of rice, find the maximum weight of rice that you can purchase.
//
// Example
// Example 1:
//
// Input:  n = 8, prices = [3,2], weights = [300,160], amounts = [1,6]
// Output:  640
// Explanation:  Buy the second rice(price = 2) use all 8 money.
// Example 2:
//
// Input:  n = 8, prices  = [2,4], weight = [100,100], amounts = [4,2 ]
// Output:  400
// Explanation:  Buy the first rice(price = 2) use all 8 money.


public class Solution {
    /**
     * @param n: the money of you
     * @param prices: the price of rice[i]
     * @param weight: the weight of rice[i]
     * @param amounts: the amount of rice[i]
     * @return: the maximum weight
     */
    public int backPackVII(int n, int[] prices, int[] weight, int[] amounts) {
        // write your code here

        return method1(n, prices, weight, amounts);
    }

    private int method1(int n, int[] prices, int[] weights, int[] amounts) {
        int len = prices.length;
        // definition: f[i] = i 这么多钱可以买的 weight
        int[] f = new int[n + 1];
        // initialization
        f[0] = 0;
        // DP
        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= amounts[i]; j++) {
                for (int k = n; k >= prices[i]; k--) {
                    f[k] = Math.max(f[k], f[k - prices[i]] + weights[i]);
                }
            }
        }
        // result
        return f[n];
    }
}
