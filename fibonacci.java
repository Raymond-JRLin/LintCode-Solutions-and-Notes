// 366. Fibonacci
// 中文English
// Find the Nth number in Fibonacci sequence.
//
// A Fibonacci sequence is defined as follow:
//
// The first two numbers are 0 and 1.
// The i th number is the sum of i-1 th number and i-2 th number.
// The first ten numbers in Fibonacci sequence is:
//
// 0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...
//
// Example
// Example 1:
// 	Input:  1
// 	Output: 0
//
// 	Explanation:
// 	return the first number in  Fibonacci sequence .
//
// Example 2:
// 	Input:  2
// 	Output: 1
//
// 	Explanation:
// 	return the second number in  Fibonacci sequence .
//
// Notice
// The Nth fibonacci number won't exceed the max value of signed 32-bit integer in the test cases.
//


public class Solution {
    /*
     * @param : an integer
     * @return: an ineger f(n)
     */
    public int fibonacci(int n) {
        // write your code here
        if (n == 0 || n == 1) {
            return n - 1;
        }
        // method 1: based on array and similar DP, O(n) time and O(n) space
        // int[] f = new int[n + 1];
        // f[1] = 0;
        // f[2] = 1;
        // for (int i = 3; i < n + 1; i++) {
        //     f[i] = f[i - 1] + f[i - 2];
        // }
        // return f[n];

        // method 2:
        int first = 0;
        int second = 1;
        int i = 3;
        int result = first + second;
        while (i < n) {
            first = second;
            second = result;
            result = first + second;
            i++;
        }
        return result;
    }
};
