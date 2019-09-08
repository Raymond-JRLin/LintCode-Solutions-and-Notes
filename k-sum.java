// 89. k Sum
// 中文English
// Given n distinct positive integers, integer k (k <= n) and a number target.
//
// Find k numbers where sum is target. Calculate how many solutions there are?
//
// Example
// Example 1
//
// Input:
// List = [1,2,3,4]
// k = 2
// target = 5
// Output: 2
// Explanation: 1 + 4 = 2 + 3 = 5
// Example 2
//
// Input:
// List = [1,2,3,4,5]
// k = 3
// target = 6
// Output: 1
// Explanation: There is only one method. 1 + 2 + 3 = 6


public class Solution {
    /*
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }

        // my try: use K Sum II to get all results and get the size of result
        // List<List<Integer>> result = new ArrayList<>();
        // dfs(result, new ArrayList<Integer>(), A, k, target, 0);
        // return result.size();
        // but obviously, it would case a very deep stack and TLE

        // so asked how many solutions, we can try DP
        // 这里涉及到前几位数， 取几位数， 得到的和， 所以是三维数组
        // definition: f[i][j][k] = 前 i 位 取 j 个数和为 k 的方案个数
        int n = A.length;
        int[][][] f = new int[n + 1][k + 1][target + 1];
        // initialization
        for (int i = 0; i < n + 1; i++) {
            // 不管有多少个数（前几位）， 取 0 个 得到和为 0 的方案个数是 1， 就是什么都不取
            f[i][0][0] = 1;
        }
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                for (int t = 1; t < target + 1; t++) {
                    // at least, we have
                    f[i][j][t] = f[i - 1][j][t];
                    if (t - A[i - 1] >= 0) {
                        f[i][j][t] += f[i - 1][j - 1][t - A[i - 1]];
                    }
                }
            }
        }
        // result
        return f[n][k][target];
    }
    private void dfs(List<List<Integer>> result, List<Integer> list, int[] nums, int k, int target, int index) {
        if (list.size() == k) {
            if (getSum(list) == target) {
                // DEEP COPY!!!
                result.add(new ArrayList<Integer>(list));
                return;
            }
        }
        if (list.size() > k) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(result, list, nums, k, target, i + 1); // next start index is i + 1 rather than index + 1
            list.remove(list.size() - 1);
        }
    }
    private int getSum(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }
}
