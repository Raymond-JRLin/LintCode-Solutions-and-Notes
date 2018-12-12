// 1479. Can Reach The Endpoint
// Description
// Given a map size of m*n, 1 means space, 0 means obstacle, 9 means the endpoint. You start at (0,0) and return whether you can reach the endpoint.
//
// Have you met this question in a real interview?
// Example
// Input:[[1,1,1],[1,1,1],[1,1,9]]
// Output:true


public class Solution {
    /**
     * @param map: the map
     * @return: can you reach the endpoint
     */
    public boolean reachEndpoint(int[][] map) {
        // Write your code here
        if (map == null || map.length == 0 || map[0].length == 0) {
            return false;
        }
        if (map[0][0] == 0) {
            return false;
        }


        // return mytry(map);

        return method2(map);
    }

    private boolean method2(int[][] map) {
        // recursion
        // 其实向右、向下走就可以了， 不需要查看左和上
        return recursion(map, 0, 0);
    }
    private boolean recursion(int[][] map, int i, int j) {
        if (i >= map.length || j >= map[i].length) {
            return false;
        }
        if (map[i][j] == 0) {
            return false;
        }
        if (map[i][j] == 9) {
            return true;
        }
        return recursion(map, i + 1, j) || recursion(map, i, j + 1);
    }

    private boolean mytry(int[][] map) {
        // BFS
        int n = map.length;
        int m = map[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        visited.add(0);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = curr / n + dx[i];
                int y = curr % n + dy[i];
                if (x < 0 || x >= n || y < 0 || y >= m) {
                    continue;
                }
                if (visited.contains(x * n + y)) {
                    continue;
                }
                if (map[x][y] == 0) {
                    continue;
                }
                if (map[x][y] == 9) {
                    return true;
                }
                queue.offer(x * n + y);
                visited.add(x * n + y);
            }
        }
        return false;
    }
}
