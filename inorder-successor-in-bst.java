// 448. Inorder Successor in BST
// 中文English
// Given a binary search tree (See Definition) and a node in it, find the in-order successor of that node in the BST.
//
// If the given node has no in-order successor in the tree, return null.
//
// Example
// Example 1:
//
// Input: {1,#,2}, node with value 1
// Output: 2
// Explanation:
//   1
//    \
//     2
// Example 2:
//
// Input: {2,1,3}, node with value 1
// Output: 2
// Explanation:
//     2
//    / \
//   1   3
// Binary Tree Representation
//
// Challenge
// O(h), where h is the height of the BST.
//
// Notice
// It's guaranteed p is one node in the given tree. (You can directly compare the memory address to find p)
//


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        if (root == null) {
            return root;
        }
        // we should find if p is in this BST first
        TreeNode successor = null;
        // while (root != null && root != p) {
        //     if (root.val > p.val) {
        //         // go left child, we use 2 pointers to record
        //         // if p is in the left child position, then successor is its parent node
        //         successor = root;
        //         root = root.left;
        //     } else {
        //         root = root.right;
        //         // if p is in the right child position, then successor is its min node in its most left subtree
        //         // 如果一直在右边，就往右边走，不用记录successor，下面会有同样的情况去找右子树中最左边的
        //     }
        // }
        // // exit because root == null, then we didn't find p
        // if (root == null) {
        //     return null;
        // }
        // // exit because root == p, then we found it
        // // if it does not have right subtree, then successor is the result we just recorded
        // if (root.right == null) {
        //     return successor;
        // }
        // // if it has right subtree, we need to find the leftmost child - min
        // root = root.right; // set root as right child first
        // while (root.left != null) {
        //     // if it has left child, we keep seaching left down
        //     root = root.left;
        // }
        // return root;

        while (root != null) {
            if (root.val > p.val) {
                // go left child, we use 2 pointers to record
                // if p is in the left child position, then successor is its parent node
                successor = root;
                root = root.left;
            } else {
                root = root.right;
                // if p is in the right child position, then successor is its min node in its most left subtree
                // 如果一直在右边，就往右边走，不用记录successor，下面会有同样的情况去找右子树中最左边的
            }
        }
        return successor;
    }
}
