// 中文English
// Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
//
// Example
// Example 1:
// 	Input:  [3,4,8,5], backpack size=10
// 	Output:  9
//
// Example 2:
// 	Input:  [2,3,5,7], backpack size=12
// 	Output:  12
//
// Challenge
// O(n x m) time and O(m) memory.
//
// O(n x m) memory is also acceptable if you do not know how to optimize memory.
//
// Notice
// You can not divide any item into small pieces.
//


public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        if (m <= 0 || A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        // 背包问题： 经典DP

        // method 1: DP with 2D arrays
        // definition: i-th 的 item 放入 j 大小背包的最大 size
        // int[][] f = new int[n + 1][m + 1];
        // // initialization
        // // DP
        // for (int i = 1; i < n + 1; i++) {
        //     for (int j = 0; j <= m; j++) {
        //         if (j >= A[i - 1]) {
        //             f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - A[i -1]] + A[i - 1]);
        //         } else {
        //             // A[i - 1] 放不进 j 大小
        //             f[i][j] = f[i - 1][j];
        //         }
        //     }
        // }
        // // result
        // return f[n][m];

        // method 2: 发现 f[i] 只依赖于 f[i - 1] 的值， 所以可以降维
        // 降维的关键是如何来表达这层依赖关系， 并能够拿到相对应的值
        // ref: https://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/73067
        // definition: f[i] means how full a backpack with size of i can be filled
        int[] f = new int[m + 1];
        // initialization
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = m; j >= 0; j--) {
                // 因为 f[i] 要用的是 f[i - 1] 的值， 所以要在内层循环中递减， 如此即可避免result[i][S]的值由本轮result[i][S-A[i]]递推得到， 也因此隐含了这层依赖关系， 达到降维目的
                if (j >= A[i - 1]) {
                    f[j] = Math.max(f[j], f[j - A[i - 1]] + A[i - 1]);
                }
            }
        }
        return f[m];
    }
}
