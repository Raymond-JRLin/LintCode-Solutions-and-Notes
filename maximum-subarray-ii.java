// 42. Maximum Subarray II
// 中文English
// Given an array of integers, find two non-overlapping subarrays which have the largest sum.
// The number in each subarray should be contiguous.
// Return the largest sum.
//
// Example
// Example 1:
//
// Input:
// [1, 3, -1, 2, -1, 2]
// Output:
// 7
// Explanation:
// the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2].
// Example 2:
//
// Input:
// [5,4]
// Output:
// 9
// Explanation:
// the two subarrays are [5] and [4].
// Challenge
// Can you do it in time complexity O(n) ?
//
// Notice
// The subarray should contain at least one number
//


public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        // same as Buy and Sell Stock III - find 2 non-overlapping subarray whose sum is max
        if (nums == null || nums.size() < 2) {
            return 0;
        }
        int n = nums.size();
        int[] left = new int[n];
        forward(nums, left);
        int[] right = new int[n];
        backward(nums, right);
        int result = left[0] + right[1];
        // here is different from Buy and Sell Stock III, where we can buy and sell in the same day, but here we cannot do that but only choose non-overlapping subarray
        for (int i = 1; i < n - 1; i++) {
            if (left[i] + right[i + 1] > result) {
                result = left[i] + right[i + 1];
            }
        }
        return result;
    }
    private void forward(ArrayList<Integer> nums, int[] left) {
        int n = nums.size();

        // based on DP
        // left[0] = nums.get(0);
        // for (int i = 1; i < n; i++) {
        //     left[i] = Math.max(left[i - 1] + nums.get(i), nums.get(i));
        // }
        // // if we use DP to do this, there is a difference from Buy and Sell Stock III, which is there may be gap between 1st and 2nd subarray, hence if we want to do the last step in main method - for loop to find the max of left[i] and right[i + 1], we should update again left and right to make smaller value after a bigger value as the same as bigger one
        // // if we use prefix sum to handle this method to get left and right array then we don't need to do this additional step since we would record min sum and recalculate prefix sum in each step
        // int max = left[0];
        // for (int i = 1; i < n; i++) {
        //     if (max > left[i]) {
        //         left[i] = max;
        //     } else {
        //         max = left[i];
        //     }
        // }

        // based on prefix sum
        // int minSum = 0;
        // int prefixSum = 0;
        // int max = Integer.MIN_VALUE;
        // for (int i = 0; i < n; i++) {
        //     prefixSum += nums.get(i);
        //     max = Math.max(max, prefixSum - minSum); // technically, this step can be seen as 2nd for loop in DP method, we always keep the max subarray sum instead of current prefixSum - minSum
        //     // AND we should do this step before updating minSum since minSum should be updating ahead of updating current prefixSum
        //     minSum = Math.min(minSum, prefixSum);
        //     left[i] = max;

        //     // or we can do following order - update minSum before we calculate current prefixSum:
        //     // minSum = Math.min(minSum, sum);
        //     // sum += nums.get(i);
        //     // maxSub = Math.max(maxSub, sum - minSum);
        //     // maxSubArray[i] = maxSub;
        // }

        // baded on Greedy by local and global
        int local = 0;
        int global = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            local = Math.max(nums.get(i), local + nums.get(i));
            global = Math.max(global, local);
            left[i] = global;
        }
    }
    private void backward(ArrayList<Integer> nums, int[] right) {
        int n = nums.size();

        // based on DP
        // right[n - 1] = nums.get(n - 1);
        // for (int i = n - 2; i >= 0; i--) {
        //     right[i] = Math.max(right[i + 1] + nums.get(i), nums.get(i));
        // }
        // // similar as when we handle forward but start from rear
        // int max = right[n - 1];
        // for (int i = n - 2; i >= 0; i--) {
        //     if (max > right[i]) {
        //         right[i] = max;
        //     } else {
        //         max = right[i];
        //     }
        // }

        // based on prefix sum
        // int minSum = 0;
        // int prefixSum = 0;
        // int max = Integer.MIN_VALUE;
        // for (int i = n - 1; i >= 0; i--) {
        //     prefixSum += nums.get(i);
        //     max = Math.max(max, prefixSum - minSum);
        //     minSum = Math.min(minSum, prefixSum);
        //     right[i] = max;
        // }

        // baded on Greedy by local and global
        int local = 0;
        int global = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            local = Math.max(nums.get(i), local + nums.get(i));
            global = Math.max(global, local);
            right[i] = global;
        }
    }
}
