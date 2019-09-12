// 665. Range Sum Query 2D - Immutable
// 中文English
// Given a 2D matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
//
// Example
// Example1
//
// Input:
// [[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]
// sumRegion(2, 1, 4, 3)
// sumRegion(1, 1, 2, 2)
// sumRegion(1, 2, 2, 4)
// Output:
// 8
// 11
// 12
// Explanation:
// Given matrix =
// [
//   [3, 0, 1, 4, 2],
//   [5, 6, 3, 2, 1],
//   [1, 2, 0, 1, 5],
//   [4, 1, 0, 1, 7],
//   [1, 0, 3, 0, 5]
// ]
// sumRegion(2, 1, 4, 3) = 2 + 0 + 1 + 1 + 0 + 1 + 0 + 3 + 0 = 8
// sumRegion(1, 1, 2, 2) = 6 + 3 + 2 + 0 = 11
// sumRegion(1, 2, 2, 4) = 3 + 2 + 1 + 0 + 1 + 5 = 12
// Example2
//
// Input:
// [[3,0],[5,6]]
// sumRegion(0, 0, 0, 1)
// sumRegion(0, 0, 1, 1)
// Output:
// 3
// 14
// Explanation:
// Given matrix =
// [
//   [3, 0],
//   [5, 6]
// ]
// sumRegion(0, 0, 0, 1) = 3 + 0 = 3
// sumRegion(0, 0, 1, 1) = 3 + 0 + 5 + 6 = 14
// Notice
// You may assume that the matrix does not change.
// There are many calls to sumRegion function.
// You may assume that row1 ≤ row2 and col1 ≤ col2.


public class NumMatrix {

    int[][] f;

    /*
    * @param matrix: a 2D matrix
    */public NumMatrix(int[][] matrix) {
        // do intialization if necessary

        // int n = matrix.length;
        // int m = matrix[0].length;
        // region = new int[n][m];
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         region[i][j] = matrix[i][j];
        //     }
        // }

        // definition: f[i][j] = sum of rectangle ending with  (i, j) - lower right corner
        int n = matrix.length;
        int m = matrix[0].length;
        f = new int[n][m];
        // initialization
        f[0][0]= matrix[0][0];
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < m; i++) {
            f[0][i] = f[0][i - 1] + matrix[0][i];
        }
        // DP
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1] - f[i - 1][j - 1] + matrix[i][j];
            }
        }
        // result
        // put result in next method to calculate

        // still MLE!!! =.=
    }

    /*
     * @param row1: An integer
     * @param col1: An integer
     * @param row2: An integer
     * @param col2: An integer
     * @return: An integer
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // write your code here
        int sum = 0;

        // my try: simple 2 for loops
        // for (int i = row1; i <= row2; i++) {
        //     for (int j = col1; j <= col2; j++) {
        //         sum += region[i][j];
        //     }
        // }
        // return sum;
        // passed 77% and MLE

        // 2nd try: DP

        // originally, I create a region[][] as method 1, and still MLE, so we should not copy that matrix do calculation here, but do in that method with original given matrix

        // we can also initialize f[n + 1][m + 1] and f[0][i] = f[i][0] = 0, then we can simplify dealing with such different cases
        if (row1 == 0 && col1 == 0) {
            return f[row2][col2];
        } else if (row1 == 0) {
            return f[row2][col2] - f[row2][col1 - 1];
        } else if (col1 == 0) {
            return f[row2][col2] - f[row1 - 1][col2];
        } else {
            return f[row2][col2] - f[row2][col1 - 1] - f[row1 - 1][col2] + f[row1 - 1][col1 - 1];
        }
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
