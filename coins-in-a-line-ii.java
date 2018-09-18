// 395. Coins in a Line II
// Description
// There are n coins with different value in a line. Two players take turns to take one or two coins from left side until there are no more coins left. The player who take the coins with the most value wins.
//
// Could you please decide the first player will win or lose?
//
// Have you met this question in a real interview?
// Example
// Given values array A = [1,2,2], return true.
//
// Given A = [1,2,4], return false.


public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        // edge cases
        if (values == null) {
            return false;
        }
        int n = values.length;
        if (values.length <= 2) {
            return true;
        }
        // definition: f[i] means the max value 1st player can pick from i to end
        int[] f = new int[n + 1];
        // initialization: from end to front
        f[n] = 0;
        f[n - 1] = values[n - 1];
        f[n - 2] = values[n - 2] + values[n - 1];
        // if (n == 3) {
        //     return values[0] + values[1] > values[2];
        // }
        f[n - 3] = values[n - 3] + values[n - 2];
        // DP
        for (int i = n - 4; i >= 0; i--) {
            // case 1: 1st pick i, and 2nd pick i + 1 or i+ 1 & i + 2, thus 1st have choices of i + 2 or i + 3
            f[i] = values[i] + Math.min(f[i + 2], f[i + 3]);
            // case 2: 1st pick i & i + 1, 2nd pick i + 2 or i + 2 & i + 3, thus 1st have choices of i + 3 or i + 4
            // we pick the max from case 1 and 2
            f[i] = Math.max(f[i], values[i] + values[i + 1] + Math.min(f[i + 3], f[i + 4]));
        }
        // calculate total sum of coins
        int sum = 0;
        for (int val : values) {
            sum += val;
        }
        // calculate 2nd's total sum
        int second = sum - f[0];
        // result
        return f[0] > second;
    }
}
