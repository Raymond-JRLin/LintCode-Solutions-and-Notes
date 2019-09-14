// 115. Unique Paths II
// 中文English
// Follow up for "Unique Paths":
//
// Now consider if some obstacles are added to the grids. How many unique paths would there be?
//
// An obstacle and empty space is marked as 1 and 0 respectively in the grid.
//
// Example
// Example 1:
// 	Input: [[0]]
// 	Output: 1
//
//
// Example 2:
// 	Input:  [[0,0,0],[0,1,0],[0,0,0]]
// 	Output: 2
//
// 	Explanation:
// 	Only 2 different path.
//
//
// Notice
// m and n will be at most 100.


public class Solution {
    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return -1;
        }
        // if (obstacleGrid[0][0] == 0) {
        //     return 1;
        // }
        // if (obstacleGrid.length == 1) {
        //     if (obstacleGrid[0][0] == 1) {
        //         return 1;
        //     } else {
        //         return 0;
        //     }
        // }
        // definition
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] f = new int[m][n];
        // initialization
        f[0][0] = 1;
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                f[i][0] = 1;
            } else {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 0) {
                f[0][i] = 1;
            } else {
                break;
            }
        }
        // answer
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                } else {
                    f[i][j] = 0;
                }
            }
        }
        return f[m - 1][n - 1];
    }
}
