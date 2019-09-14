// 1399. Take Coins
// 中文English
// There aren coins in a row, each time you want to take a coin from the left or the right side. Take a total of k times to write an algorithm to maximize the value of coins.
//
// Example
// Example 1:
//
// Input: list = [5,4,3,2,1], k = 2
// Output :9
// Explanation:Take two coins from the left.
// Example 2:
//
// Input: list = [5,4,3,2,1,6], k = 3,
// Output:15
// Explanation:Take two coins from the left and one from the right.
// Notice
// 1 <= k <= n <= 100000。
// The value of the coin is not greater than 10000。


public class Solution {
    /**
     * @param list: The coins
     * @param k: The k
     * @return: The answer
     */
    public int takeCoins(int[] list, int k) {
        // Write your code here
        if (list == null || list.length < k || k == 0) {
            return 0;
        }

        if (list.length == k) {
            int sum = 0;
            for (int num : list) {
                sum += num;
            }
            return sum;
        }

        // return mytry(list, k);

        // return mytry2(list, k);
        // above MLE

        // return mytry3(list, k);
        // DP 其实想复杂了， 总共就取 k 个， 左边 i 个， 右边 k - i 个

        // return method2(list, k);

        return method3(list, k);
    }

    private int method3(int[] nums, int k) {
        // 总共就取 k 个， 左边 i 个， 右边 k - i 个， 是有限个可能性， 便利一遍就好， 想法类似于 DP 的 mytry3， 但更简单一些
        int n = nums.length;
        int left = 0; // index
        int sum = 0;
        int result = 0;
        while (left < k) {
            sum += nums[left];
            result = Math.max(result, sum);
            left++;
        }
        left--; // 还原一下
        int right = n - 1;
        while (right >= n - k) {
            sum = sum - nums[left] + nums[right];
            result = Math.max(result, sum);
            left--;
            right--;
        }
        return result;
    }

    private int method2(int[] nums, int k) {
        // 0, 1, 2, ..., n - 3, n - 2, n - 1
        // |-left-|      |------right------|
        // 左边取 left 个， 右边取 right 个， 可以用 prefixSum 得到: O(N) time and O(N) space
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        int result = 0;
        for (int left = 0; left <= k; left++) {
            int right = k - left; // left and right are 取的“个数”
            // 所以 prefix 里面的 index 就是个数
            int sum = prefix[n] - prefix[n - right] + prefix[left];
            result = Math.max(result, sum);
        }
        return result;
    }

    private int mytry3(int[] nums, int k) {
        // iteration DP: the 2nd previous tries still got MLE, so I think creating array with k size, since k <= n <= 100000, it would not improve a lot, so tried to split it into 2 arrays with k size: O(N ^ 2) time and O(k) space
        int n = nums.length;
        int result = 0;
        // definition: f[i][j] = 左边拿 i 个， 右边拿 j 个得到的最大值
        int[] left = new int[k + 1];
        int[] right = new int[k + 1];
        // initialization
        for (int i = 1; i < k + 1; i++) {
            left[i] = nums[i - 1] + left[i - 1];
            result = Math.max(result, left[i]);
        }
        for (int j = 1; j < k + 1; j++) {
            right[j] = nums[n - j] + right[j - 1]; // actually it's nums[n - 1 - (j - 1)]
            result = Math.max(result, right[j]);
        }
        // DP
        for (int i = 1; i < k + 1; i++) {
            for (int j = 1; j < k + 1 - i; j++) {
                // 注意这里 i j 的取值范围
                result = Math.max(result, right[j] + left[i]);

            }
        }
        // result: don't forget it may be in "initialization", so we should go through all values in array
        return result;
    }

    private int mytry2(int[] nums, int k) {
        // iteration DP: MLE
        int n = nums.length;
        // definition: f[i][j] = 左边拿 i 个， 右边拿 j 个得到的最大值
        int[][] f = new int[k + 1][k + 1];
        // initialization
        for (int i = 1; i < k + 1; i++) {
            f[i][0] = nums[i - 1] + f[i - 1][0];
        }
        for (int j = 1; j < k + 1; j++) {
            f[0][j] = nums[n - j] + f[0][j - 1]; // actually it's nums[n - 1 - (j - 1)]
        }
        // DP
        for (int i = 1; i < k + 1; i++) {
            for (int j = 1; j < k + 1 - i; j++) {
                f[i][j] = f[0][j] + f[i][0];

            }
        }
        // result
        int result = 0;
        for (int[] row : f) {
            for (int num : row) {
                result = Math.max(result, num);
            }
        }
        return result;
    }

    private int mytry(int[] nums, int k) {
        // recursion DP: MLE
        int n = nums.length;
        // definition: memo[i][j] = 从 [i, j] 拿到的最大值
        int[][] memo = new int[n][n];
        // initialization
        // DP
        return recursion(nums, memo, 0, n - 1, k);
    }
    private int recursion(int[] nums, int[][] memo, int start, int end, int k) {
        if (k == 0) {
            return 0;
        }
        if (memo[start][end] != 0) {
            return memo[start][end];
        }
        if (start == end) {
            return memo[start][end] = nums[start];
        }
        int left = nums[start] + recursion(nums, memo, start + 1, end, k - 1);
        int right = nums[end] + recursion(nums, memo, start, end - 1, k - 1);
        return memo[start][end] = Math.max(left, right);
    }
}
