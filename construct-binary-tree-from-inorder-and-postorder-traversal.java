// 72. Construct Binary Tree from Inorder and Postorder Traversal
// 中文English
// Given inorder and postorder traversal of a tree, construct the binary tree.
//
// Example
// Example 1:
//
// Input：[],[]
// Output：{}
// Explanation:
// The binary tree is null
// Example 2:
//
// Input：[1,2,3],[1,3,2]
// Output：{2,1,3}
// Explanation:
// The binary tree is as follows
//   2
//  / \
// 1   3
//
// Notice
// You may assume that duplicates do not exist in the tree.
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
     *@param inorder : A list of integers that inorder traversal of a tree
     *@param postorder : A list of integers that postorder traversal of a tree
     *@return : Root of a tree
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // write your code here
        // 给了 in-order 就可以通过找到 root 来递归左右两边建数， 关键是如何找到 root， post-order 的最后一位总是当前的 root
        if ((inorder == null && postorder == null) || (inorder.length == 0 && postorder.length == 0)) {
            return null;
        }
        if (inorder.length != postorder.length) {
            return null;
        }
        return toBT(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    private TreeNode toBT(int[] inorder, int in_start, int in_end, int[] postorder, int post_start, int post_end) {
        if (in_start > in_end) {
            return null;
        }
        int index = findIndex(inorder, postorder[post_end]);
        if (index == -1) {
            return null;
        }
        TreeNode root = new TreeNode(inorder[index]);
        root.left = toBT(inorder, in_start, index - 1, postorder, post_start, post_start + index - in_start - 1);
        root.right = toBT(inorder, index + 1, in_end, postorder, post_start + index - in_start, post_end - 1);
        return root;
    }
    private int findIndex(int[] inorder, int target) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
