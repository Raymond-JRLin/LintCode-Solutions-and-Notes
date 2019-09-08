// 897. Island City
// 中文English
// Given a matrix of size n x m, the elements in the matrix are 0、1、2. 0 for the sea, 1 for the island, and 2 for the city on the island(You can assume that 2 is built on 1, ie 2 also represents the island).
// If two 1 are adjacent, then these two 1 belong to the same island. Find the number of islands with at least one city.
//
// Example
// Example1
//
// Input:
// [
// [1,1,0,0,0],
// [0,1,0,0,1],
// [0,0,0,1,1],
// [0,0,0,0,0],
// [0,0,0,0,1]
// ]
// Output: 0
// Explanation:
// There are 3 islands, but none of them contain cities.
// Example2
//
// Input:
// [
// [1,1,0,0,0],
// [0,1,0,0,1],
// [0,0,2,1,2],
// [0,0,0,0,0],
// [0,0,0,0,2]
// ]
// Output: 2
// Explanation:
// There are 3 islands, and two of them have cities.
// Notice
// We only consider up, down, left and right as adjacent.
// n <= 100，m <= 100.
// You can assume that the four sides of the matrix are surrounded by the sea.


public class Solution {
    /**
     * @param grid: an integer matrix
     * @return: an integer
     */
    public int numIslandCities(int[][] grid) {
        // Write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // return mytry(grid);

        // return mytry2(grid);

        // return method2(grid);

        return method2_2(grid);
    }
    private int method2_2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    // same idea but use DFS/recursion to flood
                    count += dfs2(grid, i, j);
                }
            }
        }
        return count;
    }
    private int dfs2(int[][] grid, int i, int j) {
        int result = 1;
        for (int k = 0; k < 4; k++) {
            int x = i + dir[k][0];
            int y = j + dir[k][1];
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                continue;
            }
            if (grid[x][y] == 0) {
                continue;
            }
            grid[x][y] = 0;
            result += dfs2(grid, x, y);
        }

        return result == 0 ? 0 : 1;
    }

    private int method2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    // same idea but use DFS/recursion to flood
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
    public int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private void dfs(int[][] grid, int i, int j) {
        for (int k = 0; k < 4; k++) {
            int x = i + dir[k][0];
            int y = j + dir[k][1];
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                continue;
            }
            if (grid[x][y] == 0) {
                continue;
            }
            grid[x][y] = 0;
            dfs(grid, x, y);
        }
    }

    private int mytry2(int[][] grid) {
        // we don't use visited array but mark visited as 0 in original given array
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    count++;
                    bfs2(grid, i, j);
                }
            }
        }
        return count;
    }
    private void bfs2(int[][] grid, int i, int j) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<Coordinator> queue = new LinkedList<>();
        Coordinator root = new Coordinator(i, j);
        queue.offer(root);
        while (!queue.isEmpty()) {
            Coordinator curr = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = curr.x + dx[k];
                int y = curr.y + dy[k];
                if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                    continue;
                }
                if (grid[x][y] == 0) {
                    continue;
                }
                grid[x][y] = 0;
                queue.offer(new Coordinator(x, y));
            }
        }
    }

    private int mytry(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == 2) {
                    // everytime we found a unvisted 2, then there's must be an island with city, then we mark all connected islands and cities as visited
                    count++;
                    bfs(grid, i, j, visited);
                }
            }
        }
        return count;
    }
    private void bfs(int[][] grid, int i, int j, boolean[][] visited) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<Coordinator> queue = new LinkedList<>();
        Coordinator root = new Coordinator(i, j);
        visited[i][j] = true;
        queue.offer(root);
        while (!queue.isEmpty()) {
            Coordinator curr = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = curr.x + dx[k];
                int y = curr.y + dy[k];
                if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                    continue;
                }
                if (visited[x][y]) {
                    continue;
                }
                if (grid[x][y] == 0) {
                    continue;
                }

                visited[x][y] = true;
                queue.offer(new Coordinator(x, y));
            }
        }
    }
    private class Coordinator {
        public int x;
        public int y;
        public Coordinator(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
