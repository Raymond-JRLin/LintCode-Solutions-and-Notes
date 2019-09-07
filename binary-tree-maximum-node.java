// 632. Binary Tree Maximum Node
// 中文English
// Find the maximum node in a binary tree, return the node.
//
// Example
// Example 1:
//
// Input:
// {1,-5,3,1,2,-4,-5}
// Output: 3
// Explanation:
// The tree look like this:
//      1
//    /   \
//  -5     3
//  / \   /  \
// 1   2 -4  -5
// Example 2
//
// Input:
// {10,-5,2,0,3,-4,-5}
// Output: 10
// Explanation:
// The tree look like this:
//      10
//    /   \
//  -5     2
//  / \   /  \
// 0   3 -4  -5


public class Solution {
    /**
     * @param root the root of binary tree
     * @return the max ndoe
     */
    public TreeNode maxNode(TreeNode root) {
        // Write your code here
        if (root == null) {
            return root;
        }
        TreeNode left = maxNode(root.left);
        TreeNode right = maxNode(root.right);
        // maybe null
        // remember compare with current root, otherwise it would return null when it's a leaf node
        return max(root, max(left, right));
    }
    private TreeNode max(TreeNode left, TreeNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (left.val > right.val) {
            return left;
        } else {
            return right;
        }
    }
}
