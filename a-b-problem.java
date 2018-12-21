// 1. A + B Problem
// Description
// Write a function that add two numbers A and B.
//
// There is no need to read data from standard input stream. Both parameters are given in function aplusb, you job is to calculate the sum and return it.
//
// Have you met this question in a real interview?
// Clarification
// Are a and b both 32-bit integers?
//
// Yes.
// Can I use bit operation?
//
// Sure you can.
// Example
// Given a=1 and b=2 return 3.
//
// Challenge
// Of course you can just return a + b to get accepted. But Can you challenge not do it like that?(You should not use + or any arithmetic operators.)
//
// Related Problems


public class Solution {
    /**
     * @param a: An integer
     * @param b: An integer
     * @return: The sum of a and b
     */
    public int aplusb(int a, int b) {
        // write your code here

        return method1(a, b);
    }

    private int method1(int a, int b) {
        // 主要利用异或运算来完成
        // 异或运算有一个别名叫做：不进位加法
        // 那么a ^ b就是a和b相加之后，该进位的地方不进位的结果
        // 然后下面考虑哪些地方要进位，自然是a和b里都是1的地方
        // a & b就是a和b里都是1的那些位置，a & b << 1 就是进位
        // 之后的结果。所以：a + b = (a ^ b) + (a & b << 1)
        // 令a' = a ^ b, b' = (a & b) << 1
        // 可以知道，这个过程是在模拟加法的运算过程，进位不可能
        // 一直持续，所以b最终会变为0。因此重复做上述操作就可以
        // 求得a + b的值。
        while (b != 0) {
            int a2 = (a ^ b);
            int b2 = ((a & b) << 1);
            a = a2;
            b = b2;
        }
        return a;
    }
}
