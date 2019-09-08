// 29. Interleaving String
// 中文English
// Given three strings: s1, s2, s3, determine whether s3 is formed by the interleaving of s1 and s2.
//
// Example
// Example 1:
//
// Input:
// "aabcc"
// "dbbca"
// "aadbbcbcac"
// Output:
// true
// Example 2:
//
// Input:
// ""
// ""
// "1"
// Output:
// false
// Example 3:
//
// Input:
// "aabcc"
// "dbbca"
// "aadbbbaccc"
// Output:
// false
// Challenge
// O(n2) time or better
//


public class Solution {
    /**
     * Determine whether s3 is formed by interleaving of s1 and s2.
     * @param s1, s2, s3: As description.
     * @return: true or false.
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
        // ref: http://www.cnblogs.com/grandyang/p/4298664.html
        // ref: http://fisherlei.blogspot.com/2012/12/leetcode-interleaving-string.html
        if (s1 == null || s1.length() == 0) {
            return s2.equals(s3);
        }
        if (s2 == null || s2.length() == 0) {
            return s1.equals(s3);
        }
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }
        // 只要是遇到字符串的子序列或是匹配问题直接就上Dynamic Programming
        // definition: f[i][j] means whether we can match s3 with length of i + j to s1 with length of i and s2 with length of j
        boolean[][] f = new boolean[len1 + 1][len2 + 1];
        // initialization
        f[0][0] = true;
        // 注意当 1 或 2 为空串的时候， 需要 3 与另外一个匹配
        for (int i = 1; i < len1 + 1; i++) {
            f[i][0] = (s1.charAt(i - 1) == s3.charAt(i - 1) && f[i - 1][0]);
        }
        for (int i = 1; i < len2 + 1; i++) {
            f[0][i] = (s2.charAt(i - 1) == s3.charAt(i - 1) && f[0][i - 1]);
        }
        // DP
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                f[i][j] = (s1.charAt(i - 1) == s3.charAt(i - 1 + j) && f[i - 1][j])
                    || (s2.charAt(j - 1) == s3.charAt(j - 1 + i) && f[i][j - 1]);
            }
        }
        // result
        return f[len1][len2];
    }
}
