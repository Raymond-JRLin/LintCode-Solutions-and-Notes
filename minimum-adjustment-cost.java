// 91. Minimum Adjustment Cost
// 中文English
// Given an integer array, adjust each integers so that the difference of every adjacent integers are not greater than a given number target.
//
// If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|
//
// Example
// Example 1:
// 	Input:  [1,4,2,3], target=1
// 	Output:  2
//
// Example 2:
// 	Input:  [3,5,4,7], target=2
// 	Output:  1
//
// Notice
// You can assume each number in the array is a positive integer and not greater than 100.
//


public class Solution {
    /*
     * @param A: An integer array
     * @param target: An integer
     * @return: An integer
     */
    public int MinAdjustmentCost(List<Integer> A, int target) {
        // write your code here
        if (A == null || A.size() == 0) {
            return -1;
        }
        int n = A.size();
        // use DP
        // definition: f[i][j] means min cost of changing (i + 1)th number to j
        int[][] f = new int[n][101];
        // initialization
        for (int j = 1; j < 101; j++) {
            f[0][j] = Math.abs(j - A.get(0));
        }
        // DP
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < 101; j++) {
                f[i][j] = Integer.MAX_VALUE;
                for (int k = 1; k < 101; k++) {
                    if (Math.abs(j - k) <= target) {
                        f[i][j] = Math.min(f[i][j], f[i - 1][k] + Math.abs(j - A.get(i)));
                    }
                }
            }
        }
        // result
        int result = Integer.MAX_VALUE;
        for (int j = 1; j < 101; j++) {
            result = Math.min(result, f[n - 1][j]);
        }
        return result;
    }
}
