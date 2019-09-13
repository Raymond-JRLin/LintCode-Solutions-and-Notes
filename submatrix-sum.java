// 405. Submatrix Sum
// 中文English
// Given an integer matrix, find a submatrix where the sum of numbers is zero. Your code should return the coordinate of the left-up and right-down number.
//
// If there are multiple answers, you can return any of them.
//
// Example
// Example 1:
//
// Input:
// [
//   [1, 5, 7],
//   [3, 7, -8],
//   [4, -8 ,9]
// ]
// Output: [[1, 1], [2, 2]]
// Example 2:
//
// Input:
// [
//   [0, 1],
//   [1, 0]
// ]
// Output: [[0, 0], [0, 0]]
// Challenge
// O(n3) time.
//


public class Solution {
    /*
     * @param matrix: an integer matrix
     * @return: the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        // write your code here
        int[][] result = new int[2][2];
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        // similar to get the sum matrix in Range Sum Query 2D - Immutable, and then do 2 sum to find if there are 2 points with the same sum
        // definition: f[i][j] = sum of rectangle ending with  (i, j) - right-down corner
        int n = matrix.length;
        int m = matrix[0].length;
        // int[][] f = new int[n][m]; wrong case: [[2,-2],[-4,4]]
        // 注意1: 这种初始化长度是错误的， 因为在下面求 diff 的过程中， 如果没有设置第一行第一列为 0 的话， 其实是求不到原 matrix 中的第一行的值的， 因为需要 第一行 - 0， 而设置相同长度的话， 所有的 diff 最开始都是减去第一行， 比如一个 1 * n 的一个矩阵， 那么 f 矩阵中只有一行元素， 所以连下面的三重循环都进不去就直接 return result 了
        // 所以如果答案可能存在第一行中， 那就要设置一行一列为 0 的， 如果说题目给出的条件可以推断出最终结果一定不会再第一行上， 那么不写也没有关系， 不过一般的子矩阵问题都是会写上
        int[][] f = new int[n + 1][m + 1];
        // initialization
        f[0][0] = 0;
        for (int i = 1; i < n + 1; i++) {
            f[i][0] = 0;
        }
        for (int j = 1; j < m + 1; j++) {
            f[0][j] = 0;
        }
        // DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1] - f[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        // result
        for (int low = 0; low < n; low++) {
            for (int high = low + 1; high < n + 1; high++) {
                // 注意2: map 要在这里 new， 因为每次都是针对新的两栏（low - high） 之间进行扫描
                Map<Integer, Integer> map = new HashMap<>();
                for (int col = 0; col < m + 1; col++) {
                    int diff = f[high][col] - f[low][col];
                    if (map.containsKey(diff)) {
                        // found: 在纸上画个图比较好理解， 相当于两个 row 夹出一横片区域， 然后用 col 从左向右扫描， 若差值相等， 说明前后两个 col 所夹的区域和为 0， 此时注意求和的区域的对角线两点的坐标， 左上角不包含， 而右下角包含
                        result[0][0] = low;
                        result[0][1] = map.get(diff);
                        result[1][0] = high - 1;
                        result[1][1] = col - 1;
                        // 注意3: 得到就及时返回， 无需全部扫完， 否则 TLE
                        return result;
                    } else {
                        map.put(diff, col);
                    }
                }
            }
        }
        return result;
    }
}
