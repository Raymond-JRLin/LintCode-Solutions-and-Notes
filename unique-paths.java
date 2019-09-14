// 114. Unique Paths
// 中文English
// A robot is located at the top-left corner of a m x n grid.
//
// The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.
//
// How many possible unique paths are there?
//
// Example
// Example 1:
//
// Input: n = 1, m = 3
// Output: 1
// Explanation: Only one path to target position.
// Example 2:
//
// Input:  n = 3, m = 3
// Output: 6
// Explanation:
// 	D : Down
// 	R : Right
// 	1) DDRR
// 	2) DRDR
// 	3) DRRD
// 	4) RRDD
// 	5) RDRD
// 	6) RDDR
// Notice
// m and n will be at most 100.


public class Solution {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        if (m == 0 || n == 0) {
            return -1;
        }
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] f = new int[m][n];
        f[0][0] = 1;
        for (int i = 0; i < m; i++) {
            f[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            f[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }
}
