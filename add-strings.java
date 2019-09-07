// 655. Add Strings
// 中文English
// Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
//
// Example
// Example 1:
//
// Input : num1 = "123", num2 = "45"
// Output : "168"
// Notice
// The length of both num1 and num2 is < 5100.
// Both num1 and num2 contains only digits 0-9.
// Both num1 and num2 does not contain any leading zero.
// You must not use any built-in BigInteger library or convert the inputs to integer directly.


public class Solution {
    /**
     * @param num1 a non-negative integers
     * @param num2 a non-negative integers
     * @return return sum of num1 and num2
     */
    public String addStrings(String num1, String num2) {
        // Write your code here
        if (num1 == null || num1.length() == 0) {
            return num2;
        }
        if (num2 == null || num2.length() == 0) {
            return num1;
        }
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        int residue = 0;
        String result = ""; // set the original value of empty as answer
        while (len1 >= 0 || len2 >= 0) {
            if (len1 >= 0) {
                residue = num1.charAt(len1) - '0' + residue;
                len1--;
            }
            if (len2 >= 0) {
                residue += num2.charAt(len2) - '0';
                len2--;
            }
            result = residue % 10 + result; // we can use concatenation of int and String directly instead of StringBuilder
            // attention: concatenation cares about the relative position of elemtments, e.g. if we wrote result += residue % 10, it would be reversed direction
            residue = residue / 10;
        }
        // attention: similar to Add Two Numbers, maybe we still have last residue in the highest digit and others are all 0, so we need to append the last residue to the result
        if (residue != 0) {
            return residue + result;
        } else {
            return result;
        }
    }
}
