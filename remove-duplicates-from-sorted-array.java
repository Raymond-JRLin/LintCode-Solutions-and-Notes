// 100. Remove Duplicates from Sorted Array
// 中文English
// Given a sorted array, 'remove' the duplicates in place such that each element appear only once and return the 'new' length.
//
// Do not allocate extra space for another array, you must do this in place with constant memory.
//
// Example
// Example 1:
//
// Input:  []
// Output: 0
// Example 2:
//
// Input:  [1,1,2]
// Output: 2
// Explanation:  uniqued array: [1,2]


public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            } else {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
