// 433. Number of Islands
// 中文English
// Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.
//
// Find the number of islands.
//
// Example
// Example 1:
//
// Input:
// [
//   [1,1,0,0,0],
//   [0,1,0,0,1],
//   [0,0,0,1,1],
//   [0,0,0,0,0],
//   [0,0,0,0,1]
// ]
// Output:
// 3
// Example 2:
//
// Input:
// [
//   [1,1]
// ]
// Output:
// 1


public class Solution {
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        // Write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int island = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    BFS(grid, i, j);
                    island ++;
                }
            }
        }
        return island;
    }

    private void BFS(boolean[][] grid, int x, int y) {
        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};
        Queue<Coordinator> queue = new LinkedList<>();
        Coordinator coordinator = new Coordinator(x, y);
        queue.offer(coordinator);
        grid[x][y] = false;
        while(!queue.isEmpty()) {
            Coordinator coor = queue.poll();
            for(int i = 0; i < 4; i++) {
                Coordinator adj = new Coordinator(coor.x + dx[i], coor.y + dy[i]);
                if (!inBound(adj, grid)) {// why don't need grid[][]
                //because what we need is the adjacent value rather than grid[][]
                    continue;
                }
                if (grid[adj.x][adj.y]) {
                    grid[adj.x][adj.y] = false;
                    queue.offer(adj);
                }
            }

        }
    }

    private boolean inBound(Coordinator adj, boolean[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if (adj.x >= 0 && adj.x < n && adj.y >= 0 && adj.y < m) {
            return true;
        } else {
            return false;
        }
    }
}
class Coordinator {
    int x, y;
    public Coordinator(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
