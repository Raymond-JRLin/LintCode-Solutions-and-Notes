// 365. Count 1 in Binary
// 中文English
// Count how many 1 in binary representation of a 32-bit integer.
//
// Example
// Example 1:
//
// Input: 32
// Output: 1
// Explanation:
// 32(100000), return 1.
// Example 2:
//
// Input: 5
// Output: 2
// Explanation:
// 5(101), return 2.
// Challenge
// If the integer is n bits with m 1 bits. Can you do it in O(m) time?
//


public class Solution {
    /**
     * @param num: an integer
     * @return: an integer, the number of ones in num
     */
    public int countOnes(int num) {
        // write your code here

        // method 1: cheat by API
        // return Integer.bitCount(num);

        // method 2: based on bit manipulation, O(m) where m is the count of 1 in binary representation
        // in O(1) check power of 2, we use i & (i - 1) to erase the last 1, so we can do this to count how many 1 earased in loop
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }
};
