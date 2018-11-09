// 148. Sort Colors
// Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
//
// Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
//
// Example
// Given [1, 0, 1, 2], sort it in-place to [0, 1, 1, 2].
//
// Challenge
// A rather straight forward solution is a two-pass algorithm using counting sort.
// First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
//
// Could you come up with an one-pass algorithm using only constant space?
//
// Notice
// You are not suppose to use the library's sort function for this problem.
// You should do it in-place (sort numbers in the original array).


class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        // write your code here

        method1(nums);
    }
    private void method1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        // keep all 0 in the left of left pointer,
        // and 2 in the right side of right pointer
        while (i <= right) { // i < right is wrong, it can be ...0 2...
            if (nums[i] == 0) {
                swap(nums, i, left);
                i++;
                left++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, right);
                right--;
                // i cannot be added 1 because we need to judge this new element
            }
        }
    }
    private void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}
