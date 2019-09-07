// 375. Clone Binary Tree
// 中文English
// For the given binary tree, return a deep copy of it.
//
// Example
// Example 1:
//
// Input: {1,2,3,4,5}
// Output: {1,2,3,4,5}
// Explanation:
// The binary tree is look like this:
//      1
//    /  \
//   2    3
//  / \
// 4   5
// Example 2:
//
// Input: {1,2,3}
// Output: {1,2,3}
// Explanation:
// The binary tree is look like this:
//    1
//  /  \
// 2    3
// Notice
// You'd better not just return root to solve the problem.
//


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
     * @param root: The root of binary tree
     * @return root of new tree
     */
    public TreeNode cloneTree(TreeNode root) {
        // Write your code here
        if (root == null) {
            return root;
        }
        TreeNode copy = new TreeNode(root.val); // deep copy
        copy.left = cloneTree(root.left);
        copy.right = cloneTree(root.right);
        return copy;
    }
}
