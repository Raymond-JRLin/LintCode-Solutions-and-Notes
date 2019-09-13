// 162. Set Matrix Zeroes
// 中文English
// Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
//
// Example
// Example 1:
//
// Input:[[1,2],[0,3]]
// Output:[[0,2],[0,0]]
// Example 2:
//
// Input:[[1,2,3],[4,0,6],[7,8,9]]
// Output:[[1,0,3],[0,0,0],[7,0,9]]
// Challenge
// Did you use extra space?
// A straight forward solution using O(mn) space is probably a bad idea.
// A simple improvement uses O(m + n) space, but still not the best solution.
// Could you devise a constant space solution?
//


public class Solution {
    /*
     * @param matrix: A lsit of lists of integers
     * @return:
     */
    public void setZeroes(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int n = matrix.length;
        int m = matrix[0].length;

        // method 1: use anther matrix to record 0s, O(nm) space
        // int[][] nums = new int[n][m];
        // // initialization
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         nums[i][j] = -1;
        //     }
        // }
        // // check which position is 0
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         if (matrix[i][j] == 0) {
        //             for (int k = 0; k < n; k++) {
        //                 nums[k][j] = 0;
        //             }
        //             for (int k = 0; k < m; k++) {
        //                 nums[i][k] = 0;
        //             }
        //         }
        //     }
        // }
        // // change original matrix to 0 in applicable position
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         if (nums[i][j] == 0) {
        //             matrix[i][j] = 0;
        //         }
        //     }
        // }

        // method 2: O(n + m) space with 2 arrays with length of n and m to record which rows and columns to be set 0 respectively
        // int[] rows = new int[n];
        // int[] cols = new int[m];
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         if (matrix[i][j] == 0) {
        //             rows[i] = 1;
        //             cols[j] = 1;
        //         }
        //     }
        // }
        // for (int i = 0; i < n; i++) {
        //     if (rows[i] == 1) {
        //         for (int j = 0; j < m; j++) {
        //             matrix[i][j] = 0;
        //         }
        //     }
        // }
        // for (int j = 0; j < m; j++) {
        //     if (cols[j] == 1) {
        //         for (int i = 0; i < n; i++) {
        //             matrix[i][j] = 0;
        //         }
        //     }
        // }

        // method 3: O(1) space using the 1st row and col record situations
        // check 1st row and col first
        boolean rowZero = false;
        boolean colZero = false;
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == 0) {
                rowZero = true;
            }
        }
        for (int j = 0; j < m; j++) {
            if (matrix[0][j] == 0) {
                colZero = true;
            }
        }
        // check if other positions are 0 except the 1st row and col, and record the result in the 1st row and col
        for (int i = 1; i < n; i++) {
            for (int j = 1; j< m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // use the above result to reset those position to 0
        for (int i = 1; i < n; i++) {
            for (int j = 1; j< m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // reset the 1st row and col if necessary
        if (rowZero) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }
        if (colZero) {
            for (int j = 0; j < m; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}
