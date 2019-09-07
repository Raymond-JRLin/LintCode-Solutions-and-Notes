// 569. Add Digits
// 中文English
// Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
//
// Example
// Example 1:
//
// Input:
// num=38
// Output:
// 2
// Explanation:
// The process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return 2.
//
// Example 2:
//
// Input:
// num=9
// Output:
// 9
// Explanation:
// 9<10,return 9.
// Challenge
// Could you do it without any loop/recursion in O(1) runtime?
//


public class Solution {
    /**
     * @param num a non-negative integer
     * @return one digit
     */
    public int addDigits(int num) {
        // Write your code here
        if (num < 10) {
            return num;
        }
        // method 1: use a loop to do, (I think we cannot use recursion by this addDigits() method)
        // while (num / 10 > 0) {
        //     int sum = 0; // 每一次都是对新的结果进行拆分
        //     // 1st loop is to detec result whether should be split digit again
        //     while (num > 0) {
        //         sum += num % 10;
        //         num = num / 10;
        //     }
        //     // 会发现， 诶， num 是做完了 可是 result 有可能还需要再拆分， 所以要两个 loop 来对应 num 和 result
        //     num = sum;
        // }
        // return num;

        // method 2: O(1) based on obervation
        // 1 - 9: num itself; 10 - 18: 1 - 9; 19 - 27: 1 - 9
        // 可以发现是每 9 个一循环， 那么就要对 9 取余
        // 但是 18 27 对 9 取余却为 0， 10 - 17， 20 - 26 可以直接取余， 所以要稍作变化
        return (num - 1) % 9 + 1;
    }
}
