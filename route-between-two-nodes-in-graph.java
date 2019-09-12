// 176. Route Between Two Nodes in Graph
// 中文English
// Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
//
// Example
// Given graph:
//
//   	A----->B----->C
// 	 \     |
// 	  \    |
// 	   \   |
// 	    \  v
// 	     ->D----->E
//
// Example 1:
// Input:s = B and t = E,
// Output:true
//
// Example 2:
// Input:s = D and t = C,
// Output:false


/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) {
 *         label = x;
 *         neighbors = new ArrayList<DirectedGraphNode>();
 *     }
 * };
 */


public class Solution {
    /*
     * @param graph: A list of Directed graph node
     * @param s: the starting Directed graph node
     * @param t: the terminal Directed graph node
     * @return: a boolean value
     */
    public boolean hasRoute(ArrayList<DirectedGraphNode> graph, DirectedGraphNode s, DirectedGraphNode t) {
        // write your code here
        if (s == t) {
            return true;
        }
        // my try: BFS
        Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
        queue.offer(s);
        // 可以采用 visited 来记录访问过哪些
        Set<DirectedGraphNode> visited = new HashSet<DirectedGraphNode>();
        visited.add(s);
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            if (node.neighbors.size() > 0) {
                for (DirectedGraphNode nei : node.neighbors) {
                    if (visited.contains(nei)) {
                        continue;
                    }
                    if (t.equals(nei)) {
                        return true;
                    }
                    queue.offer(nei);
                    visited.add(nei);
                    // 或者访问到一个就把这个 node 从 graph 中 remove
                    // if (t.equals(nei)) {
                    //     return true;
                    // }
                    // if (graph.contains(nei)) {
                    //     queue.offer(nei);
                    //     graph.remove(nei);
                    // }
                }
            }
        }
        return false;

        // method 2: DFS
        // ref: https://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/73092
        // Set<DirectedGraphNode> visited = new HashSet<DirectedGraphNode>();
        // return dfs(graph, s, t, visited);
    }
    private boolean dfs(ArrayList<DirectedGraphNode> graph, DirectedGraphNode s, DirectedGraphNode t, Set<DirectedGraphNode> visited) {
        if (s == t) {
            return true;
        }
        visited.add(s);
        if (s.neighbors.size() > 0) {
            for (DirectedGraphNode nei : s.neighbors) {
                if (visited.contains(nei)) {
                    continue;
                }
                if (dfs(graph, nei, t, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}
