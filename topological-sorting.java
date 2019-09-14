// 127. Topological Sorting
// 中文English
// Given an directed graph, a topological order of the graph nodes is defined as follow:
//
// For each directed edge A -> B in graph, A must before B in the order list.
// The first node in the order can be any node in the graph with no nodes direct to it.
// Find any topological order for the given graph.
//
// Example
// For graph as follow:
//
// 图片
//
// The topological order can be:
//
// [0, 1, 2, 3, 4, 5]
// [0, 2, 3, 1, 5, 4]
// ...
// Challenge
// Can you do it in both BFS and DFS?
//
// Clarification
// Learn more about representation of graphs
//
// Notice
// You can assume that there is at least one topological order in the graph.
//
// {0,1,2,3,4#1,3,4#2,1,4#3,4#4}
//


/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        if (graph == null) {
            return result;
        }
        Map<DirectedGraphNode, Integer> map = countIndegree(graph);
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            if (map.get(node) == 0) {
                queue.offer(node);
                result.add(node);
            }
        }
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            for (DirectedGraphNode neighbor : node.neighbors) {
                map.put(neighbor, map.get(neighbor) - 1);
                if (map.get(neighbor) == 0) {
                    result.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        if (result.size() == graph.size()) {
            return result;
        } else {
            return null;
        }
    }
    private Map<DirectedGraphNode, Integer> countIndegree(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Integer> indegree = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            indegree.put(node, 0);
        }
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegree.put(neighbor, indegree.get(neighbor) + 1);
            }
        }
        return indegree;
    }
}
