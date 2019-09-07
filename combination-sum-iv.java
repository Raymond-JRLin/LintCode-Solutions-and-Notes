// 564. Combination Sum IV
// 中文English
// Given an integer array nums with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
//
// Example
// Example1
//
// Input: nums = [1, 2, 4], and target = 4
// Output: 6
// Explanation:
// The possible combination ways are:
// [1, 1, 1, 1]
// [1, 1, 2]
// [1, 2, 1]
// [2, 1, 1]
// [2, 2]
// [4]
// Example2
//
// Input: nums = [1, 2], and target = 4
// Output: 5
// Explanation:
// The possible combination ways are:
// [1, 1, 1, 1]
// [1, 1, 2]
// [1, 2, 1]
// [2, 1, 1]
// [2, 2]
// Notice
// A number in the array can be used multiple times in the combination.
// Different orders are counted as different combinations.
//


public class Solution {
    /**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    public int backPackVI(int[] nums, int target) {
        // Write your code here
        // similar to Combanition Sum
        int n = nums.length;
        // DP:
        // definition: f[i] means total # way of combinations adding up to backpack with size of i
        int[] f = new int[target + 1];
        // initialization
        f[0] = 1; // there is only one way to fulfill backpack with size of 0: nothing
        // DP
        for (int i = 1; i <= target; i++) {
            // 外层循环遍历背包的 size 大小
            for (int j = 0; j < n; j++) {
                // 内层循环遍历给的数组中的每个数
                if (i - nums[j] >= 0) {
                    // 如果此时背包 size 比这个数大， 才有可能考虑放得进去
                    // 不断累加能放入的方法个数
                    f[i] += f[i - nums[j]];
                }
            }
        }
        // result
        return f[target];
    }
}
