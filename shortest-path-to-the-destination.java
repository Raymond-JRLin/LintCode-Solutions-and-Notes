// 1563. Shortest path to the destination
// 中文English
// Given a 2D array representing the coordinates on the map, there are only values 0, 1, 2 on the map. value 0 means that it can pass, value 1 means not passable, value 2 means target place. Starting from the coordinates [0,0],You can only go up, down, left and right. Find the shortest path that can reach the destination, and return the length of the path.
//
// Example
// Example 1
//
// Input:
// [
//  [0, 0, 0],
//  [0, 0, 1],
//  [0, 0, 2]
// ]
// Output: 4
// Explanation: [0,0] -> [1,0] -> [2,0] -> [2,1] -> [2,2]
// Example 2
//
// Input:
// [
//     [0,1],
//     [0,1],
//     [0,0],
//     [0,2]
// ]
// Output: 4
// Explanation: [0,0] -> [1,0] -> [2,0] -> [3,0] -> [3,1]
// Notice
// 1.The map must exist and is not empty, there is only one target
//


public class Solution {
    /**
     * @param targetMap:
     * @return: nothing
     */
    public int shortestPath(int[][] targetMap) {
        // Write your code here
        if (targetMap == null || targetMap.length == 0 || targetMap[0].length == 0) {
            return -1;
        }
        if (targetMap[0][0] == 1) {
            return -1;
        }
        if (targetMap[0][0] == 2) {
            return 0;
        }

        return mytry(targetMap);
    }

    private int mytry(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int result = 0;
        while (!queue.isEmpty()) {
            result++;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int[] curr = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int x = curr[0] + dx[i];
                    int y = curr[1] + dy[i];
                    if (x < 0 || x >= n || y < 0 || y >= m) {
                        continue;
                    }
                    if (visited[x][y]) {
                        continue;
                    }
                    if (matrix[x][y] == 2) {
                        return result;
                    }
                    if (matrix[x][y] == 1) {
                        continue;
                    }
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return -1;
    }
}
