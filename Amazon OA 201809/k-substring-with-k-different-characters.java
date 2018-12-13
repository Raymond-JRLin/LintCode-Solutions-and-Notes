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

        // return mytry(stringIn, K);

        return method2(stringIn, K);
    }

    private int method2(String s, int k) {
        // sliding window
        // O(N) time and O(256) space
        int n = s.length();
        Set<String> result = new HashSet<>();
        int[] counts = new int[256];
        int distinct = 0; // # distinct char
        // first k
        for (int i = 0; i < k; i++) {
            int pos = (int) s.charAt(i);
            counts[pos]++;
            if (counts[pos] == 1) {
                distinct++;
            } else if (counts[pos] == 2) {
                distinct--;
            }
        }
        if (distinct == k) {
            result.add(s.substring(0, k));
        }
        for (int i = k; i < n; i++) {
            // new adding ending char
            int right = (int) s.charAt(i);
            counts[right]++;
            if (counts[right] == 1) {
                distinct++;
            } else if (counts[right] == 2) {
                distinct--;
            }
            // to-be deleting starting char
            int left = (int) s.charAt(i - k);
            counts[left]--;
            if (counts[left] == 0) {
                distinct--;
            } else if (counts[left] == 1) {
                distinct++;
            }
            if (distinct == k) {
                // new substring,  注意范围
                result.add(s.substring(i + 1 - k, i + 1));
            }
        }
        return result.size();
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
