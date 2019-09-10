// 628. Maximum Subtree
// 中文English
// Given a binary tree, find the subtree with maximum sum. Return the root of the subtree.
//
// Example
// Example 1:
//
// Input:
// {1,-5,2,0,3,-4,-5}
// Output:3
// Explanation:
// The tree is look like this:
//      1
//    /   \
//  -5     2
//  / \   /  \
// 0   3 -4  -5
// The sum of subtree 3 (only one node) is the maximum. So we return 3.
// Example 2:
//
// Input:
// {1}
// Output:1
// Explanation:
// The tree is look like this:
//    1
// There is one and only one subtree in the tree. So we return 1.
// Notice
// LintCode will print the subtree which root is your return node.
// It's guaranteed that there is only one subtree with maximum sum and the given binary tree is not an empty tree.
//


public class Solution {
    /**
     * @param root the root of binary tree
     * @return the maximum weight node
     */

    int max = Integer.MIN_VALUE;
    TreeNode result = null;
    public TreeNode findSubtree(TreeNode root) {
        // Write your code here
        helper(root);
        return result;


    }
    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        if (left + right + root.val > max) {
            max = left + right + root.val;
            result = root;
        }
        return left + right + root.val;
    }
}
