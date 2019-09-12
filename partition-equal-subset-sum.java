// 588. Partition Equal Subset Sum
// 中文English
// Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
//
// Example
// Example 1:
//
// Input: nums = [1, 5, 11, 5],
// Output: true
// Explanation:
// two subsets: [1, 5, 5], [11]
// Example 2:
//
// Input: nums = [1, 2, 3, 9],
// Output: false
// Notice
// Each of the array element will not exceed 100.
// The array size will not exceed 200.
//
// [1,5,11,5]
//


public class Solution {
    /*
     * @param nums: a non-empty array only positive integers
     * @return: true if can partition or false
     */
    public boolean canPartition(int[] nums) {
        // write your code here
        int n = nums.length;
        if (n == 1) {
            return false;
        }
        if (n == 2) {
            return nums[0] == nums[1];
        }

        // my try: absolutely wrong LOL, 尽管是左右两边去加， 但是不一定是这么顺序的组合， 可能会混杂， 同时判断相差是 2 的倍数也不行， 因为可能差的是 2 的倍数， 但是组合不出 diff
        // 还是一开始直觉的 DP 可能可以
        // Arrays.sort(nums);
        // int left = 0;
        // int right = n - 1;
        // int left_sum = nums[0];
        // int right_sum = nums[n - 1];
        // while (left + 1 < right) {
        //     if (left_sum <= right_sum) {
        //         left++;
        //         left_sum += nums[left];
        //     } else {
        //         right--;
        //         right_sum += nums[right];
        //     }
        // }
        // if (left_sum == right_sum || Math.abs(right_sum - left_sum) % 2 == 0) {
        //     return true;
        // } else {
        //     return false;
        // }

        // 看一下参考， 这题其实是背包问题： 能不能取到一些数， 让他们的和等于数组和的一半
        // first we should check if sum can be divided to 2 parts evenly
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        // method 1: DP with 2D arrays
        // definition: f[i][j]: check if first (i - 1)-th elements can be composed to j
        // boolean[][] f = new boolean[n + 1][target + 1];
        // // initialization
        // f[0][0] = true;
        // for (int i = 1; i < n + 1; i++) {
        //     f[i][0] = true;
        // }
        // for (int i = 1; i < target + 1; i++) {
        //     f[0][i] = false;
        // }
        // // DP
        // for (int i = 1; i < n + 1; i++) {
        //     for (int j = 1; j < target + 1; j++) {
        //         f[i][j] = f[i - 1][j];
        //         if (j - nums[i - 1] >= 0) {
        //             f[i][j] = f[i][j] || f[i - 1][j - nums[i - 1]];
        //         }
        //     }
        // }
        // // result
        // return f[n][target];

        // method 2: DP with 1D array
        // definition: f[j]: check if first current index elements can be composed to j
        boolean[] f = new boolean[target + 1];
        // initialization
        f[0] = true;
        // DP
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= 1; j--) {
                if (j >= nums[i]) {
                    f[j] = f[j] || f[j - nums[i]];
                }
            }
        }
        // result
        return f[target];
    }
}
