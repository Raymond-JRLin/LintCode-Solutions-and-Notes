// 178. Graph Valid Tree
// 中文English
// Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
//
// Example
// Example 1:
//
// Input: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
// Output: true.
// Example 2:
//
// Input: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
// Output: false.
// Notice
// You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
//


public class Solution {
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        // Write your code here
        if (edges == null || n == 0) {
            return false;
        }
        if (edges.length != n - 1) {
            return false;
        }
        Map<Integer, Set<Integer>> graph = initTree(n, edges);
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(0);
        set.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer neighbor : graph.get(node)) {
                if (set.contains(neighbor)){
                    continue;
                }
                set.add(neighbor);
                queue.offer(neighbor);
            }
        }
        return (set.size() == n);
    }
    private Map<Integer, Set<Integer>> initTree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }
}
