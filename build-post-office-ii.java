// 573. Build Post Office II
// 中文English
// Given a 2D grid, each cell is either a wall 2, an house 1 or empty 0 (the number zero, one, two), find a place to build a post office so that the sum of the distance from the post office to all the houses is smallest.
//
// Return the smallest sum of distance. Return -1 if it is not possible.
//
// Example
// Example 1:
//
// Input：[[0,1,0,0,0],[1,0,0,2,1],[0,1,0,0,0]]
// Output：8
// Explanation： Placing a post office at (1,1), the distance that post office to all the house sum is smallest.
// Example 2:
//
// Input：[[0,1,0],[1,0,1],[0,1,0]]
// Output：4
// Explanation： Placing a post office at (1,1), the distance that post office to all the house sum is smallest.
// Challenge
// Solve this problem within O(n^3) time.
//
// Notice
// You cannot pass through wall and house, but can pass through empty.
// You only build post office on an empty.


public class Solution {
    /**
     * @param grid a 2D grid
     * @return an integer
     */
    public int n;
    public int m;
    public int[] dx = {1, 0, 0, -1};
    public int[] dy = {0, 1, -1, 0};
    public int WALL = 2;
    public int HOUSE = 1;
    public int EMPTY = 0;
    public int shortestDistance(int[][] grid) {
        // Write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        n = grid.length;
        m = grid[0].length;
        List<Coordinator> houses = getCoor(grid, HOUSE);
        int[][] visitedTime = new int[n][m];
        int[][] distance = new int[n][m];
        for (Coordinator house : houses) {
            bfs(house, visitedTime, distance, grid);
        }
        int shortest = Integer.MAX_VALUE;
        List<Coordinator> empty = getCoor(grid, EMPTY);
        for (Coordinator point : empty) {
            if (visitedTime[point.x][point.y] == houses.size()) {
                shortest = Math.min(shortest, distance[point.x][point.y]);
            }
        }
        if (shortest == Integer.MAX_VALUE) {
            return -1;
        } else {
            return shortest;
        }
    }
    private List<Coordinator> getCoor(int[][] grid, int type) {
        List<Coordinator> results = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == type) {
                    results.add(new Coordinator(i, j));
                }
            }
        }
        return results;
    }
    private void bfs(Coordinator house, int[][] visitedTime, int[][] distance, int[][] grid){
        Queue<Coordinator> queue = new LinkedList<>();
        boolean[][] hash = new boolean[n][m];
        int step = 0;
        queue.offer(house);
        hash[house.x][house.y] = true;
        while (!queue.isEmpty()){
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Coordinator coor = queue.poll();
                for (int j = 0; j < 4; j++) {
                    Coordinator neighbor = new Coordinator(coor.x + dx[j], coor.y + dy[j]);
                    if (!inBound(neighbor, grid)) {
                        continue;
                    }
                    if (hash[neighbor.x][neighbor.y]) {
                        continue;
                    }
                    distance[neighbor.x][neighbor.y] += step;
                    visitedTime[neighbor.x][neighbor.y]++;
                    queue.offer(neighbor);
                    hash[neighbor.x][neighbor.y] = true;
                }
            }
        }
    }
    private boolean inBound(Coordinator coor, int[][] grid) {
        if (coor.x < 0 || coor.x >= n) {
            return false;
        }
        if (coor.y < 0 || coor.y >= m) {
            return false;
        }
        return grid[coor.x][coor.y] == EMPTY;
    }
}

class Coordinator {
    int x;
    int y;
    public Coordinator(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
