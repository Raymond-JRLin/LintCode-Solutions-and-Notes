// 394. Coins in a Line
// Description
// There are n coins in a line. Two players take turns to take one or two coins from right side until there are no more coins left. The player who take the last coin wins.
//
// Could you please decide the first play will win or lose?
//
// Have you met this question in a real interview?
// Example
// n = 1, return true.
//
// n = 2, return true.
//
// n = 3, return false.
//
// n = 4, return true.
//
// n = 5, return true.
//
// Challenge
// O(n) time and O(1) memory


public class Solution {
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        // write your code here
        // edge cases
        // if (n == 0) {
        //     return false;
        // }
        // if (n == 1 || n == 2) {
        //     return true;
        // }
        // // definition
        // // 表示第n枚硬币是赢还是输
        // boolean[] f = new boolean[n + 1];
        // // initialization
        // f[0] = false;
        // f[1] = true;
        // f[2] = true;
        // // DP
        // for (int i = 3; i < n + 1; i++) {
        //     // 取决于前两块硬币的状态，前两块中有一块是输，那这块就是赢，前两块都是赢（表示我都拿走了），那这块就是输
        //     f[i] = !f[i - 1] || !f[i - 2];
        //     // 取非相当于翻转结果，即取一块变成取两块，所以总有策略使前两块当中输的那块变成赢，而赢的变成输
        // }
        // // result
        // return f[n];
        return n % 3 != 0;
    }
}
