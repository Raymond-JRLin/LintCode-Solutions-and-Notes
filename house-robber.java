// 392. House Robber
// 中文English
// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
// Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
//
// Example
// Example 1:
//
// Input: [3, 8, 4]
// Output: 8
// Explanation: Just rob the second house.
// Example 2:
//
// Input: [5, 2, 1, 3]
// Output: 8
// Explanation: Rob the first and the last house.
// Challenge
// O(n) time and O(1) memory.
//


public class Solution {
    /*
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        if (n == 1) {
            return A[0];
        }
        if (n == 2) {
            return Math.max(A[0], A[1]);
        }

        // method 1:
        // definition: f[i] means money robbed when reach i house
        // long[] f = new long[n];
        // // initialization
        // f[0] = A[0];
        // f[1] = A[1];
        // // DP
        // for (int i = 2; i < n; i++) {
        //     // 每次要根据前一个房子偷没偷来决定当下的这个房子 f[i] 是否要偷
        //     // 前一个房子没偷， 那这个房子可以偷： f[i - 2] + 当下这个房子的价值
        //     // 前一个房子偷了, 那这个房子不可以偷： 只能用前一个房子的价值， i.e. f[i - 1]
        //     f[i] = Math.max(f[i - 1], f[i - 2] + A[i]);
        // }
        // // result
        // return f[n - 1];
        // 上面的对了， 但是我觉得这么初始化不好， 因为 f[1] 也是可以选择偷或不偷的， 如果直接赋值为 A[1] 那就默认了是偷 1 不是偷 0， 应该初始化 0 为 0， 即什么都不偷／没房子可偷

        // similar: we can set i as i-th/length rather than index
        // long[] f = new long[n + 1];
        // f[0] = 0;
        // f[1] = A[0];
        // for (int i = 2; i < n + 1; i++) {
        //     f[i] = Math.max(f[i - 1], f[i - 2] + A[i - 1]);
        // }
        // return f[n];

        // method 2: 因为当下房子偷的价值只取决于前一个和前前个房子的偷的价值， 所以可以用滚动数组来代替一整个 O(n) space
        // ref: http://frankge.me/blog/2016/11/16/getting-start-to-dynamic-programming-part2-some-exercises-slightly-difficult-from-lintcode-2/
        // long[] f = new long[3];
        // f[0] = A[0];
        // f[1] = A[1];
        // for (int i = 2; i < n; i++) {
        //     // 口口口口口口口口口口
        //     // 0 1 2
        //     //   0 1 2
        //     // 2 是要求的房子价值， 2 往后移动， 0, 1 也相对应的后移
        //     f[2] = Math.max(f[1], f[0] + A[i]);
        //     f[0] = f[1];
        //     f[1] = f[2];
        //     // 注意这里更改的先后顺序
        // }
        // return f[2];
        long[] f = new long[3];
        f[0] = 0; // no house
        f[1] = A[0];
        for (int i = 2; i < n + 1; i++) {
            //  口口口口口口口口口口
            // 0 1 2
            //   0 1 2
            // 2 是要求的房子价值， 2 往后移动， 0, 1 也相对应的后移
            f[2] = Math.max(f[1], f[0] + A[i - 1]);
            f[0] = f[1];
            f[1] = f[2];
            // 注意这里更改的先后顺序
        }
        return f[2];

        // method 3: 降维成 2 variables
        // ref: https://segmentfault.com/a/1190000003811581
        // long prev2 = A[0];
        // long last = A[1];
        // for (int i = 2; i < n; i++) {
        //     long temp = last;
        //     last = Math.max(last, prev2 + A[i]);
        //     prev2 = temp;
        // }
        // return last;
        // long prev2 = 0;
        // long last = A[0];
        // for (int i = 2; i < n + 1; i++) {
        //     long temp = last;
        //     last = Math.max(last, prev2 + A[i - 1]);
        //     prev2 = temp;
        // }
        // return last;

    }
};
