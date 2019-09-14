// 417. Valid Number
// 中文English
// Validate if a given string is numeric.
//
// Example
// Example 1:
//
// Input: "0"
// Output: true
// Explanation: "0" can be converted to 0
// Example 2:
//
// Input: "0.1"
// Output: true
// Explanation: "0.1" can be converted to 0.1
// Example 3:
//
// Input: "abc"
// Output: false
// Example 4:
//
// Input: "1 a"
// Output: false
// Example 5:
//
// Input: "2e10"
// Output: true
// Explanation: "2e10" represents 20,000,000,000


public class Solution {
    /**
     * @param s the string that represents a number
     * @return whether the string is a valid number
     */
    public boolean isNumber(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return false;
        }
        int start = 0;
        int end = s.length() - 1;
        // delete white space in the front
        while (start <= end && Character.isWhitespace(s.charAt(start))) {
            start++;
        }
        if (start > end) {
            return false;
        }
        // delete white space at the end
        while (end >= start && Character.isWhitespace(s.charAt(end))) {
            end--;
        }
        // delete '+' or '-' in the front
        if (s.charAt(start) == '+' || s.charAt(start) == '-') {
            start++;
        }
        boolean num = false;
        boolean dot = false;
        boolean exp = false;
        while (start <= end) {
            char c = s.charAt(start);
            // several cases
            if (Character.isDigit(c)) {
                num = true;
            } else if (c == '.') {
                // 小数点：如果之前出现过小数点或者自然底数，返回false，否则标记dot为true。
                if (dot || exp) {
                    return false;
                }
                dot = true;
            } else if (c == 'e') {
                // 自然底数：如果之前出现过自然底数或者之前从未出现过数字，返回false，否则标记exp为true，num为false。
                if (exp || !num) {
                    return false;
                }
                exp = true;
                num = false;
            } else if (c == '+' || c == '-') {
                // 符号：符号前面如果有字符的话必须是空格或者是自然底数，标记sign为true。
                if (s.charAt(start - 1) != 'e') {
                    return false;
                }
            } else {
                // 其他字符：返回false。
                return false;
            }
            start++;
        }
        return num;

        // regex:
        // if (s.trim().isEmpty()) {
        //     return false;
        // }
        // String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";
        // if (s.trim().matches(regex)) {
        //     return true;
        // } else {
        //     return false;
        // }
    }
}
