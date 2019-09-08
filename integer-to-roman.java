// 418. Integer to Roman
// 中文English
// Given an integer, convert it to a roman numeral.
//
// The number is guaranteed to be within the range from 1 to 3999.
//
// Example
// Example 1:
//
// Input: 1
// Output: "I"
// Example 2:
//
// Input: 99
// Output: "XCIX"
// Clarification
// What is Roman Numeral?
//
// https://en.wikipedia.org/wiki/Roman_numerals
// https://zh.wikipedia.org/wiki/罗马数字
// http://baike.baidu.com/view/42061.htm


public class Solution {
    /**
     * @param n The integer
     * @return Roman representation
     */
    public String intToRoman(int n) {
        // Write your code here
        // n is 1 ~ 3999
        String[] roman = {"M", "D", "C", "L", "X", "V", "I"};
        String result = "";
        int scale = 1000;
        for (int i = 0; i < 7; i += 2) {
            int digit = n / scale;
            if (digit != 0) {
                if (digit <= 3) {
                    for (int j = 1; j <= digit; j++) {
                        result += roman[i];
                    }
                } else if (digit == 4) {
                    result += roman[i] + roman[i - 1];
                } else if (digit > 4 && digit < 9) {
                    result += roman[i - 1];
                    for (int j = 5; j < digit; j++) {
                        result += roman[i];
                    }
                } else if (digit == 9) {
                    result += roman[i] + roman[i - 2];
                }
            }
            n %= scale;
            scale /= 10;
        }
        return result;
    }
}
