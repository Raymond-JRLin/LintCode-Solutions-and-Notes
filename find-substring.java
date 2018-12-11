// 1377. Find Substring
// Description
// Given the length k, find all substrings of length k in the string str.The characters of a substring can not be repeated and output the number of substrings that satisfy such conditions (the same substring is counted only 1 times).
//
// |str| <= 100000.
// k <= 100000.
// All characters are lowercase.
// Have you met this question in a real interview?
// Example
// Given str = "abc", k = 2, output 2.
//
// Explanation:
// Characters are not repeated, and substrings of length k have "ab", "bc".
// Given str = "abacb", k = 1, output 3.
//
// Explanation:
// Characters are not repeated, and substrings of length k have "a", "b".”c”.


public class Solution {
    /**
     * @param str: The string
     * @param k: The length of the substring
     * @return: The answer
     */
    public int findSubstring(String str, int k) {
        // Write your code here
        if (str == null || str.length() <= k) {
            return 0;
        }
        // all characters are lowercase
        if (k > 26) {
            return 0;
        }

        // return method1(str, k);

        return method2(str, k);
    }

    private int method2(String s, int k) {
        int n = s.length();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n - k + 1; i++) {
            Set<Character> chars = new HashSet<>();
            int j = 0;
            while (j < k) {
                if (!chars.add(s.charAt(i + j))) {
                    break;
                }
                j++;
            }
            if (chars.size() == k) {
                set.add(s.substring(i, i + j));
            }
        }
        return set.size();
    }

    private int method1(String str, int k) {
        // 2 pointers
        int n = str.length();
        Set<String> stringSet = new HashSet<>();
        int count = 0;
        for (int i = 0; i < n + 1 - k; i++) {
            // start pointer
            Set<Character> charSet = new HashSet<>(); // record unrepeated char
            boolean isValid = true;
            for (int j = i; j <= i + k - 1; j++) {
                // end pointer: [i, i + k - 1]
                if (charSet.contains(str.charAt(j))) {
                    isValid = false;
                    break;
                }
                charSet.add(str.charAt(j));
            }
            if (isValid) {
                String curr = str.substring(i, i + k);
                if (!stringSet.contains(curr)) {
                    count++;
                    stringSet.add(curr);
                }
            }
        }
        return count;
    }

}
