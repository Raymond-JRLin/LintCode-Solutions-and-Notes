// 125. Backpack II
// 中文English
// There are n items and a backpack with size m. Given array A representing the size of each item and array V representing the value of each item.
//
// What's the maximum value can you put into the backpack?
//
// Example
// Example 1:
//
// Input: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
// Output: 9
// Explanation: Put A[1] and A[3] into backpack, getting the maximum value V[1] + V[3] = 9
// Example 2:
//
// Input: m = 10, A = [2, 3, 8], V = [2, 5, 8]
// Output: 10
// Explanation: Put A[0] and A[2] into backpack, getting the maximum value V[0] + V[2] = 10
// Challenge
// O(nm) memory is acceptable, can you do it in O(m) memory?
//
// Notice
// A[i], V[i], n, m are all integers.
// You can not split an item.
// The sum size of the items you want to put into backpack can not exceed m.
// Each item can only be picked up once


public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        // 感觉和 I 没什么区别， 把 size 换成 value 就好了
        if (m <= 0 || A == null || A.length == 0) {
            return 0;
        }

        // return method1(m, A, V);

        return method2(m, A, V);
    }

    private int method2(int m, int[] A, int V[]) {
        // method 2: DP with 1D array - decrease demension
        int n = A.length;
        // definition
        int[] f = new int[m + 1];
        // initialization
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = m; j >= 0; j--) {
                if (j >= A[i - 1]) {
                    f[j] = Math.max(f[j], f[j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        // result
        return f[m];
    }

    private int method1(int m, int[] A, int V[]) {
        // method 1: DP with 2D arrays
        int n = A.length;
        // definition: f[i][j]: the max value i-th item can get with backpack with size of j
        int[][] f = new int[n + 1][m + 1];
        // initialization
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (j >= A[i - 1]) {
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - A[i - 1]] + V[i - 1]);
                } else {
                    f[i][j] = f[i - 1][j];
                }
            }
        }
        // result
        return f[n][m];
    }
}
