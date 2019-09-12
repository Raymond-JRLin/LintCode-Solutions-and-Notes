// 413. Reverse Integer
// 中文English
// Reverse digits of an integer. Returns 0 when the reversed integer overflows (signed 32-bit integer).
//
// Example
// Example 1:
//
// Input: 123
// Output: 321
// Example 2:
//
// Input: -123
// Output: -321


public class Solution {

    /*
     * @param n: the integer to be reversed
     * @return: the reversed integer
     */
    public int reverseInteger(int n) {
        // write your code here
        if (n == 0) {
            return 0;
        }
        // similar to Palindrome number

        boolean isNeg = false;
        if (n < 0) {
            n = -n;
            isNeg = true;
        }
        // 注意审题： Returns 0 when the reversed integer overflows， 所以要考虑 如何判断是否 overflow 了
        int result = 0;
        while (n != 0) {
            int temp = result * 10 + n % 10;
            n = n / 10;
            // 肯定在这人判断是否 overflow， 但是 int 如果 overflow 了， 那就变成另外一个数， 不能和 MAX_VALUE 比较
            if (temp / 10 != result) {
                return 0;
            }
            result = temp;
        }
        if (isNeg) {
            return -result;
        } else {
            return result;
        }
    }
};
