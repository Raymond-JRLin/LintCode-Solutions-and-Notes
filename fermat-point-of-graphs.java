// 1400. Fermat Point Of Graphs
// Description
// There is a non acyclic connected graph. Each edge is described by two vertices x[i] and y[i], and the length of each edge is described by d[i].
// Find a point p such that the sum of distances from point p to other points is the smallest. If there is more than one such point p, return the smallest number.
//
// 2 <= n, d[i] <= 10^5
// 1 <= x[i], y[i] <= n
// Have you met this question in a real interview?
// Example
// Given x = [1], y = [2], d = [3], return 1.
//
// Explanation:
// The distance from other points to 1 is 3, the distance from other points to 2 is 3, and the number of 1 is smaller.
// Given x = [1,2,2], y = [2,3,4], d = [1,1,1], return 2.
//
// Explanation:
// The distance from other points to 1 is 5, the distance from other points to 2 is 3, the distance from


public class Solution {
    /**
     * @param x: The end points set of edges
     * @param y: The end points set of edges
     * @param d: The length of edges
     * @return: Return the index of the fermat point
     */
    public int getFermatPoint(int[] x, int[] y, int[] d) {
        // Write your code here
        if (x == null || x.length == 0) {
            return 0;
        }

        // return mytry(x, y, d);

        return method2(x, y, d);
    }

    private int mytry(int[] x, int[] y, int[] d) {
        int n = x.length + 1;

        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < x.length; i++) {
            adj.get(x[i]).add(new Node(y[i], d[i]));
            adj.get(y[i]).add(new Node(x[i], d[i]));
        }

        long[] dis = new long[n + 1];
        int[] count = new int[n + 1];
        dfs(1, adj, count, dis, new boolean[n + 1]);
        recursion(n, 1, adj, count, dis, new boolean[n + 1]);

        int result = 0;
        long min = Long.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            if (dis[i] < min) {
                min = dis[i];
                result = i;
            }
        }
        return result;
    }
    private void dfs(int root, List<List<Node>> adj, int[] count, long[] dis, boolean[] visited) {
        visited[root] = true;
        for (Node next : adj.get(root)) {
            if (visited[next.to]) {
                continue;
            }
            dfs(next.to, adj, count, dis, visited);
            count[root] += count[next.to];
            dis[root] += count[next.to] * next.d + dis[next.to];
        }
        count[root]++; // count root itself
    }
    private void recursion(int n, int root, List<List<Node>> adj, int[] count, long[] dis, boolean[] visited) {
        visited[root] = true;
        for (Node next : adj.get(root)) {
            if (visited[next.to]) {
                continue;
            }
            dis[next.to] = dis[root] - (long)count[next.to] * next.d + (long)(n - count[next.to]) * next.d;
            recursion(n, next.to, adj, count, dis, visited);
        }
    }
    private class Node {
        private int to; // "to" vertex
        private int d; // distance from "from" vertice to "to" vertex
        public Node(int to, int d) {
            this.to = to;
            this.d = d;
        }
    }


    private int method2(int[] x, int[] y, int[] d) {
        int n = x.length + 1;

        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < x.length; i++) {
            adj.get(x[i]).add(new Node(y[i], d[i]));
            adj.get(y[i]).add(new Node(x[i], d[i]));
        }

        long[] dis = new long[n + 1];
        int[] count = new int[n + 1];
        dfs2(1, adj, count, dis, 0);
        recursion2(n, 1, adj, count, dis, 0);

        int result = 0;
        long min = Long.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            if (dis[i] < min) {
                min = dis[i];
                result = i;
            } else if (dis[i] == min && i < result) {
                result = i;
            }
        }
        return result;
    }
    private void dfs2(int curr, List<List<Node>> adj, int[] count, long[] dis, int root) {
        count[curr]++; // count root itself
        for (Node next : adj.get(curr)) {
            if (next.to == root) {
                continue;
            }
            dfs2(next.to, adj, count, dis, curr);
            count[curr] += count[next.to];
            dis[curr] += count[next.to] * next.d + dis[next.to];
        }

    }
    private void recursion2(int n, int curr, List<List<Node>> adj, int[] count, long[] dis, int root) {
        for (Node next : adj.get(curr)) {
            if (next.to == root) {
                continue;
            }
            dis[next.to] = dis[curr] - (long)count[next.to] * next.d + (long)(n - count[next.to]) * next.d;
            recursion2(n, next.to, adj, count, dis, curr);
        }
    }
}
