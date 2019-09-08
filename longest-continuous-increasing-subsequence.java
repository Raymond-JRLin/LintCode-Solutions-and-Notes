// 397. Longest Continuous Increasing Subsequence
// 中文English
// Give an integer array，find the longest increasing continuous subsequence in this array.
//
// An increasing continuous subsequence:
//
// Can be from right to left or from left to right.
// Indices of the integers in the subsequence should be continuous.
// Example
// Example 1:
//
// Input: [5, 4, 2, 1, 3]
// Output: 4
// Explanation:
// For [5, 4, 2, 1, 3], the LICS  is [5, 4, 2, 1], return 4.
// Example 2:
//
// Input: [5, 1, 2, 3, 4]
// Output: 4
// Explanation:
// For [5, 1, 2, 3, 4], the LICS  is [1, 2, 3, 4], return 4.
// Challenge
// O(n) time and O(1) extra space.
//


public class Solution {
    /*
     * @param : An array of Integer
     * @return: an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int result = 1;
        // method 1: DP based on array; O(n) time and O(n) space
        // // definition
        // int[] left = new int[n];
        // int[] right = new int[n];
        // // initialization
        // left[0] = 1;
        // right[n - 1] = 1;
        // // DP
        // for (int i = 1; i < n; i++) {
        //     if (A[i] > A[i - 1]) {
        //         left[i] = left[i - 1] + 1;
        //     } else {
        //         left[i] = 1;
        //     }
        // }
        // for (int i = n - 2; i >= 0; i--) {
        //     if (A[i] > A[i + 1]) {
        //         right[i] = right[i + 1] + 1;
        //     } else {
        //         right[i] = 1;
        //     }
        // }
        // // result
        // for (int i = 0; i < n; i++) {
        //     result = Math.max(result, Math.max(left[i], right[i]));
        // }
        // return result;

        // method 2: if we wanna do O(n) time and O(1) space, we should use variables to replace arrays to reduce dimension
        // definition and initialization
        int left = 1;
        int right = 1;
        int max = 1;
        // DP
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                left++;
                max = Math.max(max, left);
            } else {
                left = 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                right++;
                max = Math.max(max, right);
            } else {
                right = 1;
            }
        }
        return max;
    }
};
