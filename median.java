// 80. Median
// Given a unsorted array with integers, find the median of it.
//
// A median is the middle number of the array after it is sorted.
//
// If there are even numbers in the array, return the N/2-th number after sorted.
//
// Example
// Given [4, 5, 1, 2, 3], return 3.
//
// Given [7, 9, 4, 5], return 5.
//
// Challenge
// O(n) time.


public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: An integer denotes the middle number of the array.
     */
    public int median(int[] nums) {
        // write your code here




    }

    private int method2(int[] nums) {
        // method 2: quick select, O(n)
        return quickSelect(nums, 0, nums.length - 1, (nums.length - 1) / 2);
    }
    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        int left = start;
        int right = end;
        int pivot = nums[start + (end - start) / 2];
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        if (k <= right) {
            return quickSelect(nums, start, right, k);
        } else if (k >= left) {
            return quickSelect(nums, left, end, k);
        } else {
            return nums[k];
        }
    }
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private int method1(int[] nums) {
        // method 1: basic O(nlogn)
        Arrays.sort(nums);
        return nums[(nums.length - 1) / 2];
    }
}
