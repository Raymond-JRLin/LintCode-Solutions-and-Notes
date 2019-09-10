// 1410. Matrix Water Injection
// 中文English
// Given a two-dimensional matrix, the value of each grid represents the height of the terrain. The flow of water will only flow up, down, right and left, and it must flow from the high ground to the low ground. As the matrix is surrounded by water, it is now filled with water from (R,C) and asked if water can flow out of the matrix.
//
// Example
// Example1
//
// Input:
// mat =
// [
//     [10,18,13],
//     [9,8,7],
//     [1,2,3]
// ] and R = 1, C = 1
// Output: "YES"
// Explanation:
// (1,1) →(1,2)→Outflow.
// Example2
//
// Input:
// mat =
// [
//     [10,18,13],
//     [9,7,8],
//     [1,11,3]
// ] and R = 1, C = 1
// Output: "NO"
// Explanation:
// Since (1,1) cannot flow to any other grid, it cannot flow out.
// Notice
// The input matrix size is n x n, n <= 200.
// Ensure that each height is a positive integer.


public class Solution {
    /**
     * @param matrix: the height matrix
     * @param R: the row of (R,C)
     * @param C: the columns of (R,C)
     * @return: Whether the water can flow outside
     */
    public String waterInjection(int[][] matrix, int R, int C) {
        // Write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return "NO";
        }

        return mytry(matrix, R, C);
    }

    // 思路点拨：从(R,C)开始DFS，看是否能碰到边界。返回YES或者NO。

    // 考点分析：简单的热身搜索，一定要做到bugfree，注意水是从高处向低处流动

    private String mytry(int[][] matrix, int R, int C) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{R, C});
        boolean result = false;
        boolean[][] visited = new boolean[n][m];
        visited[R][C] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = curr[0] + dx[i];
                int y = curr[1] + dy[i];
                if (x < 0 || x >= n || y < 0 || y >= m) {
                    return "YES";
                }
                if (visited[x][y]) {
                    continue;
                }
                if (matrix[x][y] >= matrix[curr[0]][curr[1]]) {
                    continue;
                }
                queue.offer(new int[]{x, y});
                visited[x][y] = true;
            }
            if (result) {
                break;
            }
        }

        return "NO";
    }
}
