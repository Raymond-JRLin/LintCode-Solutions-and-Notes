// 2. Trailing Zeros
// 中文English
// Write an algorithm which computes the number of trailing zeros in n factorial.
//
// Example
// Example 1:
// 	Input:  11
// 	Output: 2
//
// 	Explanation:
// 	11! = 39916800, so the output should be 2
//
// Example 2:
// 	Input:  5
// 	Output: 1
//
// 	Explanation:
// 	5! = 120, so the output should be 1.
//
// Challenge
// O(log N) time
//

public class Solution {
    /*
     * @param n: An integer
     * @return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
        // 数学题， 看了 wiki 和答案， 求 trailing 0 相当于求有多少个 10 相乘， 可以拆分成其质数的个数， 而 2 远多于 5， 所以求小的个数
        // 11! = 11 * 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1;
        // 求几个5， 那就做除法

        if (n <= 0) {
            return 0;
        }

        // method 1: iteration
        // long result = 0;
        // while (n > 0) {
        //     n = n / 5;
        //     result += n;
        // }
        // return result;

        // method 2: recursion
        return n / 5 + trailingZeros(n / 5);
    }
};
