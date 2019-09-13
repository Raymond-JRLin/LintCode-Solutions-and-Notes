// 54. String to Integer (atoi)
// 中文English
// Implement function atoi to convert a string to an integer.
//
// If no valid conversion could be performed, a zero value is returned.
//
// If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
//
// Example
// Example 1
//
// Input: "10"
// Output: 10
// Example 2
//
// Input: "1"
// Output: 1
// Example 3
//
// Input: "123123123123123"
// Output: 2147483647
// Explanation: 123123123123123 > INT_MAX, so we return INT_MAX
// Example 4
//
// Input: "1.0"
// Output: 1
// Explanation: We just need to change the first vaild number


public class Solution {
    /*
     * @param str: A string
     * @return: An integer
     */
    public int atoi(String str) {
        // write your code here
        if (str == null || str.length() == 0) {
            return 0;
        }
        // my try: just as logic process
        // 这题其实不难， 就是考虑多种边界条件， 直直的按照运算逻辑写， 然后再根据不同的边界条件修改， 可以是可以， 就是比较麻烦， 可以参照 method2， 怎样按照逻辑来写并包括各种条件
        // str = str.trim(); // remove leading and trailing spaces
        // int n = str.length();
        // boolean isNegative = false; // negative flag
        // if (str.charAt(0) == '-') {
        //     str = str.substring(1, n);
        //     n--;
        //     isNegative = true;
        // } else if (str.charAt(0) == '+') {
        //     // attention leading '+'
        //     str = str.substring(1, n);
        //     n--;
        // }
        // boolean hasDecimals = false; // check if it has decimals
        // int deci_index = n;
        // if (str.contains(".")) {
        //     hasDecimals = true;
        //     deci_index = str.indexOf(".");
        // }
        // double result = 0;
        // for (int i = 0; i < deci_index; i++) {
        //     char c = str.charAt(i);
        //     if (!Character.isDigit(c)) {
        //         // illegal input
        //         if (isNegative) {
        //             return (int) -result;
        //         } else {
        //             return (int) result;
        //         }
        //     }
        //     int digit = c - '0';
        //     result = result * 10 + digit;
        //     if (result > Integer.MAX_VALUE) {
        //         if (isNegative) {
        //             return Integer.MIN_VALUE;
        //         } else {
        //             return Integer.MAX_VALUE;
        //         }
        //     }
        // }
        // if (hasDecimals) {
        //     if (str.charAt(deci_index + 1) - '0' >= 5) {
        //         result++;
        //     }
        // }
        // if (isNegative) {
        //     return (int) -result;
        // } else {
        //     return (int) result;
        // }

        return method2_simpler(str);
    }
    private int method2_simpler(String str) {
        str = str.trim();
        int n = str.length();
        int sign = 1;
        int i = 0;
        if (str.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (str.charAt(i) == '+') {
            i++;
        }
        double result = 0;
        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            // 用 while 循环更好， 遇到不符合规定的输入， 直接 break
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }
        // if (sign == -1) {
            // 先写这个转换正负， 下面最值的判断就不需要再在里面包含正负判断了
            // 如果用 int 来确定正负， 不论正负， 直接乘上去
            result = sign * result;
        // }
        // 处理 min and max
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) result;
    }
}
