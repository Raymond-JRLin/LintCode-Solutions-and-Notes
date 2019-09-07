// 563. Backpack V
// 中文English
// Given n items with size nums[i] which an integer array and all positive numbers. An integer target denotes the size of a backpack. Find the number of possible fill the backpack.
//
// Each item may only be used once
//
// Example
// Given candidate items [1,2,3,3,7] and target 7,
//
// A solution set is:
// [7]
// [1, 3, 3]
// return 2
//


public class Solution {
    /**
     * @param nums: an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
    public int backPackV(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // return mytry(nums, target);

        // return method1(nums, target);

        return method2(nums, target);
    }

    private int method2(int[] nums, int target) {
        // O(N ^ 2) time and O(N) space: 1D-array DP
        int n = nums.length;
        // definition
        int[] f = new int[target + 1];
        // initialization
        f[0] = 1;
        // DP
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= nums[i]; j--) {
                f[j] += f[j - nums[i]];
            }
        }
        // result
        return f[target];
    }

    private int method1(int[] nums, int target) {
        // O(N ^ 2) time and space: 2D-array DP
        int n = nums.length;
        // definition: f[i][j] = 给 nums 中前 i 个数， 容量为 j 的背包填满的可能的方法数
        int[][] f = new int[n + 1][target + 1];
        // initialization
        for (int i = 0; i < n + 1; i++) {
            // size of backpack is 0
            f[i][0] = 1;
        }
        for (int j = 1; j < target + 1; j++) {
            // not give any item
            f[0][j] = 0;
        }
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < target + 1; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= nums[i - 1]) {
                    f[i][j] += f[i - 1][j - nums[i - 1]];
                }
            }
        }
        // result
        return f[n][target];
    }

    private int mytry(int[] nums, int target) {
        // TLE
        Arrays.sort(nums);
        int[] result = new int[1];
        dfs(nums, target, 0, 0, result);
        return result[0];
    }
    private void dfs(int[] nums, int target, int index, int sum, int[] result) {
        if (sum == target) {
            result[0]++;
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            dfs(nums, target, i + 1, sum + nums[i], result);
        }
    }
}
