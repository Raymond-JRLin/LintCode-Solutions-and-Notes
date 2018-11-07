// 434. Number of Islands II
// Description
// Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k). Originally, the 2D matrix is all 0 which means there is only sea in the matrix. The list pair has k operator and each operator has two integer A[i].x, A[i].y means that you can change the grid matrix[A[i].x][A[i].y] from sea to island. Return how many island are there in the matrix after each operator.
//
// 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.
//
// Have you met this question in a real interview?
// Example
// Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].
//
// return [1,1,2,2].


/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    /**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // Write your code here

        if (n == 0 || m == 0 || operators == null || operators.length == 0) {
            return Collections.emptyList();
        }

        // return mytry(n, m, operators);

        return method2(n, m, operators);
    }

    private List<Integer> method2(int n, int m, Point[] operators) {
        // method 2: use HashMap to build Union Find
        // reference: https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Islands%20II.java
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int count = 0;
        UnionFind uf = new UnionFind(n * m);
        int SEA = 0;
        int ISLAND = 1;
        int[] islands = new int[n * m];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < operators.length; i++) {
            int x = operators[i].x;
            int y = operators[i].y;
            int index = x * m + y;
            if (islands[index] == SEA) {
                count++;
                islands[index] = ISLAND;
                for (int j = 0; j < 4; j++) {
                    int newX = x + dx[j];
                    int newY = y + dy[j];
                    int newIndex = newX * m + newY;
                    if (!inBound(newX, newY, n, m)) {
                        continue;
                    }
                    if (islands[newIndex] == SEA) {
                        continue;
                    }
                    int root = uf.find(index);
                    int newRoot = uf.find(newIndex);
                    if (root != newRoot) {
                        uf.union(root, newRoot);
                        count--;
                    }
                }
            }
            result.add(count);
        }
        return result;
    }
    // use HashMap to build a Union Find
    private class UnionFind {
        HashMap<Integer, Integer> map = new HashMap<>();
        UnionFind(int size) {
            for (int i = 0; i < size; i++) {
                map.put(i, i);
            }
        }
        // UnionFind(int n, int m) {
        //     for (int i = 0; i < n; i++) {
        //         for (int j = 0; j < m; j++) {
        //             int index = i * m + j;
        //             map.put(index, index);
        //         }
        //     }
        // }
        public int find(int target) {
            int i = map.get(target);
            while (i != map.get(i)) {
                i = map.get(i);
            }
            // return i; // without path compression
            // HashMap path compression:
            int father = target;
            while (father != map.get(target)) {
                int temp = map.get(target);
                map.put(father, i);
                father = temp;
            }
            return i;
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                map.put(rootX, rootY);
            }
        }
    }

    private List<Integer> mytry(int n, int m, Point[] operators) {
        // mthod 1: use 1D array to do Union Find
        // reference: https://segmentfault.com/a/1190000004197552
        // it reminds me another problem: connecting graph
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int count = 0;
        int[] id = new int[n * m];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n * m; i++) {
            id[i] = -1;
            // if we use 1D array, then we should initialize as -1
            // if we do like 0 or 1, it would be wrong since 0 or 1 would be connected before starting
        }
        for (int i = 0; i < operators.length; i++) {
            int index = operators[i].x * m + operators[i].y;
            if (id[index] != -1) {
                // if it is the same operation as previous one
                result.add(count);
                continue;
            }
            // make root
            id[index] = index;
            count++;
            for (int j = 0; j < 4; j++) {
                int x = operators[i].x + dx[j];
                int y = operators[i].y + dy[j];
                if (!inBound(x, y, n, m)) {
                    continue;
                }
                if (id[x * m + y] == -1) {
                    continue;
                }
                // find the new root of new coordinator
                int root = findRoot(id, x * m + y);
                if (root != index) {
                    // if their roots are not equal, then connect, and minus count
                    id[root] = index;
                    count--;
                }
            }
            result.add(count);
        }
        return result;
    }
    private int findRoot(int[] id, int i) {
        while (i != id[i]) {
            id[i] = id[id[i]]; // path compression
            i = id[i];
        }
        return i;
    }
    private boolean inBound(int x, int y, int n, int m) {
        if (x < 0 || x >= n) {
            return false;
        }
        if (y < 0 || y >= m) {
            return false;
        }
        return true;
    }
}
