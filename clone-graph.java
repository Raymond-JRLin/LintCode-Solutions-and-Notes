// 137. Clone Graph
// 中文English
// Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors. Nodes are labeled uniquely.
//
// You need to return a deep copied graph, which has the same structure as the original graph, and any changes to the new graph will not have any effect on the original graph.
//
// Example
// Example1
//
// Input:
// {1,2,4#2,1,4#4,1,2}
// Output:
// {1,2,4#2,1,4#4,1,2}
// Explanation:
// 1------2
//  \     |
//   \    |
//    \   |
//     \  |
//       4
// Clarification
// How we serialize an undirected graph: http://www.lintcode.com/help/graph/
//
// Notice
// You need return the node with the same label as the input node.
//


/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
        if (node == null) {
            return null;
        }
        ArrayList<UndirectedGraphNode> nodeList = getNode(node);
        Map<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
        for (UndirectedGraphNode n : nodeList) {
            mapping.put(n, new UndirectedGraphNode(n.label));
        }
        for (UndirectedGraphNode n : nodeList) {
            UndirectedGraphNode newNode = mapping.get(n);
            for (UndirectedGraphNode neighbor : n.neighbors) {
                UndirectedGraphNode newNeighbor = mapping.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }
        return mapping.get(node);
    }
    private ArrayList<UndirectedGraphNode> getNode(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        while(!queue.isEmpty()) {
            UndirectedGraphNode nodes = queue.poll();
            for (UndirectedGraphNode nodeNeighbor : nodes.neighbors) {
                if (!set.contains(nodeNeighbor)){
                    queue.offer(nodeNeighbor);
                    set.add(nodeNeighbor);
                }
            }
        }
        return new ArrayList<UndirectedGraphNode>(set);
    }
}
