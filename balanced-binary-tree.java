// 93. Balanced Binary Tree
// 中文English
// Given a binary tree, determine if it is height-balanced.
//
// For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
//
// Example
// Example  1:
// 	Input: tree = {1,2,3}
// 	Output: true
//
// 	Explanation:
// 	This is a balanced binary tree.
// 		  1
// 		 / \
// 		2  3
//
//
// Example  2:
// 	Input: tree = {3,9,20,#,#,15,7}
// 	Output: true
//
// 	Explanation:
// 	This is a balanced binary tree.
// 		  3
// 		 / \
// 		9  20
// 		  /  \
// 		 15   7
//
//
// Example  3:
// 	Input: tree = {1,#,2,3,4}
// 	Output: false
//
// 	Explanation:
// 	This is not a balanced tree.
// 	The height of node 1's right sub-tree is 2 but left sub-tree is 0.
// 		  1
// 		   \
// 		   2
// 		  /  \
// 		 3   4
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
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        // write your code here
        if (root == null) {
		    return true;
	    }
        // method 1:
        // int result = countDepth(root);
        // if (result == -1) {
        //     return false;
        // } else {
        //     return true;
        // }
        //return result != -1;

        // method 2:

	    int heightDiff = getHeight(root.left) - getHeight(root.right);
	    if (Math.abs(heightDiff) > 1) {
		    return false;
	    } else {
		    return isBalanced(root.left) && isBalanced(root.right);
	    }
    }
    // method 1:
    private int countDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countDepth(root.left);
        int right = countDepth(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
    // method 2:
    private int getHeight(TreeNode root) {
	    if (root == null) {
		    return 0;
	    }
	    return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}
