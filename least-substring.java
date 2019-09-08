// 1638. Least Substring
// 中文English
// Given a string containing n lowercase letters, the string needs to be divided into several continuous substrings, the letter in the substring should be same, and the number of letters in the substring does not exceed k, and output the minimal substring number meeting the requirement.
//
// Example
// Example1
//
// Input: s = "aabbbc", k = 2
// Output: 4
// Explanation:
// we can get "aa", "bb", "b", "c" four substring.
// Example2
//
// input: s = "aabbbc", k = 3
// Output: 3
// we can get "aa", "bbb", "c" three substring.
// Notice
// n \leq 1e5n≤1e5


public class Solution {
    /**
     * @param s: the string s
     * @param k: the maximum length of substring
     * @return: return the least number of substring
     */
    public int getAns(String s, int k) {
        // Write your code here
        if (s == null || s.length() < k) {
            return 0;
        }
        if (k == 1) {
            return s.length();
        }

        return mytry(s, k);
    }

    private int mytry(String s, int k) {
        int n = s.length();
        int result = 0;
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int j = 0;
            for ( ; j < k; j++) {
                if (i + j >= n || s.charAt(i + j) != c) {
                    break;
                }
            }
            result++;
            i += j;
        }
        return result;
    }
}
