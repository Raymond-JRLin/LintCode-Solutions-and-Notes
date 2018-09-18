// 396. Coins in a Line III
// Description
// There are n coins in a line. Two players take turns to take a coin from one of the ends of the line until there are no more coins left. The player with the larger amount of money wins.
//
// Could you please decide the first player will win or lose?
//
// Have you met this question in a real interview?
// Example
// Given array A = [3,2,2], return true.
//
// Given array A = [1,2,4], return true.
//
// Given array A = [1,20,4], return false.
//
// Challenge
// Follow Up Question:
//
// If n is even. Is there any hacky algorithm that can decide whether first player will win or lose in O(1) memory and O(n) time?


public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        if (values == null || values.length == 0) {
            return false;
        }
        if (values.length == 1) {
            return true;
        }
        int n = values.length;
        // sum function to record sum from i to j
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (j == i) {
                    sum[i][j] = values[j];
                } else {
                    sum[i][j] = sum[i][j - 1] + values[j];
                }
            }
        }
        // definition
        int[][] f = new int[n][n];
        // DP
        for (int left = n - 1; left >= 0; left--) {
            for (int right = left; right < n; right++) {
                if (right == left) {
                    f[left][right] = values[left];
                } else {
                    f[left][right] = sum[left][right] - Math.min(f[left + 1][right], f[left][right - 1]);
                }
            }
        }
        // result
        return f[0][n - 1] > sum[0][n - 1] / 2;
    }
}
