// 515. Paint House
// 中文English
// There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color, and you need to cost the least. Return the minimum cost.
//
// The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
//
// Example
// Example 1:
//
// Input: [[14,2,11],[11,14,5],[14,3,10]]
// Output: 10
// Explanation: blue green blue, 2 + 5 + 3 = 10
// Example 2:
//
// Input: [[1,2,3],[1,4,6]]
// Output: 3
// Notice
// All costs are positive integers.
//


public class Solution {
    /**
     * @param costs n x 3 cost matrix
     * @return an integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        // Write your code here
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        // if (costs[0].length == 1) {
        //     return Math.min(Math.min(costs[0][0], costs[1][0]), costs[2]);
        // }
        int n = costs.length;
        // definition
        int[][] f = new int[n][3];
        // initialization
        for (int i = 0; i < 3; i++) {
            f[0][i] = costs[0][i];
        }
        // DP
        for (int i = 1; i < n; i++) {
            f[i][0] = costs[i][0] + Math.min(f[i - 1][1], f[i - 1][2]);
            f[i][1] = costs[i][1] + Math.min(f[i - 1][0], f[i - 1][2]);
            f[i][2] = costs[i][2] + Math.min(f[i - 1][0], f[i - 1][1]);
        }
        // result
        return Math.min(f[n - 1][0], Math.min(f[n - 1][1], f[n - 1][2]));
    }
}
