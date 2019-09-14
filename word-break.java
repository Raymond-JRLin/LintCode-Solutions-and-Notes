// 107. Word Break
// 中文English
// Given a string s and a dictionary of words dict, determine if s can be break into a space-separated sequence of one or more dictionary words.
//
// Example
// Example 1:
// 	Input:  "lintcode", ["lint", "code"]
// 	Output:  true
//
// Example 2:
// 	Input: "a", ["a"]
// 	Output:  true


public class Solution {
    /**
     * @param s: A string s
     * @param dict: A dictionary of words dict
     */
    public boolean wordBreak(String s, Set<String> dict) {
        // write your code here
        if (s == null || s.length() == 0) {
            return true;
        }
        if (dict == null || dict.size() == 0) {
            return false;
        }
        int n = s.length();
        // we can use DP to solve this "existence" problem
        int maxLen = getMaxLength(dict);
        // definition
        boolean[] f = new boolean[n + 1]; //
        // initialization
        f[0] = true;
        // DP
        // state transfer function: f[i] = f[j] && dict.contains(s.substring(j, i))
        // for (int i = 0; i < n + 1; i++) {
        //     for (int j = 0; j < i; j++) {
        //         f[i] = f[j] && dict.contains(s.substring(j, i));
        //         if (f[i]) {
        //             // if we found there is one possible way, then we don't need to go further, otherwise if would assign false to current f[]
        //             break;
        //         }
        //     }
        // }
        // above DP has a TLE problem, because everytime we would start from the 1st position, but actually, we can optimize by only searching a range, apart from current i, of length of words with max length in given dict
        for (int i = 0; i < n + 1; i++) {
            for (int j = i - maxLen; j < i; j++) {
                if (j < 0) {
                    continue;
                }
                f[i] = f[j] && dict.contains(s.substring(j, i));
                if (f[i]) {
                    // if we found there is one possible way, then we don't need to go further, otherwise if would assign false to current f[]
                    break;
                }
            }
        }
        // Attention: if we do above, it would cause ArrayIndexOutOfBoundsException, because i - maxLen may exceed length of string, so we should do bottom-up
        // result
        return f[n];
    }
    private int getMaxLength(Set<String> dict) {
        int max = 0;
        for (String str : dict) {
            if (str.length() > max) {
                max = str.length();
            }
        }
        return max;
    }
}
