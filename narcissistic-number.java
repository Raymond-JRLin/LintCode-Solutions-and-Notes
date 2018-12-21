// 147. Narcissistic Number
// Description
// Narcissistic Number is a number that is the sum of its own digits each raised to the power of the number of digits. See wiki
//
// For example the 3-digit decimal number 153 is a narcissistic number because 153 = 13 + 53 + 33.
//
// And the 4-digit decimal number 1634 is a narcissistic number because 1634 = 14 + 64 + 34 + 44.
//
// Given n, return all narcissistic numbers with n digits.
//
// You may assume n is smaller than 8.
//
// Have you met this question in a real interview?
// Example
// Given 1, return [0,1,2,3,4,5,6,7,8,9].
//
// Given 2, return [].


public class Solution {
    /**
     * @param n: The number of digits
     * @return: All narcissistic numbers with n digits
     */
    public List<Integer> getNarcissisticNumbers(int n) {
        // write your code here

        return method1(n);
    }

    private List<Integer> method1(int n) {
        // brute force
        List<Integer> result = new ArrayList<>();
        // n 为 1 的时候比较特殊， 0 也是
        if (n == 1) {
            for (int i = 0; i < 10; i++) {
                result.add(i);
            }
            return result;
        }
        // 暴力地去 for n digit的所有数
        for (int i = pow(10, n - 1); i < pow(10, n); i++) {
            int sum = 0;
            int digit = i;
            // 得到每一个 digit 的 n 次方的和
            while (digit > 0) {
                sum += pow(digit % 10, n);
                digit /= 10;
            }
            // 如果相等就是答案
            if (sum == i) {
                result.add(i);
            }
        }
        return result;
    }
    // 重写一下 pow 函数， 自带的返回的是 double 类型
    private int pow(int base, int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= base;
        }
        return result;
    }
}
