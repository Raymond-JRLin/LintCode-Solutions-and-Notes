// 69. Binary Tree Level Order Traversal
// 中文English
// Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
//
// Example
// Example 1:
//
// Input：{1,2,3}
// Output：[[1],[2,3]]
// Explanation：
//   1
//  / \
// 2   3
// it will be serialized {1,2,3}
// level order traversal
// Example 2:
//
// Input：{1,#,2,3}
// Output：[[1],[2],[3]]
// Explanation：
// 1
//  \
//   2
//  /
// 3
// it will be serialized {1,#,2,3}
// level order traversal
// Challenge
// Challenge 1: Using only 1 queue to implement it.
//
// Challenge 2: Use BFS algorithm to do it.
//
// Notice
// The first data is the root node, followed by the value of the left and right son nodes, and "#" indicates that there is no child node.
// The number of nodes does not exceed 20.


/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }
}
