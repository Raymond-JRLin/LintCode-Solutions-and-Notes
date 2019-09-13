// 374. Spiral Matrix
// 中文English
// Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
//
// Example
// Example 1:
//
// Input:	[[ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ]]
// Output: [1,2,3,6,9,8,7,4,5]
// Example 2
//
// Input:	[[ 6,4,1 ], [ 7,8,9 ]]
// Output: [6,4,1,9,8,7]


public class Solution {
    /*
     * @param : a matrix of m x n elements
     * @return: an integer list
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        // write your code here
        if (matrix == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int n = matrix.length;
        int m = matrix[0].length;

        // method 1
        // reference:
        // http://www.cnblogs.com/EdwardLiu/p/4018225.html
        // http://bangbingsyb.blogspot.com/2014/11/leetcode-spiral-matrix-i-ii.html
        // 把一圈按照上、右、下、左的顺序加入，会发现最终到达的中心只有一半
        // int level = (Math.min(n, m)) / 2;
        // for (int i = 0; i < level; i++) {
        //     // go right (upper line)
        //     for (int j = i; j < m - 1 - i; j++) {
        //         result.add(matrix[i][j]);
        //     }
        //     // go down (right line)
        //     for (int j = i; j < n - 1 - i; j++) {
        //         result.add(matrix[j][m - 1 - i]);
        //     }
        //     // go left (lower line)
        //     for (int j = m - 1 - i; j > i; j--) {
        //         result.add(matrix[n - 1 - i][j]);
        //     }
        //     // go up (left line)
        //     for (int j = n - 1 - i; j > i; j--) {
        //         result.add(matrix[j][i]);
        //     }
        // }
        // // 比如：3*4矩阵，螺旋只有一层，但中间还有一行元素没有没加入。所以最后要将剩余的走完。
        // if (Math.min(n, m) % 2 == 1) {
        //     if (n > m) {
        //         for (int i = level; i < n - level; i++) {
        //             result.add(matrix[i][level]);
        //         }
        //     } else {
        //         for (int i = level; i < m - level; i++) {
        //             result.add(matrix[level][i]);
        //         }
        //     }
        // }
        // return result;

        // method 2: reference and NC solution
        // reference: https://segmentfault.com/a/1190000004594397
        // use while loop to spin
        int level = 0;
        while (2 * level < n && 2 * level < m) {
            // go right
            for (int i = level; i <= m - 1 - level; i++) {
                result.add(matrix[level][i]);
            }
            // go down
            for (int i = level + 1; i <= n - 1 - level; i++) {
                // + 1 because when we go right we already added the upper position
                result.add(matrix[i][m - 1 - level]);
            }
            // since we may leave one colomn or row, so we can check here after last row/column added by above for loops
            // if (2 * level + 1 == n || 2 * level + 1 == m) {
            //     break;
            // }
            // if we use another format, it would be easier to understand
            if (n - 2 * level == 1 || m - 2 * level == 1) {
                // only 1 colomn or row left, and we added above
                break;
            }
            // go left
            for (int i = m - 2 - level; i >= level; i--) {
                result.add(matrix[n - 1 - level][i]);
            }
            // go up
            for (int i = n - 2 - level; i >= level + 1; i--) {
                // >= + 1 because avoid last circle
                result.add(matrix[i][level]);
            }
            // we did a whole circle
            level++;
        }
        return result;
    }
};
