// 51. Previous Permutation
// 中文English
// Given a list of integers, which denote a permutation.
//
// Find the previous permutation in ascending order.
//
// Example
// Example 1:
//
// Input:[1]
// Output:[1]
// Example 2:
//
// Input:[1,3,2,3]
// Output:[1,2,3,3]
// Example 3:
//
// Input:[1,2,3,4]
// Output:[4,3,2,1]
//
// Notice
// The list may contains duplicate integers.
//


public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers that's previous permuation
     */
    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
		// write your code
		if (nums == null || nums.size() <= 1) {
		    return nums;
		}
		int n = nums.size();
		// int index = 0;
		// for (int i = n - 1; i > 0; i--) {
		//    if (nums.get(i) > nums.get(i - 1)) {
		//        index = i - 1;
		//    }
		// }
        // we cannot use for loop but while loop, because if the decreasing sequence is not starting from the end of the array, it means there is only one number, which is the last one, is in the decreasing sequence
        // 1: find the first bigger number after decreaseing sequence from back to front
        int i = n - 1;
        while (i > 0 && nums.get(i - 1) <= nums.get(i)) {
            // use <= to AC case of repeated numbers, e.g. [1, 1, 2]
            i--;
        }
        int index = i - 1;
        if (index == -1) {
            reverse(nums, 0);
            return nums;
        }
        // 2: find the biggest number, which is smaller than above value, after above value; be aware of numbers in rest after above value would be larger or smaller; and rest would descending sequence in view of back-to-front, so we should traverse from left to right
        int pivot = nums.get(index);
        int biggestIndex = index + 1; // from back to front
        while (biggestIndex < n && nums.get(biggestIndex) < pivot) {
            biggestIndex++;
        }
        biggestIndex--; // find the biggest number smaller than pivot
        // 3: swap the first decreasing number and biggest value smaller than that
        swap(nums, index, biggestIndex);
        // 4: reverse rest list after index from descending to ascending
        reverse(nums, index + 1);
        return nums;
    }
    private void swap(ArrayList<Integer> nums, int i, int j) {
        int temp = nums.get(j);
        nums.set(j, nums.get(i)); // be aware of how to set value in ArrayList
        nums.set(i, temp);
    }
    private void reverse(ArrayList<Integer> nums, int index) {
        int i = index;
        int j = nums.size() - 1;
        while (i <= j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
