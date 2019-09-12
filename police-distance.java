// 1367. Police Distance
// 中文English
// Given a matrix size of n x m, element 1 represents policeman, -1 represents wall and 0 represents empty.
// Now please output a matrix size of n x m, output the minimum distance between each empty space and the nearest policeman
//
// Example
// Example1
//
// Input:
// mat =
// [
//     [0, -1, 0],
//     [0, 1, 1],
//     [0, 0, 0]
// ]
// Output: [[2,-1,1],[1,0,0],[2,1,1]]
// Explanation:
// The distance between the policeman and himself is 0, the shortest distance between the two policemen to other empty space is as shown above
// Example2
//
// Input:
// mat =
// [
//     [0, -1, -1],
//     [0, -1, 1],
//     [0, 0, 0]
// ]
// Output: [[5,-1,-1],[4,-1,0],[3,2,1]]
// Explanation:
// The shortest distance between the policemen to other 5 empty space is as shown above.
// Notice
// Given a matrix size of n x m， n <= 200，m <= 200.
// We guarantee that each empty space can be reached by one policeman at least.


public class Solution {
    /**
     * @param matrix : the martix
     * @return: the distance of grid to the police
     */
    public int[][] policeDistance(int[][] matrix ) {
        // Write your code here

        // return mytry(matrix);

        return method2(matrix);
    }

    private int[][] method2(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] result = new int[n][m];
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == WALL) {
                    result[i][j] = WALL;
                } else if (matrix[i][j] == POLICE) {
                    result[i][j] = 0;
                    queue.offer(new Node(i, j, 0));
                } else {
                    // initialization, otherwise all are 0 then would not be updated
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        // BFS for all starts
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = curr.x + dx[i];
                int y = curr.y + dy[i];
                int dis = curr.dis + 1; // get 1 more step
                // invalid position
                if (x < 0 || x >= n || y < 0 || y >= m) {
                    continue;
                }
                // no need to update
                if (matrix[x][y] == WALL || matrix[x][y] == POLICE) {
                    continue;
                }
                // udpate with smaller distance
                if (dis < result[x][y]) {
                    result[x][y] = dis;
                    queue.offer(new Node(x, y, dis));
                }
            }
        }
        return result;
    }
    private class Node{
        public int x;
        public int y;
        public int dis; // nearest distance
        public Node(int i, int j, int d) {
            x = i;
            y = j;
            dis = d;
        }
    }

    public final int WALL = -1;
    public final int EMPTY = 0;
    public final int POLICE = 1;
    private int[][] mytry(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == WALL) {
                    result[i][j] = WALL;
                } else if (matrix[i][j] == POLICE) {
                    result[i][j] = 0;
                    bfs(matrix, i, j, result);
                }
            }
        }
        return result;
    }
    public final int[] dx = {0, 0, 1, -1};
    public final int[] dy = {1, -1, 0, 0};
    private void bfs(int[][] matrix, int row, int col, int[][] result) {
        int n = matrix.length;
        int m = matrix[0].length;
        Queue<Coordinator> queue = new LinkedList<>();
        Coordinator root = new Coordinator(row, col);
        queue.offer(root);
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Coordinator curr = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int x = curr.x + dx[j];
                    int y = curr.y + dy[j];
                    if (x < 0 || x >= n || y < 0 || y >= m) {
                        continue;
                    }
                    if (matrix[x][y] == WALL || matrix[x][y] == POLICE) {
                        continue;
                    }
                    if (result[x][y] == 0 || step < result[x][y]) {
                        //
                        result[x][y] = step;
                        queue.offer(new Coordinator(x, y));
                    }
                }
            }
        }
    }
    private class Coordinator{
        public int x;
        public int y;
        public Coordinator(int i, int j) {
            x = i;
            y = j;
        }
    }
}
