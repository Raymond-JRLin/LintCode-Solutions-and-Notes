// 119. Edit Distance
// 中文English
// Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
//
// You have the following 3 operations permitted on a word:
//
// Insert a character
// Delete a character
// Replace a character
// Example
// Example 1:
//
// Input:
// "horse"
// "ros"
// Output: 3
// Explanation:
// horse -> rorse (replace 'h' with 'r')
// rorse -> rose (remove 'r')
// rose -> ros (remove 'e')
// Example 2:
//
// Input:
// "intention"
// "execution"
// Output: 5
// Explanation:
// intention -> inention (remove 't')
// inention -> enention (replace 'i' with 'e')
// enention -> exention (replace 'n' with 'x')
// exention -> exection (replace 'n' with 'c')
// exection -> execution (insert 'u')


public class Solution {
    /**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        // write your code here
        if (word1 == null || word2 == null) {
            return 0;
        }
        if (word1.equals(word2)) {
            return 0;
        }
        // 经典 DP 问题 - 其实我也是看了才知道=。=
        int len1 = word1.length();
        int len2 = word2.length();
        // definition: 我们要设 f[0][0] = 0 来使得没有字符串的时候是 0， 而 f[1][1] 则表示有 1 个字符， 所以 f[i][j] 表示的是 word1 的长度为 i 的转换成 word2 的长度为 j 的最小编辑距离， 对应到 index 上应该是 i - 1, j - 1
        // so f[i][j] definition is the distance conversed from word1(1 ~ ith) to word2(1 ~ jth)
        int[][] f = new int[len1 + 1][len2 + 1];
        // initialization: if one of them is null, then we need "length" steps
        for (int i = 0; i < len1 + 1; i++) {
            f[i][0] = i;
        }
        for (int j = 0; j < len2 + 1; j++) {
            f[0][j] = j;
        }
        // DP: 初始化第一行和第一列， 然后从左上到右下进行DP
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                int insertion = f[i][j - 1] + 1;
                int deletion = f[i - 1][j] + 1;
                // for replacement, we need to check whether they have same char
                int replacement;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // be careful of "length" and "index"
                    replacement = f[i - 1][j - 1];
                } else {
                    replacement = f[i - 1][j - 1] + 1;
                }
                // final result is min of above 3
                // of course we can combine then together, but here we use other 3 variables to do a more explicit process
                f[i][j] = Math.min(Math.min(insertion, deletion), replacement);
            }
        }
        // result
        return f[len1][len2];

        // 也可以降维到一个一维数组和一个变量， 但是不理解LOL
    }
}
