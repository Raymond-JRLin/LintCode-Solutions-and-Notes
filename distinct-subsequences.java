// 118. Distinct Subsequences
// 中文English
// Given two strings S and T. Count the number of distinct subsequences of S which equals T.
//
// A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not)
//
// Example
// Example 1:
//
// Input: S = "rabbbit", T = "rabbit"
// Output: 3
// Explanation: You could remove any 'b' in S, so there are 3 ways to get T.
// Example 2:
//
// Input: S = "abcd", T = ""
// Output: 1
// Explanation: There is only 1 way to get T - remove all chars in S.
// Challenge
// Do it in O(n^2n
// ​2
// ​​ ) time and O(n) memory.
//
// O(n^2n
// ​2
// ​​ ) memory is also acceptable if you do not know how to optimize memory.
//


public class Solution {
    /**
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        // write your code here
        if (S == null || S.length() == 0) {
            return 0;
        }
        if (T == null || T.length() == 0) {
            return 1;
        }
        int s_len = S.length();
        int t_len = T.length();

        // 两个 string 有 substring 求多少种， 应该要想到用 DP
        // 以S ="rabbbit",T = "rabbit"为例:
        //  "" r a b b b i t
    //    "" 1 1 1 1 1 1 1 1
        // r 0 1 1 1 1 1 1 1
        // a 0 0 1 1 1 1 1 1
        // b 0 0 0 1 2 3 3 3
        // b 0 0 0 0 1 3 3 3
        // i 0 0 0 0 0 0 3 3
        // t 0 0 0 0 0 0 0 3
        // method 1: DP with 2D array
        // definition
        // there is null in the beginning
        // f[i][j]表示: S[i] 字符串能够被 T[j] 匹配的个数
        // int[][] f = new int[s_len + 1][t_len + 1];
        // // initialization
        // for (int i = 0; i < s_len + 1; i++) {
        //     f[i][0] = 1; // T 是空串， 至少能够匹配这个空串
        // }
        // for (int i = 1; i < t_len + 1; i++) {
        //     f[0][i] = 0; // S 是空串， 则没有一个可以匹配
        // }
        // // DP
        // for (int i = 1; i < s_len + 1; i++) {
        //     for (int j = 1; j < t_len + 1; j++) {
        //         if (S.charAt(i - 1) == T.charAt(j - 1)) {
        //             // 当前位置的 char 相等的话， 可以让这两个匹配， 从而找 S[i - 1] 与 T[j - 1] 的匹配个数
        //             f[i][j] = f[i - 1][j] + f[i - 1][j - 1];
        //         } else {
        //             f[i][j] = f[i - 1][j]; // 至少 S[i - 1] 能够匹配当前的 T
        //         }
        //     }
        // }
        // // 或者将 i/j (S/T) 的位置调换过来求
        // // result
        // return f[s_len][t_len];

        // method 2: 可以发现 f[i][j] 只和 f[i - 1][j] 与 f[i - 1][j - 1] 有关， 那么就可以使用滚动数组或者参数降维
        // ref: http://bangbingsyb.blogspot.com/2014/11/leetcode-distinct-subsequences.html
        // 好难想明白哇 =。=
        // definition
        int[] f = new int[s_len + 1];
        // initialization
        for (int j = 0; j < s_len + 1; j++) {
            f[j] = 1;
        }
        // DP
        for (int i = 1; i < t_len + 1; i++) {
            int last = f[0];
            f[0] = 0;
            for (int j = 1; j < s_len + 1; j++) {
                int temp = f[j];
                f[j] = f[j - 1];
                if (S.charAt(j - 1) == T.charAt(i - 1)) {
                    f[j] += last;
                }
                last = temp;
            }
        }
        return f[s_len];
    }
}
