// 422. Length of Last Word
// 中文English
// Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
//
// If the last word does not exist, return 0.
//
// Example
// Example 1:
//
// Input: "Hello World"
// Output: 5
// Example 2:
//
// Input: "Hello LintCode"
// Output: 8
// Notice
// A word is defined as a character sequence consists of non-space characters only.
//


public class Solution {
    /**
     * @param s A string
     * @return the length of last word
     */
    public int lengthOfLastWord(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }

        // method 1:
        // String[] array = s.split(" ");
        // if (array.length == 0) {
        //     return 0;
        // }
        // String last = array[array.length - 1];
        // return last.length();

        // method 2:
        char[] array = s.toCharArray();
        int length = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            if (length == 0) {
                if (array[i] == ' ') {
                    continue;
                } else {
                    length++;
                }
            } else {
                if (array[i] == ' ') {
                    break;
                } else {
                    length++;
                }
            }
        }
        return length;
    }
}
