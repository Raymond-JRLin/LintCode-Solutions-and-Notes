// 704. Bulb Switcher II
// 中文English
// There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations towards buttons, you need to return how many different kinds of status of the n lights could be.
//
// Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:
//
// You can flip all the lights.
// You can flip lights with even numbers.
// You can flip lights with odd numbers.
// You can flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
// Example
// Example1
//
// Input:
// 1
// 1
// Output: 2
// Explanation:
// Status can be: [on], [off]
// Example2
//
// Input:
// 2
// 1
// Output: 3
// Explanation:
// Status can be: [on, off], [off, on], [off, off]


public class Solution {
    /*
     * @param : number of lights
     * @param : number of operations
     * @return: the number of status
     */
    public int flipLights(int n, int m) {
        // write your code here
        // 对于题目所给的 4 种方式： 1 + 2 --> 3, 1 + 3 --> 2, 2 + 3 --> 1， 因此只有 8 种结果： All_on, 1, 2, 3, 4, 1+4, 2+4, 3+4
        // return 的是状态数， 而不是亮灯的灯泡数
        if (n == 0 || m == 0) {
            return 1; // all on
        }
        if (n == 1) {
            return 2; // 1 个灯泡只有开、关两种状态
        }
        if (n == 2) {
            // 两个灯泡
            if (m == 1) {
                // all on, odd, even
                return 3;
            } else {
                // all on, all off, odd, even
                return 4;
            }
        }
        // n > 2
        if (m == 1) {
            // all off, odd, even, k
            return 4;
        } else if (m == 2) {
            // 1, 2, 3, 4, 加上加和的那 3 个
            return 7;
        } else {
            // all cases
            return 8;
        }
    }
};
