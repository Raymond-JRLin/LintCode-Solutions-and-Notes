// 109. Triangle
// Description
// Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
//
// Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
//
// Have you met this question in a real interview?
// Example
// Given the following triangle:
//
// [
//      [2],
//     [3,4],
//    [6,5,7],
//   [4,1,8,3]
// ]
// The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).


public class Solution {
    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
        if (triangle == null || triangle.length == 0 || triangle[0].length == 0) {
            return -1;
        }

        // return method1(trangle);

        // return method2(triangle);

        // return method3(triangle);

        return method4(triangle);
    }

    private int method4(int[][] triangle) {
        // bottom-up 1D DP
        int n = triangle.length;
        // definition:
        int[] f = new int[n];
        // initialization
        for (int i = 0; i < n; i++) {
            f[i] = triangle[n - 1][i];
        }
        // DP
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                f[j] = Math.min(f[j], f[j + 1]) + triangle[i][j];
            }
        }
        // result
        return f[0];
    }

    private int method3(int[][] triangle) {
        // bottom-up DP
        int n = triangle.length;
        // definition: f[i][j] = min path sum reaching triangle[i][j]
        int[][] f = new int[n][n];
        // initialization
        for (int i = 0; i < n; i++) {
            f[n - 1][i] = triangle[n - 1][i];
        }
        // DP
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + triangle[i][j];
            }
        }
        // result
        return f[0][0];
    }

    private int method2(int[][] triangle) {
        // recursion
        int n = triangle.length;
        int[][] f = new int[n][n];

        for (int[] row : f) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        return recursion(triangle, f, 0, 0);
    }
    private int recursion(int[][] triangle, int[][] f, int i, int j) {
        if (i >= triangle.length) {
            return 0;
        }
        if (f[i][j] != Integer.MAX_VALUE) {
            return f[i][j];
        }
        return f[i][j] = Math.min(recursion(triangle, f, i + 1, j), recursion(triangle, f, i + 1, j + 1)) + triangle[i][j];
    }

    private int method1(int[][] triangle) {
        // top-down DP
        int len = triangle.length;
        // definition: f[i][j] = min path sum reaching triangle[i][j]
        int[][] f = new int[len][len];
        // initialization
        f[0][0] = triangle[0][0];
        for (int i = 1; i < len; i++) {
            f[i][0] = f[i - 1][0] + triangle[i][0];
            f[i][i] = f[i - 1][i - 1] + triangle[i][i];
        }
        // DP
        for (int i = 1; i < len; i++) {
            for (int j = 1; j < i; j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i - 1][j - 1]) + triangle[i][j];
            }
        }
        // result
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < len; j++) {
            result = Math.min(result, f[len - 1][j]);
        }
        return result;
    }
}
