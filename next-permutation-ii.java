// 190. Next Permutation II
// 中文English
// Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//
// If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
//
// Example
// Example 1:
//
// Input:1,2,3
// Output:1,3,2
// Example 2:
//
// Input:3,2,1
// Output:1,2,3
// Example 3:
//
// Input:1,1,5
// Output:1,5,1
// Challenge
// The replacement must be in-place, do not allocate extra memory.
//


public class Solution {
    /**
     * @param nums: an array of integers
     * @return: return nothing (void), do not return anything, modify nums in-place instead
     */
    public void nextPermutation(int[] nums) {
        // write your code here
        if (nums == null || nums.length <= 1) {
            return;
        }
        // 从前往后找到最后一个上升的地方，它右边是一个递减序列，它的前一位数是要换到后面的，那从后面找一个和它交换的就是从后往前第一个比它大的数；交换之后，需要讲右边递减序列reverse成递增序列
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
            return;
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
