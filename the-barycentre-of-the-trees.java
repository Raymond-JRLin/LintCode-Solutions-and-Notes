// 1395. The Barycentre Of The Trees
// Description
// For a multi-branch tree, if there is a node R with R as the root, and the largest sub-tree of all its sub-trees has the least number of nodes, the node R is said to be the center of gravity of the tree.
// Now give you a multi-branch tree with n nodes. Find the center of gravity of this tree. If there are multiple centers of gravity, return the one with the lowest number.
// x[i], y[i] represents the two points of the i-th edge.
//
// 2 <= n <= 10^5
// 1 <= x[i], y[i] <= n
// Have you met this question in a real interview?
// Example
// Given x = [1], y = [2], return 1.
//
// Explanation:
// Both 1 and 2 can be center of gravity, but the number of 1 is the smallest.
// Given x = [1,2,2], y = [2,3,4], return 2.
//
// Explanation:
// 2 is the center of gravity.


public class Solution {
    /**
     * @param x: The vertexes of the edges
     * @param y: The vertexes of the edges
     * @return: Return the index of barycentre
     */
    public int getBarycentre(int[] x, int[] y) {
        // Write your code here

        return mytry(x, y);
    }

    private int mytry(int[] x, int[] y) {
        // similar to Fermat Point Of Graphs on LintCode and Sum of Distance in Tree on LeetCode

        // DP DFS
        int n = x.length + 1; // n node (1 - n)

        // get adjacent list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < x.length; i++) {
            adj.get(x[i]).add(y[i]);
            adj.get(y[i]).add(x[i]);
        }

        int[] count = new int[n + 1]; // 子节点数
        int[] max = new int[n + 1]; // max[i] = 以 i 为 root 的最大子树 （节点数最多的子树） 的 node 数
        dfs(adj, n, count, max, 1, 0);
        // result
        int result = Integer.MAX_VALUE;
        int node = 0;
        for (int i = 1; i <= n; i++) {
            if (max[i] < result) {
                result = max[i];
                node = i;
            }
        }
        return node;
    }
    private void dfs(List<List<Integer>> adj, int n, int[] count, int[] max, int root, int prev) {
        // get the sum of # subtree's nodes of this root

        for (int next : adj.get(root)) {
            if (next == prev) {
                continue;
            }
            dfs(adj, n, count, max, next, root);
            count[root] += count[next];
            max[root] = Math.max(max[root], count[next]);
        }
        count[root]++;
        max[root] = Math.max(max[root], n - count[root]);
    }
}



考点

树形 DP

题解

随意选择一个点作为树的根节点，比如 1 结点。
dp[i] 代表以 i 为根的子树的结点个数，dp[i] = sum(dp[j]) + 1。
则以 i 为根的子树的最大结点个数为 max(max(dp[j]), n - dp[i])。
在 DFS 的过程中维护答案即可。


// answer:

public class Solution {
    /**
     * @param x: The vertexes of the edges
     * @param y: The vertexes of the edges
     * @return: Return the index of barycentre
     */

    int ansNode;
    int ansSize;

    void dfs(int x, int f, int n, int[] dp, List<List<Integer>> g) {
        dp[x] = 1;
        int maxSubtree = 0;
        for (int i = 0; i < g.get(x).size(); i++) {
            int y = g.get(x).get(i);
            if (y == f) {
                continue;
            }
            dfs(y, x, n, dp, g);
            dp[x] += dp[y];
            maxSubtree = Math.max(maxSubtree, dp[y]);
        }
        maxSubtree = Math.max(maxSubtree, n - dp[x]);
        if (maxSubtree < ansSize || (maxSubtree == ansSize && x < ansNode)) {
            ansNode = x;
            ansSize = maxSubtree;
        }
    }

    public int getBarycentre(int[] x, int[] y) {
        // Write your code here
        List<List<Integer>>g = new ArrayList<List<Integer>>();
        for(int i = 0; i <= x.length + 1; i++) {
            g.add(new ArrayList<Integer>());
        }
        int[] dp = new int[x.length + 2];
        for (int i = 0; i < x.length; i++) {
            g.get(x[i]).add(y[i]);
            g.get(y[i]).add(x[i]);
        }
        ansNode = 0;
        ansSize = x.length + 2;
        dfs(1, 0, x.length + 1, dp, g);
        return ansNode;
    }
}
