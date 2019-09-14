// 95. Validate Binary Search Tree
// 中文English
// Given a binary tree, determine if it is a valid binary search tree (BST).
//
// Assume a BST is defined as follows:
//
// The left subtree of a node contains only nodes with keys less than the node's key.
// The right subtree of a node contains only nodes with keys greater than the node's key.
// Both the left and right subtrees must also be binary search trees.
// A single node tree is a BST
// Example
// Example 1:
//
// Input:  {-1}
// Output：true
// Explanation：
// For the following binary tree（only one node）:
// 	      -1
// This is a binary search tree.
// Example 2:
//
// Input:  {2,1,4,#,#,3,5}
// Output: true
// For the following binary tree:
// 	  2
// 	 / \
// 	1   4
// 	   / \
// 	  3   5
// This is a binary search tree.


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
class resultType {
    boolean isBST;
    int max, min;
    resultType(boolean isBST, int max, int min) {
        this.isBST = isBST;
        this.max = max;
        this.min = min;
    }
}
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // write your code here

        // method 1:
        // resultType result = helper(root);
        // return result.isBST;

        // method 2:
        return divConq(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public resultType helper(TreeNode root) {
        if (root == null) {
            return new resultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
            //attention here swap the min and max value, cause it's null node
        }
        resultType left = helper(root.left);
        resultType right = helper(root.right);
        if (!left.isBST || !right.isBST) {
            return new resultType(false, 0, 0);
        }
        if (root.left != null && root.val <= left.max || root.right != null && root.val >= right.min) {
            //attention for root.left/right, or left/right. directly
            return new resultType(false, 0, 0);
        }
        return new resultType(true, Math.max(root.val, right.max), Math.min(root.val, left.min));
        //attention for root.left/right, or left/right. directly

    }

    // method 2: divide and conquer
    private boolean divConq(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return divConq(root.left, min, root.val) && divConq(root.right, root.val, max);
    }
}
