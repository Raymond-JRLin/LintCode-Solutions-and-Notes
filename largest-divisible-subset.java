// 603. Largest Divisible Subset
// 中文English
// Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
//
// Example
// Example 1:
//
// Input: nums =  [1,2,3],
// Output: [1,2] or [1,3]
// Example 2:
//
// Input: nums = [1,2,4,8],
// Output: [1,2,4,8]
// Notice
// If there are multiple solutions, return any subset is fine.
//


public class Solution {
    /**
     * @param nums a set of distinct positive integers
     * @return the largest subset
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return null;
        }
        List<Integer> results = new ArrayList<Integer>();
        int n = nums.length;
        // definition
        int[] f = new int[n];
        int[] pre = new int[n]; // record how can we get this f[]
        // initialization
        for (int i = 0; i < n; i++) {
            f[i] = 1;
            pre[i] = i;
        }
        Arrays.sort(nums);
        // DP
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && f[j] + 1 > f[i]) {
                    f[i] = f[j] + 1;
                    pre[i] = j;
                }
            }
        }
        // find the longest path in f[] and what does it come from in pre[]
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] > max) {
                max = f[i];
                maxIndex = i;
            }
        }
        // answer
        while (maxIndex != pre[maxIndex]) {
            results.add(nums[maxIndex]);
            maxIndex = pre[maxIndex];
        }
        results.add(nums[maxIndex]);
        // Collections.reverse(results);
        return results;
    }
}
