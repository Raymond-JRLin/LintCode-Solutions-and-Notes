// 144. Interleaving Positive and Negative Numbers
// 中文English
// Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.
//
// Example
// Example 1
//
// Input : [-1, -2, -3, 4, 5, 6]
// Outout : [-1, 5, -2, 4, -3, 6]
// Explanation :  any other reasonable answer.
// Challenge
// Do it in-place and without extra memory.
//
// Notice
// You are not necessary to keep the original order of positive integers or negative integers.
//


class Solution {
    /**
     * @param A: An integer array.
     * @return: void
     */
    public void rerange(int[] A) {
        // write your code here
        if (A == null || A.length <= 2) {
            return;
        }
        int n = A.length;
        int positive = countPos(A);
        if (positive > n / 2) {
            swap(A, 0, 1);
            return;
        } else {
            swap(A, 1, 0);
            return;
        }
    }
    private int countPos(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (num > 0) {
                count++;
            }
        }
        return count;
    }
    private void swap(int[] nums, int pos, int neg) {
        int n = nums.length;
        while (pos < n && neg < n) {
            while (pos < n && nums[pos] > 0) {
                pos += 2;
            }
            while (neg < n && nums[neg] < 0) {
                neg += 2;
            }
            if (pos < n && neg < n) {
                int temp = nums[pos];
                nums[pos] = nums[neg];
                nums[neg] = temp;
            }
        }
    }
}
