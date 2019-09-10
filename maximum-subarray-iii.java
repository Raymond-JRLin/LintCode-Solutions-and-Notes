// 43. Maximum Subarray III
// 中文English
// Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.
//
// The number in each subarray should be contiguous.
//
// Return the largest sum.
//
// Example
// Example 1
//
// Input:
// List = [1,2,3]
// k = 1
// Output: 6
// Explanation: 1 + 2 + 3 = 6
// Example 2
//
// Input:
// List = [-1,4,-2,3,-2,3]
// k = 2
// Output: 8
// Explanation: 4 + (3 + -2 + 3) = 8
// Notice
// The subarray should contain at least one number
//


public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length < k) {
            return 0;
        }
        int n = nums.length;
        // use 2D array DP
        // definition
        int[][] local = new int[n + 1][k + 1];
        int[][] global = new int[n + 1][k + 1];
        // initialization
        // DP
        // because i should equal or larger than j at least, then we can split into j subarrays
        for (int j = 1; j <= k; j++) {
            local[j - 1][j] = Integer.MIN_VALUE;
            for (int i = j; i <= n; i++) {
                local[i][j] = Math.max(local[i - 1][j], global[i - 1][j - 1]) + nums[i - 1];
                if (i == j) {
                    global[i][j] = local[i][j];
                } else {
                    global[i][j] = Math.max(global[i - 1][j], local[i][j]);
                }
            }
        }
        // result
        return global[n][k];
    }
}
