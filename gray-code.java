// 411. Gray Code
// 中文English
// The gray code is a binary numeral system where two successive values differ in only one bit.
//
// Given a non-negative integer n representing the total number of bits in the code, find the sequence of gray code. A gray code sequence must begin with 0 and with cover all 2n integers.
//
// Example
// Example 1:
//
// Input: 1
// Output: [0, 1]
// Example 2:
//
// Input: 2
// Output: [0, 1, 3, 2]
// Explanation:
//   0 - 00
//   1 - 01
//   3 - 11
//   2 - 10
// Challenge
// O(2n) time.
//
// Notice
// For a given n, a gray code sequence is not uniquely defined.
//
// When n=2, both [0,1,3,2] and [0,2,3,1] are valid gray code sequence according to the above definition.
//


public class Solution {
    /*
     * @param n: a number
     * @return: Gray code
     */
    public List<Integer> grayCode(int n) {
        // write your code here
        List<Integer> result = new ArrayList<Integer>();

        // method 1: according to gray code definition
        // ref: http://www.cnblogs.com/grandyang/p/4315607.html
        // for (int i = 0; i < Math.pow(2, n); i++) {
        //     result.add((i >> 1) ^ i);
        // }
        // return result;

        // method 2: mirror version
        result.add(0);
        for (int i = 0; i < n; i++) {
            int size = result.size();
            for (int j = size - 1; j >= 0; j--) {
                result.add((1 << i) | result.get(j));
            }
        }
        return result;
    }
}
