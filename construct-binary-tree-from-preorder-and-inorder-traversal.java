// 73. Construct Binary Tree from Preorder and Inorder Traversal
// 中文English
// Given preorder and inorder traversal of a tree, construct the binary tree.
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
// Input：[2,1,3],[1,2,3]
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
     *@param preorder : A list of integers that preorder traversal of a tree
     *@param inorder : A list of integers that inorder traversal of a tree
     *@return : Root of a tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here
        if (preorder == null || inorder == null) {
            return null;
        }
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        if (preorder.length != inorder.length) {
            return null;
        }
        return toBT(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    private TreeNode toBT(int[] preorder, int pre_start, int pre_end, int[] inorder, int in_start, int in_end) {
        if (in_start > in_end) {
            return null;
        }
        int index = findIndex(inorder, preorder[pre_start]);
        if (index == -1) {
            return null;
        }
        TreeNode root = new TreeNode(inorder[index]);
        root.left = toBT(preorder, pre_start + 1, pre_start + index - in_start, inorder, in_start, index - 1);
        root.right = toBT(preorder, pre_start + index - in_start + 1, pre_end, inorder, index + 1, in_end);
        return root;
    }
    private int findIndex(int[] inorder, int target) {
        // 树中没有重复的值， 所以我们可以通过遍历来找到当前根结点的值
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
