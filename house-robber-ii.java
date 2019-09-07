// 534. House Robber II
// 中文English
// After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.
//
// Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
//
// Example
// Example1
//
// Input:  nums = [3,6,4]
// Output: 6
// Example2
//
// Input:  nums = [2,3,2,3]
// Output: 6
// Notice
// This is an extension of House Robber.
//


public class Solution {
    /**
     * @param nums: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    public int houseRobber2(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0 || nums.length == 2) {
            return 0;
        }
        int n = nums.length;
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 3) {
            return nums[1];
        }
        // 我的直接想法是： 因为偷了第一个就不能偷最后一个， 不然就可以偷最后一个， 我们可以用 I 中的代码， 更改初始值， 用两个 array 或者 调 I 的方法， 来比较两种情况下哪种偷得多
        // 其实也相当于把 array 给截去第一个和最后一个， 然后 rob
        // 偷了第一个， 不偷最后一个
        int first = houseRob(0, nums[0], nums, n - 1);
        // 没偷第一个， 偷最后一个
        int last = houseRob(0, 0, nums, n);
        return Math.max(first, last);

    }
    private int houseRob(int last2, int last1, int[] nums, int n) {
        int prev2 = last2; // no house
        int last = last1;
        for (int i = 2; i < n + 1; i++) {
            int temp = last;
            last = Math.max(last, prev2 + nums[i - 1]);
            prev2 = temp;
        }
        return last;
    }
}
