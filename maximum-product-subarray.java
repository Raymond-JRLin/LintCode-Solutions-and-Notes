// 191. Maximum Product Subarray
// 中文English
// Find the contiguous subarray within an array (containing at least one number) which has the largest product.
//
// Example
// Example 1:
//
// Input:[2,3,-2,4]
// Output:6
// Example 2:
//
// Input:[-1,2,4,1]
// Output:8
// Notice
// The product of the largest subsequence of the product, less than 2147483647
//


public class Solution {
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct(int[] nums) {
        // write your code here
        // for product, we should consider sign - positive or negative;
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;

        // DP: based on arrays
        // // max, min, nums[i] could be positive or negative, so
        // // max_product[i] = Max{max_product[i - 1] * nums[i], min_product[i - 1] * nums[i], nums[i]}
        // // min_product[i] = Min{max_product[i - 1] * nums[i], min_product[i - 1] * nums[i], nums[i]}
        // // definition
        // int[] max = new int[n]; // 以nums[i]结尾的max subarray product
        // int[] min = new int[n]; // 以nums[i]结尾的min subarray product
        // // initialization
        // max[0] = nums[0];
        // min[0] = nums[0];
        // int result = max[0];
        // // DP
        // for (int i = 1; i < n; i++) {
        //     if (nums[i] > 0) {
        //         max[i] = Math.max(max[i - 1] * nums[i], nums[i]);
        //         min[i] = Math.min(min[i - 1] * nums[i], nums[i]);
        //     } else {
        //         max[i] = Math.max(min[i - 1] * nums[i], nums[i]);
        //         min[i] = Math.min(max[i - 1] * nums[i], nums[i]);
        //     }
        //     result = Math.max(result, max[i]);
        // }
        // // result
        // return result;

        // DP: based on 2 variables - according to above transfer functions directly
        // definition & initialization
        int max = nums[0];
        int min = nums[0];
        int result = max;
        for (int i = 1; i < n; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            result = Math.max(result, max);
        }
        return result;
    }
}
