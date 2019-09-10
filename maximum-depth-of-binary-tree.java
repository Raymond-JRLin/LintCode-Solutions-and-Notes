// 97. Maximum Depth of Binary Tree
// 中文English
// Given a binary tree, find its maximum depth.
//
// The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
//
// Example
// Example 1:
//
// Input: tree = {}
// Output: 0
// Explanation: The height of empty tree is 0.
// Example 2:
//
// Input: tree = {1,2,3,#,#,4,5}
// Output: 3
// Explanation: Like this:
//    1
//   / \
//  2   3
//     / \
//    4   5
// it will be serialized {1,2,3,#,#,4,5}


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
     * @return: An integer.
     */

    int depth = 0;
    public int maxDepth(TreeNode root) {
        // write your code here
        if (root == null) {
            return depth;
        }
        helper(root, 1); //if root is not null, at least we have 1 level
        return depth;
    }
    private void helper(TreeNode root, int curDepth) {
        if (root == null) {
            return;
        }
        if (curDepth > depth) {
            depth = curDepth;
        }
        helper(root.left, curDepth + 1);
        helper(root.right, curDepth + 1);
    }
}
