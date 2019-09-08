// 1639. K-Substring with K different characters
// 中文English
// Given a string S and an integer K.
// Calculate the number of substrings of length K and containing K different characters
//
// Example
// Example 1:
//
// Input："abcabc"，k=3
// Output：3
// Explanation：
// substrings:  ["abc", "bca", "cab"]
// Example 2:
//
// Input："abacab"，k=3
// Output：2
// Explanation：
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
        int n = s.length();
        Set<String> result = new HashSet<>();
        int[] counts = new int[256];
        int distinct = 0;
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
            int right = (int) s.charAt(i);
            counts[right]++;
            if (counts[right] == 1) {
                distinct++;
            } else if (counts[right] == 2) {
                distinct--;
            }
            int left = (int) s.charAt(i - k);
            counts[left]--;
            if (counts[left] == 0) {
                distinct--;
            } else if (counts[left] == 1) {
                distinct++;
            }
            if (distinct == k) {
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
