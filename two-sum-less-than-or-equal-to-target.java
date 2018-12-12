// 609. Two Sum - Less than or equal to target
// Description
// Given an array of integers, find how many pairs in the array such that their sum is less than or equal to a specific target number. Please return the number of pairs.
//
// Have you met this question in a real interview?
// Example
// Given nums = [2, 7, 11, 15], target = 24.
// Return 5.
// 2 + 7 < 24
// 2 + 11 < 24
// 2 + 15 < 24
// 7 + 11 < 24
// 7 + 15 < 25


public class Solution {
    /**
     * @param nums an array of integer
     * @param target an integer
     * @return an integer
     */
    public int twoSum5(int[] nums, int target) {
        // Write your code here
        if (nums.length == 0 || nums == null) {
            return 0;
        }

        return method1(nums, target);
    }

    private int method1(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left < right) {
            if (nums[left] + nums[right] <= target) {
                count += right - left;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }
}
