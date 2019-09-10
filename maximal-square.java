// 436. Maximal Square
// 中文English
// Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
//
// Example
// Example 1:
//
// Input:
// [
//   [1, 0, 1, 0, 0],
//   [1, 0, 1, 1, 1],
//   [1, 1, 1, 1, 1],
//   [1, 0, 0, 1, 0]
// ]
// Output: 4
// Example 2:
//
// Input:
// [
//   [0, 0, 0],
//   [1, 1, 1]
// ]
// Output: 1


public class Solution {
    /*
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int max = 0;
        // use DP
        // definition:
        int[][] f = new int[n][m];
        // initialization
        for (int i = 0; i < n; i++) {
            f[i][0] = matrix[i][0];
            max = Math.max(max, f[i][0]);
        }
        for (int i = 0; i < m; i++) {
            f[0][i] = matrix[0][i];
            max = Math.max(max, f[0][i]);
        }
        // DP
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;
                }
                max = Math.max(max, f[i][j]);
            }
        }
        // result
        return max * max;
    }
}
