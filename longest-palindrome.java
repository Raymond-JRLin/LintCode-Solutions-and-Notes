// 627. Longest Palindrome
// 中文English
// Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
//
// This is case sensitive, for example "Aa" is not considered a palindrome here.
//
// Example
// Example 1:
//
// Input : s = "abccccdd"
// Output : 7
// Explanation :
// One longest palindrome that can be built is "dccaccd", whose length is `7`.
// Notice
// Assume the length of given string will not exceed 1010.
//


public class Solution {
    /**
     * @param s a string which consists of lowercase or uppercase letters
     * @return the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        int odd = set.size();
        if (odd == 0) {
            return s.length();
        } else {
            return s.length() - odd + 1;
        }
    }
}
