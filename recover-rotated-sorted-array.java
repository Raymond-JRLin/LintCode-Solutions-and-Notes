// 39. Recover Rotated Sorted Array
// 中文English
// Given a rotated sorted array, recover it to sorted array in-place.
//
// Example
// Example1:
// [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
// Example2:
// [6,8,9,1,2] -> [1,2,6,8,9]
//
// Challenge
// In-place, O(1) extra space and O(n) time.
//
// Clarification
// What is rotated array?
//
// For example, the orginal array is [1,2,3,4], The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]


public class Solution {
    /**
     * @param nums: The rotated sorted array
     * @return: void
     */
    public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
        // write your code
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1)) {
                reverse(nums, 0, i);
                reverse(nums, i + 1, nums.size() - 1);
                reverse(nums, 0, nums.size() - 1);
            }
        }
        // binary search
        // int start = 0;
        // int end = nums.size();
        // while (start + 1 < end) {
        //     int mid = start + (end - start) / 2;
        //     if (nums.get(mid) >= nums.get(start)) {
        //         start = mid;
        //     } else {
        //         end = mid;
        //     }
        // }
        // int i = start;
        // reverse(nums, 0, i);
        // reverse(nums, i + 1, nums.size() - 1);
        // reverse(nums, 0, nums.size() - 1);
    }
    private void reverse(ArrayList<Integer> nums, int start, int end) {
        for (int left = start, right = end; left < right; left++, right--) {
            int temp = nums.get(left);
            nums.set(left, nums.get(right));
            nums.set(right, temp);
        }
    }
}
