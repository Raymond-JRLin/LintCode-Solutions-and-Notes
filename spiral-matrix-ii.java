// 381. Spiral Matrix II
// 中文English
// Given an integer n, generate a square matrix filled with elements from 1 to n^2n
// ​2
// ​​  in spiral order.
//
// (The spiral rotates clockwise from the outside to the inside, referring to examples)
//
// Example
// Example 1:
//
// input: 2
// output:
// [
//   [1, 2],
//   [4, 3]
// ]
// Example 2:
//
// input: 3
// output:
// [
//   [ 1, 2, 3 ],
//   [ 8, 9, 4 ],
//   [ 7, 6, 5 ]
// ]


public class Solution {
    /**
     * @param n an integer
     * @return a square matrix
     */
    public int[][] generateMatrix(int n) {
        // Write your code here
        int level = n / 2;
        int val = 1;
        int[][] result = new int[n][n];
        if (n < 1) {
            return result;
        }
        // the sequence is:
        // -------->|   1 2 0   1 2 3   1 2 3   1 2 3
        // |        |   0 0 0   0 0 4   0 0 4   0 0 4
        // |        |   0 0 0
        // |        |
        // |<--------
        for (int i = 0; i < level; i++) {
            // go right (upper line)
            for (int j = i; j < n - 1 - i; j++) {
                result[i][j] = val;
                val++;
            }
            // go down (right line)
            for (int j = i; j < n - 1 - i; j++) {
                result[j][n - 1 - i] = val;
                val++;
            }
            // go left (lower line)
            for (int j = n - 1 - i; j > i; j--) {
                result[n - 1 - i][j] = val;
                val++;
            }
            // go up (left line)
            for (int j = n - 1 - i; j > i; j--) {
                result[j][i] = val;
                val++;
            }
        }
        // finally, if n is and odd number, result[n / 2][n / 2] would be empty
        // if it's even number then there is no one row/column left so no need to resign
        if (n % 2 == 1) {
            result[level][level] = val;
        }
        return result;
    }
}
