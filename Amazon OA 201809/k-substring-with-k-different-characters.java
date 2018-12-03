// 1639. K-Substring with K different characters
// Description
// Given a string S and an integer K.
// Calculate the number of substrings of length K and containing K different characters
//
// Have you met this question in a real interview?
// Example
// String: "abcabc"
// K: 3
//
// Answer: 3
// substrings:  ["abc", "bca", "cab"]
// String: "abacab"
// K: 3
//
// Answer: 2
// substrings: ["bac", "cab"]

public class Solution {
    /**
     * @param stringIn: The original string.
     * @param K: The length of substrings.
     * @return: return the count of substring of length K and exactly K distinct characters.
     */
    public int KSubstring(String stringIn, int K) {
        // Write your code here
        if (stringIn == null || stringIn.length() < K) {
            return 0;
        }

        return mytry(stringIn, K);
    }

    private int mytry(String s, int k) {
        // O(N) time and O(N) space
        int n = s.length();
        Set<String> result = new HashSet<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n - k + 1; i++) {
            int j = 0;
            while (j < k) {
                if (!set.add(s.charAt(i + j))) {
                    break;
                }
                j++;
            }
            if (set.size() == k) {
                result.add(s.substring(i, i + j));
            }
            set.clear(); // 如果没有这句， 或者把创建 set 的语句放在 for loop 内部， 则会 MLE
        }
        return result.size();
    }
}
