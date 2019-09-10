// 41. Maximum Subarray
// 中文English
// Given an array of integers, find a contiguous subarray which has the largest sum.
//
// Example
// Example1:
//
// Input: [−2,2,−3,4,−1,2,1,−5,3]
// Output: 6
// Explanation: the contiguous subarray [4,−1,2,1] has the largest sum = 6.
// Example2:
//
// Input: [1,2,3,4]
// Output: 10
// Explanation: the contiguous subarray [1,2,3,4] has the largest sum = 10.
// Challenge
// Can you do it in time complexity O(n)?
//
// Notice
// The subarray should contain at least one number.
//


public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // method 1: prefix sum
        // int max = Integer.MIN_VALUE;
        // int minPreSum = 0;
        // int sum = 0;
        // for (int i = 0; i < nums.length; i++) {
        //     sum = sum + nums[i]; // calculate prefix sum ends at each position
        //     max = Math.max(sum - minPreSum, max); // record maximun value
        //     minPreSum = Math.min(minPreSum, sum); // update min prefix sum
        // }
        // return max;

        // method 2 - 1: DP -> f(i) = Max{f(i - 1) + A[i] , A[i]}
        // reference:http://algorithms.tutorialhorizon.com/dynamic-programming-maximum-subarray-problem/
        // int n = nums.length;
        // int[] f = new int[n];
        // // initialization
        // f[0] = nums[0];
        // // DP
        // for (int i = 1; i < n; i++) {
        //     f[i] = Math.max(f[i - 1] + nums[i], nums[i]);
        // }
        // int result = f[0];
        // for (int i = 1; i < n; i++) {
        //     result = Math.max(result, f[i]);
        // }
        // return result;

        // method 2-2: DP -> 2 variables
        // int n = nums.length;
        // int local = nums[0];
        // int global = nums[0];
        // for (int i = 1; i < n; i++) {
        //     local = Math.max(local + nums[i], nums[i]);
        //     // compare local with nums[i] in order to handle the case when all numbers in array are negative
        //     global = Math.max(global, local);
        // }
        // return global;

        // method 3: Greedy
        // int sum = 0;
        // int max = Integer.MIN_VALUE;
        // for (int i = 0; i < nums.length; i++) {
        //     sum += nums[i];
        //     max = Math.max(max, sum);
        //     if (sum < 0) {
        //         sum = 0;
        //     }
        // }
        // return max;

        return divide(nums, 0, nums.length - 1, Integer.MIN_VALUE);
    }
    private int divide(int A[], int left, int right, int tmax) {
        if(left > right) {
            return Integer.MIN_VALUE;
        }

        int mid = left + (right - left) / 2;
        //得到子区间[left, mid - 1]最大值
        int lmax = divide(A, left, mid - 1, tmax);
        //得到子区间[mid + 1, right]最大值
        int rmax = divide(A, mid + 1, right, tmax);

        tmax = Math.max(tmax, lmax);
        tmax = Math.max(tmax, rmax);

        int sum = 0;
        int mlmax = 0;
        //得到[left, mid - 1]最大值
        for(int i = mid - 1; i >= left; i--) {
            sum += A[i];
            mlmax = Math.max(mlmax, sum);
        }

        sum = 0;
        int mrmax = 0;
        //得到[mid + 1, right]最大值
        for(int i = mid + 1; i <= right; i++) {
            sum += A[i];
            mrmax = Math.max(mrmax, sum);
        }

        tmax = Math.max(tmax, A[mid] + mlmax + mrmax);
        return tmax;
    }
}
