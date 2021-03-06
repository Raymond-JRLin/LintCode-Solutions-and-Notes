// 13. Implement strStr()
// 中文English
// For a given source string and a target string, you should output the first index(from 0) of target string in source string.
//
// If target does not exist in source, just return -1.
//
// Example
// Example 1:
//
// Input: source = "source" ，target = "target"
// Output: -1
// Explanation: If the source does not contain the target content, return - 1.
// Example 2:
//
// Input:source = "abcdabcdefg" ，target = "bcd"
// Output: 1
// Explanation: If the source contains the target content, return the location where the target first appeared in the source.
// Challenge
// O(n2) is acceptable. Can you implement an O(n) algorithm? (hint: KMP)
//
// Clarification
// Do I need to implement KMP Algorithm in a real interview?
//
// Not necessary. When you meet this problem in a real interview, the interviewer may just want to test your basic implementation ability. But make sure you confirm with the interviewer first.


class Solution {
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        // write your code here
        if (source == null || target == null) {
            return -1;
        } //null case
        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            int j = 0;
            for (j = 0; j < target.length(); j++) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    break;
                }
            }
            if (j == target.length()) {
                return i;
            }
        }
        return -1;
    }
}
