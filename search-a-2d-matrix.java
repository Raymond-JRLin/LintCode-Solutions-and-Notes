// 28. Search a 2D Matrix
// 中文English
// Write an efficient algorithm that searches for a value in an m x n matrix.
//
// This matrix has the following properties:
//
// Integers in each row are sorted from left to right.
// The first integer of each row is greater than the last integer of the previous row.
// Example
// Example 1:
// 	Input:  [[5]],2
// 	Output: false
//
// 	Explanation:
// 	false if not included.
//
// Example 2:
// 	Input:  [
//     [1, 3, 5, 7],
//     [10, 11, 16, 20],
//     [23, 30, 34, 50]
// ],3
// 	Output: true
//
// 	Explanation:
// 	return true if included.
// Challenge
// O(log(n) + log(m)) time
//


public class Solution {
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || matrix[0][0] > target || matrix[matrix.length-1][matrix[0].length-1] == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        if (matrix[0][0] == target || matrix[n-1][m-1] == target) {
            return true;
        }
        int start = 0;
        int end = n * m - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int midx = mid / m;
            int midy = mid % m;
            if (matrix[midx][midy] < target) {
                start = mid;
            } else if (matrix[midx][midy] > target) {
                end = mid;
            } else if (matrix[midx][midy] == target) {
                return true;
            }
        }
        return false;
    }
}
