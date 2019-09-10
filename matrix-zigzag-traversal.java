// 185. Matrix Zigzag Traversal
// 中文English
// Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in ZigZag-order.
//
// Example
// Example 1:
// 	Input: [[1]]
// 	Output:  [1]
//
// Example 2:
// 	Input:
// 	[
//     [1, 2,  3,  4],
//     [5, 6,  7,  8],
//     [9,10, 11, 12]
//   ]
//
// 	Output:  [1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]
//
//


public class Solution {
    /*
     * @param matrix: An array of integers
     * @return: An array of integers
     */
    public int[] printZMatrix(int[][] matrix) {
        // write your code here
        if (matrix == null) {
            return null;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[] result = new int[n * m];
        if (n == 0 || m == 0) {
            return result;
        }
        // put the first value in
        result[0] = matrix[0][0];
        int count = 1;
        // use dx and dy to control direction: left-down or right-up
        // initialize as right-up
        int dx = 1;
        int dy = -1;
        // initialize the starting position
        int x = 0;
        int y = 0;
        while (count < n * m) {
            if (x + dx >= 0 && y + dy >= 0 && x + dx < m && y + dy < n) {
                // keep going left-down or right-up
                x += dx;
                y += dy;
            } else {
                // touch the boundary, we need to decide which boundary we reached, up or left
                // reach upper or right bound
                if (dx == 1 && dy == -1) {
                    if (x + dx < m) {
                        // go right by one step first
                        x++;
                    } else {
                        // if we cannot go right then go down by one step
                        y++;
                    }
                    // change direction
                    dx = -1;
                    dy = 1;
                } else {
                    // reach left or bottom bound
                    if (y + dy < n) {
                        // go down first
                        y++;
                    } else {
                        // if we cannot go dwon, then go right by one step
                        x++;
                    }
                    // change direction
                    dx = 1;
                    dy = -1;
                }
            }
            // put into result
            result[count] = matrix[y][x];
            count++;
        }
        return result;
    }
};
