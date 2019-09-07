// 666. Guess Number Higher or Lower II
// 中文English
// We are playing the Guess Game. The game is as follows:
// I pick a number from 1 to n. You have to guess which number I picked.
// Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
// However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
// Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
//
// Example
// Example1
//
// Input: 10
// Output: 16
// Explanation:
// Given n = 10, I pick 2.
// First round:  You guess 7, I tell you that it's lower. You pay $7.
// Second round: You guess 3, I tell you that it's lower. You pay $3.
// Third round:  You guess 1, I tell you that it's higher. You pay $1.
// Game over. 1 is the number I picked.
// You end up paying $7 + $3 + $1 = $11.
//
// Given n = 10, I pick 4.
// First round:  You guess 7, I tell you that it's lower. You pay $7.
// Second round: You guess 3, I tell you that it's higher. You pay $3.
// Third round:  You guess 5, I tell you that it's lower. You pay $5.
// Game over. 4 is the number I picked.
// You end up paying $7 + $3 + $5 = $15.
//
// Given n = 10, I pick 8.
// First round:  You guess 7, I tell you that it's higher. You pay $7.
// Second round: You guess 9, I tell you that it's lower. You pay $9.
// Game over. 8 is the number I picked.
// You end up paying $7 + $7 + $9 = $16.
//
// So given n = 10, the answer is 16.
// Example2
//
// Input: 5
// Output: 6


public class Solution {
    /**
     * @param n an integer
     * @return how much money you need to have to guarantee a win
     */
    public int getMoneyAmount(int n) {
        // Write your code here

        if (n <= 0) {
            return 0;
        }
        int[][] f = new int[n + 1][n + 1];

        // method 1: recursion + memorizing search
        // dfs(f, 1, n);
        // return f[1][n];

        // method 2: DP / iteration
        dp(f, 1, n);
        return f[1][n];
    }
    private int dfs(int[][] f, int left, int right) {
        if (left >= right) {
            return 0;
        }
        if (f[left][right] != 0) {
            return f[left][right];
        }
        f[left][right] = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            f[left][right] = Math.min(f[left][right],
                i + Math.max(dfs(f, left, i - 1), dfs(f, i + 1, right)));
        }
        return f[left][right];
    }
    private void dp(int[][] f, int start, int end) {
        for (int left = end - 1; left >= start; left--) {
            for (int right = left + 1; right <= end; right++) {
                f[left][right] = Integer.MAX_VALUE;
                for (int i = left; i < right; i++) {
                    f[left][right] = Math.min(f[left][right],
                        i + Math.max(f[left][i - 1], f[i + 1][right]));
                }
            }
        }
    }
}
