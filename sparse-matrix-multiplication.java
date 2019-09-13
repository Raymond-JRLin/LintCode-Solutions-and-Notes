// 654. Sparse Matrix Multiplication
// 中文English
// Given two Sparse Matrix A and B, return the result of AB.
//
// You may assume that A's column number is equal to B's row number.
//
// Example
// Example1
//
// Input:
// [[1,0,0],[-1,0,3]]
// [[7,0,0],[0,0,0],[0,0,1]]
// Output:
// [[7,0,0],[-7,0,3]]
// Explanation:
// A = [
//   [ 1, 0, 0],
//   [-1, 0, 3]
// ]
//
// B = [
//   [ 7, 0, 0 ],
//   [ 0, 0, 0 ],
//   [ 0, 0, 1 ]
// ]
//
//
//      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
// AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
//                   | 0 0 1 |
// Example2
//
// Input:
// [[1,0],[0,1]]
// [[0,1],[1,0]]
// Output:
// [[0,1],[1,0]]


public class Solution {
    /**
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = A[0].length;
        int k = B[0].length;

        int[][] C = new int[n][k];

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (A[i][j] != 0)
                    for (int l = 0; l < k; ++l)
                        C[i][l] += A[i][j] * B[j][l];
        return C;
    }
}
