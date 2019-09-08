// 175. Invert Binary Tree
// 中文English
// Invert a binary tree.Left and right subtrees exchange.
//
// Example
// Example 1:
//
// Input: {1,3,#}
// Output: {1,#,3}
// Explanation:
// 	  1    1
// 	 /  =>  \
// 	3        3
// Example 2:
//
// Input: {1,2,3,#,#,4}
// Output: {1,3,2,#,4}
// Explanation:
//
//       1         1
//      / \       / \
//     2   3  => 3   2
//        /       \
//       4         4
// Challenge
// Do it in recursion is acceptable, can you do it without recursion?
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
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void invertBinaryTree(TreeNode root) {
        // write your code here

        // method 1: recursion
        // if (root == null) {
        //     return;
        // }
        // // invert left and right
        // TreeNode temp = root.right;
        // root.right = root.left;
        // root.left = temp;
        // invertBinaryTree(root.left); // go left subtree
        // invertBinaryTree(root.right); // go right subtree

        // method 2: iteration
        if (root == null) {
            return;
        }
        // while (root != null) {
        //     invert(root);
        //     if (root.right != null) {
        //         invert(root.right);
        //     }
        //     if (root.left != null) {
        //         invert(root.left);
        //     }
        // }
        // found that we cannot use while loop, because we should go left AND right
        // so we can try queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            invert(node);
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
        }
    }
    private void invert(TreeNode root) {
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
    }
}
