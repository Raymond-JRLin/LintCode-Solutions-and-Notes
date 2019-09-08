// 584. Drop Eggs II
// 中文English
// There is a building of n floors. If an egg drops from the k th floor or above, it will break. If it's dropped from any floor below, it will not break.
//
// You're given m eggs, Find k while minimize the number of drops for the worst case. Return the number of drops in the worst case.
//
// Example
// Example 1:
//
// Input: m = 2, n = 100
// Output: 14
// Example 2:
//
// Input: m = 2, n = 36
// Output: 8


public class Solution {
    /**
     * @param m the number of eggs
     * @param n the umber of floors
     * @return the number of drops in the worst case
     */
    public int dropEggs2(int m, int n) {
        // Write your code here
        // definition:#drops in the worst case for m eggs and n floors
        int[][] f = new int[m + 1][n + 1];
        // initialization
        // 对于 0 或 1 层楼，不管多少颗鸡蛋，都是 0 或 1
        for (int i = 0; i <= m; i++) {
            f[i][0] = 0;
            f[i][1] = 1;
        }
        // 对于 1 颗鸡蛋，有几层楼就要扔几次
        for (int j = 1; j <= n; j++) {
            f[1][j] = j;
        }
        // DP
        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                f[i][j] = Integer.MAX_VALUE; // initialize to max first
                for (int k = 1; k <= j; k++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[i][j - k], f[i - 1][k - 1]) + 1);
                    // case 1: 鸡蛋没破，还有i个鸡蛋，往上查找 j - k 层
                    // case 2: 鸡蛋破了，还有 i - 1个鸡蛋，往下剩 k - 1 层
                    // 取最坏的情况，再加 1 步，再在最坏的情况中取最优
                }
            }
        }
        // result
        return f[m][n];
    }
}
