// 52. Next Permutation
// 中文English
// Given a list of integers, which denote a permutation.
//
// Find the next permutation in ascending order.
//
// Example
// Example 1:
//
// Input:[1]
// Output:[1]
// Example 2:
//
// Input:[1,3,2,3]
// Output:[1,3,3,2]
// Example 3:
//
// Input:[4,3,2,1]
// Output:[1,2,3,4]
// Notice
// The list may contains duplicate integers.
//


public class Solution {
    /**
     * @param nums: an array of integers
     * @return: An array of integers that's next permuation
     */
    public int[] nextPermutation(int[] nums) {
        // write your code here
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        // 1: find the last increasing number (last: start from end of array)
        int n = nums.length;
        int index = -1; // index 0 could be the last increasing number, e.g. [1, 2, 1], than next is [2, 1, 1]
        for (int i = n - 1; i > 0; i--) {
            // we should compare current with previous one, so from 1 - n
            if (nums[i] > nums[i - 1]) {
                index = i - 1;
                break;
            }
        }
        // special case: the last permutation, e.g. [3, 2, 1], next [1, 2, 3]
        if (index == -1) {
            reverse(nums, 0);
            return nums;
        }
        // 2: find the first bigger number than nums[index] after index from the end or array
        int firstBigger = index + 1; // firstBigger at least would be next one
        for (int i = n - 1; i > index; i--) {
            if (nums[i] > nums[index]) {
                firstBigger = i;
                break;
            }
        }
        // 3: swap last increasing number and first bigger number
        swap(nums, index, firstBigger);
        // 4: reverse rest part after index of last increasing number
        reverse(nums, index + 1);
        return nums;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
    private void reverse(int[] nums, int index) {
        int i = index;
        int j = nums.length - 1;
        while (i <= j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
