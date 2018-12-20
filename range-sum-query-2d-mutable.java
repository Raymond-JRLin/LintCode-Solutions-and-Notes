// 817. Range Sum Query 2D - Mutable
// Description
// Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
//
// 1.The matrix is only modifiable by the update function.
// 2.You may assume the number of calls to update and sumRegion function is distributed evenly.
// 3.You may assume that row1 ≤ row2 and col1 ≤ col2.
//
// Have you met this question in a real interview?
// Example
// Given matrix = [
//   [3, 0, 1, 4, 2],
//   [5, 6, 3, 2, 1],
//   [1, 2, 0, 1, 5],
//   [4, 1, 0, 1, 7],
//   [1, 0, 3, 0, 5]
// ]
// 
// sumRegion(2, 1, 4, 3) -> 8
// update(3, 2, 2)
// sumRegion(2, 1, 4, 3) -> 10


class NumMatrix {

    private int[][] rowPrefix;
    private int n;
    private int m;


    // brute force 的 O(N ^ 2) time 通不过， 每次都去重新求 sum 非常 heavy
    // 答案给了 Binary Indexed Tree - 2D 版本， 下次复习好了 BIT 再来
    // 这里参考了其他同学的答案， O(N) time for update and get sum, 预存/每次改变【每一行】的 prefix sum， 存每一行的 prefix sum 对于更新 value 会很方便， 要注意当 col 为 0 时候的处理

    public NumMatrix(int[][] matrix) {
        n = matrix.length;
        m = matrix[0].length;
        rowPrefix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowPrefix[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                rowPrefix[i][j] += rowPrefix[i][j - 1];
            }
        }
    }

    public void update(int row, int col, int val) {
        int diff = (col == 0 ?
            val - rowPrefix[row][col] : val - (rowPrefix[row][col] - rowPrefix[row][col - 1]));
        for (int j = col; j < m; j++) {
            rowPrefix[row][j] += diff;
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += rowPrefix[i][col2] - (col1 == 0 ? 0 : rowPrefix[i][col1 - 1]);
        }
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
