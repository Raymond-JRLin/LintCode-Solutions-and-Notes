// 272. Climbing Stairs II
// 中文English
// A child is running up a staircase with n steps, and can hop either 1 step, 2 steps, or 3 steps at a time. Implement a method to count how many possible ways the child can run up the stairs.
//
// Example
// Example 1:
//
// Input: 3
// Output: 4
// Explanation: 1 + 1 + 1 = 2 + 1 = 1 + 2 = 3 = 3 , there are 4 ways.
// Example 2:
//
// Input: 4
// Output: 7
// Explanation: 1 + 1 + 1 + 1 = 1 + 1 + 2 = 1 + 2 + 1 = 2 + 1 + 1 = 2 + 2 = 1 + 3 = 3 + 1 = 4 , there are 7 ways.
// Clarification
// For n=0, we think the answer is 1.
//


public class Solution {
    /**
     * @param n an integer
     * @return an integer
     */
    public int climbStairs2(int n) {
        // Write your code here
        if (n == 0 || n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        // definition
        int[] f = new int[n + 1];
        // initialization
        f[0] = 1;
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            f[i] = f[i - 1] + f[i - 2] + f[i - 3];
        }
        return f[n];
    }
}
