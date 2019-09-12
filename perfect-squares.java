// 513. Perfect Squares
// 中文English
// Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
//
// Example
// Example 1:
//
// Input: 12
// Output: 3
// Explanation: 4 + 4 + 4
// Example 2:
//
// Input: 13
// Output: 2
// Explanation: 4 + 9


public class Solution {
    /**
     * @param n a positive integer
     * @return an integer
     */
    public int numSquares(int n) {
        // Write your code here
        if (n <= 0) {
            return 0;
        }
        // definition
        int[] f = new int[n + 1];
        // initialization
        f[0] = 0;
        for (int i = 0; i <= n; i++) {
            f[i] = i;
            // DP
            for (int j = 0; j * j <= i; j++) {
                f[i] = Math.min(f[i], f[i - j * j] + 1);
            }
        }
        // answer
        return f[n];
    }
}
