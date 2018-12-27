// 1092. Cut Off Trees for Golf Event
// Description
// You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
//
// 0 represents the obstacle can't be reached.
// 1 represents the ground can be walked through.
// The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
// You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).
// You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.
//
// You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
//
// size of the given matrix will not exceed 50x50.
//
// Have you met this question in a real interview?
// Example
// Input:
// [
// [1,2,3],
// [0,0,4],
// [7,6,5]
// ]
// Output: 6
//
// Input:
// [
// [1,2,3],
// [0,0,0],
// [7,6,5]
// ]
// Output: -1
//
// Input:
// [
// [2,3,4],
// [0,0,5],
// [8,7,6]
// ]
// Output: 6
// Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.


public class Solution {
    /**
     * @param forest: a list of integers
     * @return: return a integer
     */
    public int cutOffTree(List<List<Integer>> forest) {
        // write your code here
        if (forest == null || forest.size() == 0 || forest.get(0).size() == 0) {
            return -1;
        }
        if (forest.get(0).get(0) == 0) {
            return -1;
        }

        return mytry(forest);
    }

    private int mytry(List<List<Integer>> forest) {
        // 题目一开始理解错了， 没有砍掉的树也是可以通过的， 每次要走去砍掉这个森林中最矮的那一颗树
        int n = forest.size();
        int m = forest.get(0).size();
        // 首先把所有需要砍的树存起来， 按照树的高度从小到大排序
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (forest.get(i).get(j) > 1) {
                    points.add(new Point(i, j, forest.get(i).get(j)));
                }
            }
        }
        Collections.sort(points);
        // 使用 BFS 找出每次砍树走过的最短距离
        int result = 0;
        int x = 0;
        int y = 0;
        for (Point p : points) {
            int step = bfs(x, y, p, forest, n, m);
            if (step == -1) {
                System.out.println(" cannot reach");
                return -1;
            }
            result += step;
            // 重新设置下一次的起始点
            x = p.x;
            y = p.y;
        }
        return result;
    }
    private int bfs(int x0, int y0, Point p, List<List<Integer>> forest, int n, int m) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x0, y0});
        visited[x0][y0] = true;
        int result = 0;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                if (curr[0] == p.x && curr[1] == p.y) {
                    return result;
                }
                for (int i = 0; i < 4; i++) {
                    int x = curr[0] + dx[i];
                    int y = curr[1] + dy[i];
                    if (x < 0 || x >= n || y < 0 || y >= m) {
                        continue;
                    }
                    if (visited[x][y]) {
                        continue;
                    }
                    if (forest.get(x).get(y) == 0) {
                        continue;
                    }
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
            result++;
        }
        return -1;
    }
    private class Point implements Comparable<Point> {
        private int x;
        private int y;
        private int h;

        public Point(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }

        @Override
        public int compareTo(Point o2) {
            return Integer.compare(this.h, o2.h);
        }
    }
}
