// 826. Computer Maintenance
// 中文English
// A n*m matrix represents an array of computers, and give a list which represents the coordinate of the broken computer. Now we start with (0,0) and repair the computer. There are some request:
//
// You have to fix all the broken computers in the current line to get to the next line.
// If you are going to the next line, the mechanic must first return to the far left or right of the line.
// Find the minimum access distance.
// Example
// Example 1
//
// Input:
// n = 3
// m = 10
// list = [[0,0],[0,9],[1,7]]
// Output: 15
// Explanation:
// Starting from (0,0), fix 0, then go to (0,9) to fix 1 and go from (0,9) to next line (1,9), then go to (1,7) to fix 3, then go back to (1,9) and go to (2,9).
// Example 2
//
// Input:
// n = 3
// m = 10
// list = [[0,3],[1,7],[1,2]]
// Output: 17
// Explanation:
// Starting from (0,0), go to (0,3) and fix 0, then go back to (0,0) to next line (1,0), and go to (1,2) to fix 2, then go to (1,7) to fix 1, then go to (1,9), and end at (2,9).
// Notice
// The size of the given matrix is n x m, n <= 200, m <= 200.
// num is the number of broken computer, num <= 1000.
// After fixing the last computer, you need to return to the far left or right of the last line.


public class Solution {
    /**
     * @param n: the rows of matrix
     * @param m: the cols of matrix
     * @param Badcomputers: the bad computers
     * @return: The answer
     */
    public int maintenance(int n, int m, Point[] Badcomputers) {
        // Write your code here
        if (Badcomputers == null || Badcomputers.length == 0) {
            return n + m - 2;
        }

        return method1(n, m, Badcomputers);
    }

    private int method1(int n, int m, Point[] points) {
        int[][] nums = build(n, m, points); // build matrix with 1 representing bad computer
        // defintion
        int[][] f = new int[n][2]; // f[i][j] = 完成 i 行后停在最左边 j = 0 和 最右边 j = 1 的最少步数
        // initialization
        int left = -1;
        int right = -1;
        for (int j = 0; j < m; j++) {
            if (nums[0][j] == 1) {
                // left 和 right 是相对的， left 是从右向左看最左边的， right 是从左往右看最右边的
                left = Math.max(left, m - 1 - j);
                right = Math.max(right, j);
            }
        }
        if (left == -1) {
            //
            f[0][0] = 0;
            f[0][1] = m - 1;
        } else {
            f[0][0] = 2 * right;
            f[0][1] = m - 1;
        }
        // DP
        for (int i = 1; i < n; i++) {
            left = -1;
            right = -1;
            for (int j = 0 ; j < m; j++) {
                if (nums[i][j] == 1) {
                    left = Math.max(left, m - 1 - j);
                    right = Math.max(right, j);
                }
                if (left == -1) {
                    //
                    f[i][0] = Math.min(f[i - 1][0], f[i - 1][1] + m - 1) + 1;
                    f[i][1] = Math.min(f[i - 1][1], f[i - 0][1] + m - 1) + 1;
                    // f[i][0] = f[i - 1][0] + 1;
                    // f[i][1] = f[i - 1][1] + 1;
                } else {
                    f[i][0] = Math.min(f[i - 1][0] + 2 * right, f[i - 1][1] + m - 1) + 1;
                    f[i][1] = Math.min(f[i - 1][0] + m - 1, f[i - 1][1] + 2 * left) + 1;
                }
            }
        }
        // result
        return Math.min(f[n - 1][0], f[n - 1][1]);
    }
    private int[][] build(int n, int m, Point[] points) {
        int[][] nums = new int[n][m];
        for (int i = 0; i < points.length; i++) {
            nums[points[i].x][points[i].y] = 1;
        }
        return nums;
    }
}
