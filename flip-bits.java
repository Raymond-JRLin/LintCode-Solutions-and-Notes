// 181. Flip Bits
// 中文English
// Determine the number of bits required to flip if you want to convert integer n to integer m.
//
// Example
// Example 1:
// 	Input: n = 31, m = 14
// 	Output:  2
//
// 	Explanation:
// 	(11111) -> (01110) there are two different bits.
//
//
// Example 2:
// 	Input: n = 1, m = 7
// 	Output:  2
//
// 	Explanation:
// 	(001) -> (111) will change two bits.
//
//
// Notice
// Both n and m are 32-bit integers.
//


class Solution {
    /**
     *@param a, b: Two integer
     *return: An integer
     */
    public static int bitSwapRequired(int a, int b) {
        // write your code here
        int result = a ^ b;
        int count = 0;
        while (result != 0) {
            count++;
            result = result & (result - 1);
        }
        return count;
    }
};
