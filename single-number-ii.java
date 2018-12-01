// 83. Single Number II
// Description
// Given 3*n + 1 non-negative integer, every numbers occurs triple times except one, find it.
//
// Have you met this question in a real interview?
// Example
// Given [1,1,2,3,3,3,2,2,4,1] return 4
//
// Challenge
// One-pass, constant extra space.
//

public class Solution {
	/**
	 * @param A : An integer array
	 * @return : An integer
	 */
    public int singleNumberII(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }

        // one-pass and O(1) space: we should also use bit manipulation
        // 用一个32位的数的每一位表示某一位出现几次，出现3次就给它归零
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // 从右向左依次拿每一位
            int sum = 0;
            for (int j = 0; j < A.length; j++) {
                // 循环取每个 number
                if ((A[j] & (1 << i)) != 0) {

                    sum++;
                }
            }
            sum %= 3; // mod
            result |= (sum << i); // 将结果 (0 or 1) 左移到当前位， 与累计结果取或

        }

        return result;

    }
}
