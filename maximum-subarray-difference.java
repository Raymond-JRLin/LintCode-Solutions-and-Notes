// 45. Maximum Subarray Difference
// 中文English
// Given an array with integers.
//
// Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the largest.
//
// Return the largest difference.
//
// Example
// Example 1:
//
// Input:[1, 2, -3, 1]
// Output:6
// Explanation:
// The subarray are [1,2] and [-3].So the answer is 6.
// Example 2:
//
// Input:[0,-1]
// Output:1
// Explanation:
// The subarray are [0] and [-1].So the answer is 1.
// Challenge
// O(n) time and O(n) space.
//
// Notice
// The subarray should contain at least one number
//


public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two
     *          Subarrays
     */
    public int maxDiffSubArrays(int[] nums) {
        // write your code here
        // 比较综合，关于不重合的两段 subarray 我们知道要 forward-backward， 因为差值可以是正负之间，所以要作 double forward-backward，分别求 max 和 min， 然后找最大的差值
        int n = nums.length;
        int[] leftMax = new int[n];
        int[] leftMin = new int[n];
        forward(nums, leftMax, leftMin);
        int[] rightMax = new int[n];
        int[] rightMin = new int[n];
        backward(nums, rightMax, rightMin);
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            result = Math.max(result, Math.abs(leftMax[i] - rightMin[i + 1]));
            result = Math.max(result, Math.abs(leftMin[i] - rightMax[i + 1]));
        }
        return result;
    }
    private void forward(int[] nums, int[] max, int[] min) {
        int localMax = 0;
        int globalMax = Integer.MIN_VALUE;
        int localMin = 0;
        int globalMin = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            localMax = Math.max(nums[i], nums[i] + localMax);
            globalMax = Math.max(localMax, globalMax);
            max[i] = globalMax;
            localMin = Math.min(nums[i], nums[i] + localMin);
            globalMin = Math.min(localMin, globalMin);
            min[i] = globalMin;
        }
    }
    private void backward(int[] nums, int[] max, int[] min) {
        int localMax = 0;
        int globalMax = Integer.MIN_VALUE;
        int localMin = 0;
        int globalMin = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            localMax = Math.max(nums[i], nums[i] + localMax);
            globalMax = Math.max(localMax, globalMax);
            max[i] = globalMax;
            localMin = Math.min(nums[i], nums[i] + localMin);
            globalMin = Math.min(localMin, globalMin);
            min[i] = globalMin;
        }
    }
}
