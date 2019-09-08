// 78. Longest Common Prefix
// 中文English
// Given k strings, find the longest common prefix (LCP).
//
// Example
// Example 1:
// 	Input:  "ABCD", "ABEF", "ACEF"
// 	Output:  "A"
//
//
// Example 2:
// 	Input: "ABCDEFG", "ABCEFG" and "ABCEFA"
// 	Output:  "ABC"
//


public class Solution {
    /*
     * @param strs: A list of strings
     * @return: The longest common prefix
     */
    public String longestCommonPrefix(String[] strs) {
        // write your code here
        if (strs == null) {
            return null;
        }
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        // 直接的就是去比较所有的 string， 可有两种基本比较方式： 两两比较 string， 一个个比较所有 string 的 char
        // 在这两种方法的基础上， 我们可以用 D&C, BS, Trie 来 improve
        // ref: http://www.geeksforgeeks.org/longest-common-prefix-set-1-word-by-word-matching/

        // method 1: compare 2 strings by 2 strings (word by words matching)
        // String prefix = strs[0];
        // for (int i = 1; i < strs.length; i++) {
        //     prefix = compareStr(prefix, strs[i]);
        // }
        // return prefix;

        // method 2: compare all strings char by char (character by character matching)
        return compareChar(strs);
    }
    private String compareStr(String prefix, String string) {
        int i = 0;
        for (; i < prefix.length() && i < string.length(); i++) {
            if (prefix.charAt(i) != string.charAt(i)) {
                break;
            }
        }
        return prefix.substring(0, i);
    }
    private String compareChar(String[] strs) {
        int minLen = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < minLen) {
                minLen = strs[i].length();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLen; i++) {
            char curr = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (curr != strs[j].charAt(i)) {
                    return sb.toString();
                } else {
                    continue;
                }
            }
            sb.append(curr);
        }
        return sb.toString();
    }
}
