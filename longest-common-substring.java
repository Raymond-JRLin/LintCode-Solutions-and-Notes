// 79. Longest Common Substring
// 中文English
// Given two strings, find the longest common substring.
//
// Return the length of it.
//
// Example
// Example 1:
// 	Input:  "ABCD" and "CBCE"
// 	Output:  2
//
// 	Explanation:
// 	Longest common substring is "BC"
//
//
// Example 2:
// 	Input: "ABCD" and "EACB"
// 	Output:  1
//
// 	Explanation:
// 	Longest common substring is 'A' or 'C' or 'B'
// Challenge
// O(n x m) time and memory.
//
// Notice
// The characters in substring should occur continuously in original string. This is different with subsequence.
//


public class Solution {
    /*
     * @param A: A string
     * @param B: A string
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        if ((A == null || A.length() == 0) && (B == null || B.length() == 0)) {
            return 0;
        }
        int a_len = A.length();
        int b_len = B.length();
        // my try: we can should DP, difference is LCSubstring should have continuous sequence
        // definition: f[i][j] means the length of LCSustring with String A [0, i] and String B [0, j]
        int[][] f = new int[a_len + 1][b_len + 1];
        // initialization
        f[0][0] = 0;
        for (int i = 1; i < a_len + 1; i++) {
            f[i][0] = 0;
        }
        for (int i = 1; i < b_len + 1; i++) {
            f[0][i] = 0;
        }
        // DP
        int max = 0; // 随着过程更新最大值， 当然也可以做完了再在 f[][] 里面找最大
        for (int i = 1; i < a_len + 1; i++) {
            char a = A.charAt(i - 1);
            for (int j = 1; j < b_len + 1; j++) {
                char b = B.charAt(j - 1);
                if (a == b) {
                    // 只和 f[i - 1][j - 1] 有关， 即同时去掉这个字符， 和上面、 左边都没关系， 因为必须是连续的
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    // substring 必须是连续的， 如果不等， 就重新开始
                    f[i][j] = 0;
                }
                max = Math.max(max, f[i][j]);
            }
        }
        // result: 注意不是 f[a_len][b_len]， 而是 f[][] 里所有中最大的那个， 因为连续子串， 不一定最后一位是最大的
        return max;
    }
}
