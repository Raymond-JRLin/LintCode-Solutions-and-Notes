// 415. Valid Palindrome
// 中文English
// Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
//
// Example
// Example 1:
//
// Input: "A man, a plan, a canal: Panama"
// Output: true
// Explanation: "amanaplanacanalpanama"
// Example 2:
//
// Input: "race a car"
// Output: false
// Explanation: "raceacar"
// Challenge
// O(n) time without extra memory.
//
// Notice
// Have you consider that the string might be empty? This is a good question to ask during an interview.
//
// For the purpose of this problem, we define empty string as valid palindrome.


public class Solution {
    /**
     * @param s A string
     * @return Whether the string is a valid palindrome
     */
    public boolean isPalindrome(String s) {
        // Write your code here
        // need to check block by block because of white spaces or punctuation
        // so we only compare LETTERS and DIGITS, ignoring upper/lower case
        // attention to those empty cases
        if (s == null || s.length() == 0) {
            return true;
        } // null case
        int front = 0;
        int end = s.length() - 1;
        // initialize 2 pionters
        while (front < end) {
            while (front < s.length() && !isValid(s.charAt(front))) {
                front++;
            } // this value is not a letter or digit, then omit this position, hence move forward front pointer
            // pay ATTENTION to the LOGIC in condition: we need to verify whether this front position exists or not, then consider its value, which is the most common and correct logic
            if (front == s.length()) {
                return true;
                // if a string has only puctuation, after previous while loop, the position will reach out the string, i.e. front == length, but this will not enter while to do some verification, so need one more to judge this condition
            }
            while (end >= 0 && !isValid(s.charAt(end))) {
                // similarly we need put checking of end position before checking char value
                end--; // similar to the front pointer, omitting spaces and punctuations
            }
            if (Character.toLowerCase(s.charAt(front)) == Character.toLowerCase(s.charAt(end))) {
                front++;
                end--;
            } else {
                break;
            }
        }
        return end <= front;
    }
    private boolean isValid(char ch) {
        return Character.isLetter(ch) || Character.isDigit(ch);
        // return simplify
    }
}
