// 76. Longest Increasing Subsequence
// 中文English
// Given a sequence of integers, find the longest increasing subsequence (LIS).
//
// You code should return the length of the LIS.
//
// Example
// Example 1:
// 	Input:  [5,4,1,2,3]
// 	Output:  3
//
// 	Explanation:
// 	LIS is [1,2,3]
//
//
// Example 2:
// 	Input: [4,2,4,5,3,7]
// 	Output:  4
//
// 	Explanation:
// 	LIS is [2,4,5,7]
// Challenge
// Time complexity O(n^2) or O(nlogn)
//
// Clarification
// What's the definition of longest increasing subsequence?
//
// The longest increasing subsequence problem is to find a subsequence of a given sequence in which the subsequence's elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible. This subsequence is not necessarily contiguous, or unique.
//
// https://en.wikipedia.org/wiki/Longest_increasing_subsequence
//


public class Solution {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // definition
        int[] f = new int[nums.length];
        // initialization
        for (int i = 0; i < nums.length; i++) {
            f[i] = 1;
        }
        // DP
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        // answer
        int result = f[0];
        for (int i = 1; i < nums.length; i++) {
            result = Math.max(result, f[i]);
        }
        return result;
    }
}
