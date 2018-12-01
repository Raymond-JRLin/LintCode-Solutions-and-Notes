// 84. Single Number III
// Description
// Given 2*n + 2 numbers, every numbers occurs twice except two, find them.
//
// Have you met this question in a real interview?
// Example
// Given [1,2,2,3,4,4,5,3] return 1 and 5
//
// Challenge
// O(n) time, O(1) extra space.
//

public class Solution {
    /**
     * @param A : An integer array
     * @return : Two integers
     */
    public List<Integer> singleNumberIII(int[] A) {
        // write your code here
        // 从 I 我们知道异或最终的结果会是单独的那个数， 现在有两个单独的数， 异或的结果就是这俩的异或， 那要怎么区分出来呢？ 因为它们俩是不同的， 也就意味着他俩异或的结果一定有 1 在里面， 我们可以找到最低位的 1， 然后再对数组中的所有数做一遍异或， 但是加一条件： 最低位 1 的那位分别按照 0 和 1 的不同分成两组， 在两组内异或清零， 最后分别剩下来的就是两个不同的数了
        // 实在是巧妙， 可是不会还是不会 =。=
        int xor = 0;
        for (int val : A) {
            // get xor of all values in array
            xor ^= val;
        }
        // get the last 1 bit, (xor & (xor - 1)) gets
        int lastOne = xor - (xor & (xor - 1));
        // for O(1) space, we use 2 varibles or array with length of 2
        int group0 = 0;
        int group1 = 0;
        for (int val : A) {
            if ((val & lastOne) == 0) {
                group0 ^= val;
            } else {
                group1 ^= val;
            }
        }
        List<Integer> result = new ArrayList<>();
        result.add(group0);
        result.add(group1);
        return result;
    }
}
