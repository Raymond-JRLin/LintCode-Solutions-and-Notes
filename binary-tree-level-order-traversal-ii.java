// 70. Binary Tree Level Order Traversal II
// 中文English
// Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
//
// Example
// Example 1:
//
// Input:
// {1,2,3}
// Output:
// [[2,3],[1]]
// Explanation:
//     1
//    / \
//   2   3
// it will be serialized {1,2,3}
// level order traversal
// Example 2:
//
// Input:
// {3,9,20,#,#,15,7}
// Output:
// [[15,7],[9,20],[3]]
// Explanation:
//     3
//    / \
//   9  20
//     /  \
//    15   7
// it will be serialized {3,9,20,#,#,15,7}
// level order traversal


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
     * @return: buttom-up level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(0, level);
            // result.add(level);
        }
        // Collections.reverse(result);
        return result;
    }
}
