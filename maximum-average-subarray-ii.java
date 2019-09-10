// 617. Maximum Average Subarray II
// 中文English
// Given an array with positive and negative numbers, find the maximum average subarray which length should be greater or equal to given length k.
//
// Example
// Example 1:
//
// Input:
// [1,12,-5,-6,50,3]
// 3
// Output:
// 15.667
// Explanation:
//  (-6 + 50 + 3) / 3 = 15.667
// Example 2:
//
// Input:
// [5]
// 1
// Output:
// 5.000
// Notice
// It's guaranteed that the size of the array is greater or equal to k.
//


public class Solution {
    /**
     * @param nums an array with positive and negative numbers
     * @param k an integer
     * @return the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        // Write your code here
        if (nums == null || nums.length < k) {
            return (double)0;
        }
        // it's simialr to maximum subarray iv, but there is one difference: it's not sucessary that result would be greater with greater prefix sum - min sum, since it's alse influenced by divisor
        int n = nums.length;
        // double result = 0.0;
        // for (int i = 0; i < k; i++) {
        //     result += (double)nums[i];
        // }
        // result /= k;
        // Pair[] prefixSum = new Pair[n + 1];
        // prefixSum[0] = new Pair(0.0, 0);
        // double minSum = 0.0;
        // for (int i = 1; i < n + 1; i++) {
        //     prefixSum[i] = new Pair((prefixSum[i - 1].val * prefixSum[i - 1].div + nums[i - 1]) / (prefixSum[i - 1].div + 1), prefixSum[i - 1].div + 1);
        //     if (i >= k) {
        //         result = Math.max(result, prefixSum[i].val - minSum);
        //         minSum = Math.min(minSum, prefixSum[i - k + 1].val);
        //     }
        // }
        // return result;
        // above is wrong

        // first we can try prefix sum, and 2 for loops to traverse every possibilities in each eligible position - but it would TLE
        // double[] sum = new double[n + 1];
        // sum[0] = 0.0;
        // for (int i = 1; i < n + 1; i++) {
        //     sum[i] = sum[i - 1] + (double)nums[i - 1];
        // }
        // double max = (double)Integer.MIN_VALUE;
        // for (int j = n; j >= k; j--) {
        //     for (int i = 0; i < n - k + 1; i++) {
        //         if (j - i >= k) {
        //             max = Math.max(max, (sum[j] - sum[i]) / (j - i));
        //         } else {
        //             break;
        //         }
        //     }
        // }
        // return max;

        // so we should optimize above method, better than pure traverse is: Binary Search on result
        double left = Integer.MAX_VALUE;
        double right = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] < left) {
                left = nums[i]; // assign left as the min value in nums array
            }
            if (nums[i] > right) {
                right = nums[i]; // assign right as the max value in nums array
            }
        }
        // binaray search on result
        while (right - left >= 1e-6) {
            // because we cannot compare 2 double type values, so we set a relative small value as gap
            double mid = left + (right - left) / 2.0;
            if (helper(nums, mid, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
    private boolean helper(int[] nums, double mid, int k) {
        // prefix sum
        int n = nums.length;
        double[] prefixSum = new double[n + 1];
        prefixSum[0] = 0;
        double minSum = 0;
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1] - mid;
            if (i >= k && prefixSum[i] - minSum >= 0) {
                return true;
            }
            if (i >= k) {
                minSum = Math.min(minSum, prefixSum[i - k + 1]); //
            }
        }
        return false;
    }
}
class Pair {
    double val;
    int div;
    Pair(double num, int d) {
        val = num;
        div = d;
    }
    // pretty tedious solution and cannot do prefixSum - minSum as usual
}
