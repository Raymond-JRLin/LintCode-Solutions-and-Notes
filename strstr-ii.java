// 594. strStr II
// 中文English
// Implement strStr function in O(n + m) time.
//
// strStr return the first index of the target string in a source string. The length of the target string is m and the length of the source string is n.
// If target does not exist in source, just return -1.
//
// Example
// Example 1:
//
// Input：source = "abcdef"， target = "bcd"
// Output：1
// Explanation：
// The position of the first occurrence of a string is 1.
// Example 2:
//
// Input：source = "abcde"， target = "e"
// Output：4
// Explanation：
// The position of the first occurrence of a string is 4.


public class Solution {
    /**
     * @param source a source string
     * @param target a target string
     * @return an integer as index
     */
    public int strStr2(String source, String target) {
        // Write your code here
        if (source == null || target == null) {
            return -1;
        }
        int s = source.length();
        int t = target.length();
        if (t == 0 && source != null) {
            return 0;
        }
        if (s == 0) {
            return -1;
        }
        // use hashcode to sovle this problem in O(n+m), we need to get the hash code of target first, then calculate hash code of every substring in source with same length of target, if their hash codes are the same, we then compare their char one by one
        int mod = Integer.MAX_VALUE / 33;
        // 1: calculate target string hash code
        int target_hash = 0;
        for (int i = 0; i < t; i++) {
            target_hash = (target_hash * 33 + target.charAt(i) - 'a') % mod;
            if (target_hash < 0) {
                target_hash += mod;
            }
        }
        // because in length: s > t, so when we do next substring in source, we need to minus the previous deleted char
        // 2: set a previous factor to do this minus conveniently
        int pre = 1;
        for (int i = 0; i < t - 1; i++) {
            pre = pre * 33 % mod;
        }
        // 3: calculate every substring's hash code in source
        int result_hash = 0;
        for (int i = 0; i < s; i++) {
            // 如果超过target长度了，需要减去第一位
            if (i >= t) {
                result_hash = result_hash - (source.charAt(i - t) - 'a') * pre % mod;
            }
            // 再求hash code
            result_hash = (result_hash * 33 + source.charAt(i) - 'a') % mod;
            if (result_hash < 0) {
                result_hash += mod;
            }
            // 如果有相等的hash code，比较两个是否完全相等
            if (i >= t - 1 && result_hash == target_hash) {
                // subString的长度要注意从何开始，结束位置要包含进去
                if (target.equals(source.substring(i - t + 1, i + 1))) {
                    return i - t + 1;
                }
            }
        }
        return -1;
    }
}
