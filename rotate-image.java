// 161. Rotate Image
// 中文English
// You are given an n x n 2D matrix representing an image.
// Rotate the image by 90 degrees (clockwise).
//
// Example
// Example 1：
//
// Input:[[1,2],[3,4]]
// Output:[[3,1],[4,2]]
// Example 2:
//
// Input:[[1,2,3],[4,5,6],[7,8,9]]
// Output:[[7,4,1],[8,5,2],[9,6,3]]
// Challenge
// Do it in-place.
//


public class Solution {
    /*
     * @param matrix: a lists of integers
     * @return:
     */
    public void rotate(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int n = matrix.length;
        // challenge said we should do in-place, so we should find out the relation between 2 numbers to be swapped

        // method 1: get transpose matrix first, and then reverse each row
        // transpose(matrix);
        // reverseRow(matrix);

        // method 2: 沿着副对角线翻转， 再沿着中间一行上下翻转
        secDiaRotate(matrix);
        rowRotate(matrix);
    }
    // method 1:
    private void transpose(int[][] nums) {
        // 求转置矩阵相当于沿着主对角线翻转
        // i: [0, n - 1]; j: [i + 1, n - 1]; [i][j] <-> [j][i]
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // swap(nums[i][j], nums[j][i]); 这样传值并不会改变矩阵里面的值
                int temp = nums[i][j];
                nums[i][j] = nums[j][i];
                nums[j][i] = temp;
            }
        }
    }
    private void reverseRow(int[][] nums) {
        // reverse each row
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                // swap(nums[i][j], nums[i][n - 1 - j]);
                int temp = nums[i][j];
                nums[i][j] = nums[i][n - 1 -j];
                nums[i][n - 1 - j] = temp;
            }
        }
    }

    // method 2:
    private void secDiaRotate(int[][] nums) {
        // i: [0, n - 2]; j: [0, n - 2 - i]; [i][j] <-> [n - 1 - j][n - 1 - i]
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                int temp = nums[i][j];
                nums[i][j] = nums[n - 1 - j][n - 1 - i];
                nums[n - 1 - j][n - 1 - i] = temp;
            }
        }
    }
    private void rowRotate(int[][] nums) {
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = nums[i][j];
                nums[i][j] = nums[n - 1 - i][j];
                nums[n - 1 - i][j] = temp;
            }
        }
    }
}
