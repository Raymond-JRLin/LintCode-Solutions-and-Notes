// 108. Palindrome Partitioning II
// 中文English
// Given a string s, cut s into some substrings such that every substring is a palindrome.
//
// Return the minimum cuts needed for a palindrome partitioning of s.
//
// Example
// Example 1:
//
// Input: "a"
// Output: 0
// Explanation: "a" is already a palindrome, no need to split.
// Example 2:
//
// Input: "aab"
// Output: 1
// Explanation: Split "aab" once, into "aa" and "b", both palindrome.


public class Solution {
    /**
     * @param s a string
     * @return an integer
     */
    public int minCut(String s) {
        // write your code here
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int n = s.length();

        // method 1
        // definition
        boolean[][] f = new boolean[n][n]; //
        int[] min = new int[n + 1]; //
        // initialization
        for (int i = 0; i < n + 1; i++) {
            min[i] = i - 1;// at least it can cut every single char
        }
        // DP - 1 for f[][]
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    f[i][j] = true;
                } else if (j == i + 1) {
                    f[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
                }
            }
        }
        // DP - 2 for min[]
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j][i - 1]) {
                    min[i] = Math.min(min[i], min[j] + 1);
                }
            }
        }
        // result
        return min[n];
    }
};
