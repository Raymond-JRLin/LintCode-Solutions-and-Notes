// 458. Last Position of Target
// Description
// Find the last position of a target number in a sorted array. Return -1 if target does not exist.
//
// Have you met this question in a real interview?
// Example
// Given [1, 2, 2, 4, 5, 5].
//
// For target = 2, return 2.
//
// For target = 5, return 5.
//
// For target = 6, return -1.


public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int lastPosition(int[] nums, int target) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // return mytry(nums, target);

        return mytry2(nums, target);
    }

    private int mytry2(int[] nums, int target) {
        // BS
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] == target) {
            return end;
        } else if (nums[start] == target) {
            return start;
        } else {
            return -1;
        }
    }

    private int mytry(int[] nums, int target) {
        // O(N), TLE
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
