// 414. Divide Two Integers
// 中文English
// Divide two integers without using multiplication, division and mod operator.
//
// If it will overflow(exceeding 32-bit signed integer representation range), return 2147483647
//
// The integer division should truncate toward zero.
//
// Example
// Example 1:
//
// Input: dividend = 0, divisor = 1
// Output: 0
// Example 2:
//
// Input: dividend = 100, divisor = 9
// Output: 11


public class Solution {
    /**
     * @param dividend the dividend
     * @param divisor the divisor
     * @return the result
     */
    public int divide(int dividend, int divisor) {
        // Write your code here
        if (divisor == 0) {
            return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int result = 0;
        // be aware of negative cases, e.g. -1, 1
        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        while (a >= b) {
            int power = 0;
            while (a >= (b << power)) {
                power++;
            }
            a -= b << (power - 1);
            result += 1 << (power - 1);
        }
        return isNegative ? -result : result;
    }
}
