// 44. Minimum Subarray
// 中文English
// Given an array of integers, find the continuous subarray with smallest sum.
//
// Return the sum of the subarray.
//
// Example
// Example 1
//
// Input：[1, -1, -2, 1]
// Output：-3
// Example 2
//
// Input：[1, -1, -2, 1, -4]
// Output：-6
// Notice
// The subarray should contain one integer at least.
//


public class Solution {
    /**
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray(ArrayList<Integer> nums) {
        // write your code
        int n = nums.size();

        // method 1: prefix sum
        // int[] prefixSum = new int[n + 1];
        // prefixSum[0] = 0;
        // for (int i = 0; i < n; i++) {
        //     prefixSum[i + 1] = prefixSum[i] + nums.get(i);
        // }
        // int maxSum = prefixSum[0];
        // int min = Integer.MAX_VALUE;
        // for (int i = 1; i < n + 1; i++) {
        //     min = Math.min(prefixSum[i] - maxSum, min);
        //     if (prefixSum[i] > maxSum) {
        //         maxSum = prefixSum[i];
        //     }
        // }
        // return min;

        // method 2: prefix sum by 2 variables
        int sum = 0;
        int maxSum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, sum);
            sum += nums.get(i);
            min = Math.min(min, sum - maxSum);
        }
        return min;


        // method 3: DP with array
        // int[] f = new int[n];
        // f[0] = nums.get(0);
        // for (int i = 1; i < n; i++) {
        //     if (f[i - 1] + nums.get(i) <= nums.get(i)) {
        //         f[i] = f[i - 1] + nums.get(i);
        //     } else {
        //         f[i] = nums.get(i);
        //     }
        // }
        // int min = f[0];
        // for (int i = 1; i < n; i++) {
        //     min = Math.min(min, f[i]);
        // }
        // return min;

        // method 4: DP with 2 variables
        // int global = nums.get(0);
        // int local = nums.get(0);
        // for (int i = 1; i < n; i++) {
        //     local = Math.min(local + nums.get(i), nums.get(i));
        //     global = Math.min(global, local);
        // }
        // return global;
    }
}
