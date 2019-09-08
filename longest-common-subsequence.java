// 77. Longest Common Subsequence
// 中文English
// Given two strings, find the longest common subsequence (LCS).
//
// Your code should return the length of LCS.
//
// Example
// Example 1:
// 	Input:  "ABCD" and "EDCA"
// 	Output:  1
//
// 	Explanation:
// 	LCS is 'A' or  'D' or 'C'
//
//
// Example 2:
// 	Input: "ABCD" and "EACB"
// 	Output:  2
//
// 	Explanation:
// 	LCS is "AC"
// Clarification
// What's the definition of Longest Common Subsequence?
//
// https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
// http://baike.baidu.com/view/2020307.htm


public class Solution {
    /*
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        if ((A == null || A.length() == 0) && (B == null || B.length() == 0)) {
            return 0;
        }
        int a_len = A.length();
        int b_len = B.length();
        // my try: we can should DP
        // definition: f[i][j] means the length of LCS with String A [0, i] and String B [0, j]
        int[][] f = new int[a_len + 1][b_len + 1];
        // initialization
        f[0][0] = 0;
        for (int i = 1; i < a_len + 1; i++) {
            f[i][0] = 0;
        }
        for (int i = 1; i < a_len + 1; i++) {
            f[0][i] = 0;
        }
        // DP
        for (int i = 1; i < a_len + 1; i++) {
            char a = A.charAt(i - 1);
            for (int j = 1; j < b_len + 1; j++) {
                char b = B.charAt(j - 1);
                if (a == b) {
                    // 相等就要同时在两个 string 中去掉这个字符， 比较之前的
                    // 应该从左上方来
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    // 可以从上边或左边来， 拿之前的结果中的大的
                    f[i][j] = Math.max(f[i][j - 1], f[i - 1][j]);
                }
            }
        }
        // result
        return f[a_len][b_len];
    }
}
