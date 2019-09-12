// 428. Pow(x, n)
// 中文English
// Implement pow(x, n). (n is an integer.)
//
// Example
// Example 1:
//
// Input: x = 9.88023, n = 3
// Output: 964.498
// Example 2:
//
// Input: x = 2.1, n = 3
// Output: 9.261
// Example 3:
//
// Input: x = 1, n = 0
// Output: 1
// Challenge
// O(logn) time
//
// Notice
// You don't need to care about the precision of your answer, it's acceptable if the expected answer and your answer 's difference is smaller than 1e-3.

public class Solution {
    /*
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {
        // write your code here
        if (x == 0) {
            return 0L;
        }
        if (n == 0) {
            return 1L;
        }
        if (n > 0) {
            return pow(x, n);
        } else {
            return (1 / pow(x, -n));
        }
    }
    private double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double half = pow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
