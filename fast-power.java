// 140. Fast Power
// 中文English
// Calculate the an % b where a, b and n are all 32bit non-negative integers.
//
// Example
// For 231 % 3 = 2
//
// For 1001000 % 1000 = 0
//
// Challenge
// O(logn)
//


class Solution {
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        // write your code here
        if (a == 0) {
            return 0;
        }
        if (n == 0) {
            return 1 % b;
        }
        if (n == 1) {
            return a % b;
        }
        long result = fastPower(a, b, n / 2);
        result *= result;
        result %= b;
        if (n % 2 == 1) {
            result = result * (a % b) % b;
        }
        return (int) result;
    }
};
