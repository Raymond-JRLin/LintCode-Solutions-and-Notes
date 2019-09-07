// 159. Find Minimum in Rotated Sorted Array
// 中文English
// Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//
// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
// Find the minimum element.
//
// Example
// Example 1:
//
// Input：[4, 5, 6, 7, 0, 1, 2]
// Output：0
// Explanation：
// The minimum value in an array is 0.
// Example 2:
//
// Input：[2,1]
// Output：1
// Explanation：
// The minimum value in an array is 1.
// Notice
// You can assume no duplicate exists in the array.
//


public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        // int target = nums[nums.length - 1];
        // while (start + 1 < end) {
        //     int mid = start + (end - start) / 2;
        //     if (nums[mid] == target) {
        //         end = mid;
        //     } else if (nums[mid] < target) {
        //         end = mid;
        //     } else {
        //         start = mid;
        //     }
        // }
        // if (nums[start] <= target) {
        //     return nums[start];
        // } else if (nums[start] > target) {
        //     return nums[end];
        // } else {
        //     return -1;
        // }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return Math.min(nums[start], nums[end]);
    }
}
