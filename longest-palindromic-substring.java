// 200. Longest Palindromic Substring
// 中文English
// Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
//
// Example
// Example 1:
//
// Input:"abcdzdcab"
// Output:"cdzdc"
// Example 2:
//
// Input:"aba"
// Output:"aba"
// Challenge
// O(n2) time is acceptable. Can you do it in O(n) time.
//


public class Solution {
    /*
     * @param s: input string
     * @return: the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        // write your code here
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return "";
        }
        int n = s.length();
        // method 1: brute method of O(n^3) with 3 loops
        // String result = "";
        // int max = 0;
        // for (int i = 0; i < n; i++) {
        //     // loop 1 for start index
        //     char curr = s.charAt(i);
        //     for (int j = n - 1; j >= i; j--) {
        //         // loop 2 for end index
        //         if (s.charAt(j) == curr) {
        //             // loop 3 to check if it's a palindrome
        //             if (isPalindrome(s, i, j)) {
        //                 String temp = s.substring(i, j + 1);
        //                 if (temp.length() > max) {
        //                     // if it's longer, then update
        //                     max = temp.length();
        //                     result = temp;
        //                 }
        //             }
        //         } else {
        //             continue;
        //         }
        //     }

        // }
        // return result;

        // method 2: DP with O(n ^ 2) time and O(n ^ 2) space
        // ref: http://bangbingsyb.blogspot.com/2014/11/leetcode-longest-palindromic-substring.html
        // String result = "";
        // int max = 0; // update with DP
        // // definition: f[i][j] means if it's a palindrome of string in [i, j]
        // boolean[][] f = new boolean[n][n]; // index, 0-th is no meaning
        // // initialization
        // // DP
        // for (int i = n - 1; i >= 0; i--) {
        //     // top-down: 是因为
        //     for (int j = i; j < n; j++) {
        //         if (s.charAt(i) == s.charAt(j) && (i + 1 > j - 1 || f[i + 1][j - 1])) {
        //             // attention the condition
        //             f[i][j] =true;
        //             // if it's true - palindrome, then we update
        //             if (j - i + 1 > max) {
        //                 max = j - i + 1;
        //                 result = s.substring(i, j + 1);
        //             }
        //         }

        //     }
        // }
        // or we can change the relative position of i and j
        // ref: http://fisherlei.blogspot.com/2012/12/leetcode-longest-palindromic-substring.html
        // for (int j = 0; j < n; j++) {
        //     for (int i = 0; i <= j; i++) {
        //         f[i][j] = (s.charAt(i) == s.charAt(j) && (j - i < 2 || f[i + 1][j - 1]));
        //         if (f[i][j] && j - i + 1 > max) {
        //             max = j - i + 1;
        //             result = s.substring(i, j + 1);
        //         }
        //     }
        //     // f[j][j] = true;
        // }
        // result
        // return result;

        // method 3: DP with O(n ^ 2) time and O(1) space
        // we can use longest palindrome substring to replace 2D array
        // the idea is to generate all even length and odd length palindromes and keep track of the longest palindrome seen so far.
        // Step to generate odd length palindrome: Fix a centre and expand in both directions for longer palindromes.
        // Step to generate even length palindrome: Fix two centre ( low and high ) and expand in both directions for longer palindromes.
        String longest = s.substring(0, 1);
        for (int i = 0; i < n; i++) {
            // for odd length palindrome
            String temp = search(s, i, i, n);
            if (temp.length() > longest.length()) {
                longest = temp;
            }
            // for even length palindrome
            temp = search(s, i, i + 1, n);
            if (temp.length() > longest.length()) {
                longest = temp;
            }
        }
        return longest;
    }
    // method 1:
    private boolean isPalindrome(String s, int left, int right) {
        int start = left;
        int end = right;
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                break;
            }
        }
        return end <= start;
    }
    // method 3
    private String search(String s, int start, int end, int n) {
        while (start >= 0 && end < n && s.charAt(start) == s.charAt(end)) {
            // expand to 2 directions
            start--;
            end++;
        }
        return s.substring(start + 1, end);
    }
}
