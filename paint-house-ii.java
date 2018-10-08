// 516. Paint House II
// Description
// There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
//
// The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
//
// All costs are positive integers.
//
// Have you met this question in a real interview?
// Example
// Given n = 3, k = 3, costs = [[14,2,11],[11,14,5],[14,3,10]] return 10
//
// house 0 is color 2, house 1 is color 3, house 2 is color 2, 2 + 5 + 3 = 10
//
// Challenge
// Could you solve it in O(nk)?


public class Solution {
    /**
     * @param costs n x k cost matrix
     * @return an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        // Write your code here
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;
        // initialization
        int prevMin = 0;
        int prevSec = 0;
        int prevIndex = -1;
        // DP
        // we don't use a 2D array but variables to record min and 2nd min
        for (int i = 0; i < n; i++) {
            int currMin = Integer.MAX_VALUE;
            int currSec = Integer.MAX_VALUE;
            int currIndex = -1;
            for (int j = 0; j < k; j++) {
                // calculate cost at current position depending on if index is equal
                int cost = costs[i][j] + (j == prevIndex ? prevSec : prevMin);
                if (cost < currMin) {
                    // update min, 2nd min and index
                    currSec = currMin;
                    currMin = cost;
                    currIndex = j;
                } else if (cost < currSec) {
                    // cost is between min and 2nd min, then just update 2nd min
                    currSec = cost;
                    // don't need to update index, because index is always the index of min
                }
            }
            // before we go to next level, we should update preValue by currValue
            prevMin = currMin;
            prevSec = currSec;
            prevIndex = currIndex;
        }
        // result
        // be aware of currMin is defined in for loop, which cannot be got here
        return prevMin;
    }
}
