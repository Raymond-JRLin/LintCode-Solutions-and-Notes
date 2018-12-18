// 1577. Sum of leaf nodes
// Description
// Given a binary tree, find the sum of all leaf nodes.Use O(1) space.
//
// 1.参考Morris 算法
//
// Have you met this question in a real interview?
// Example
// Given binary tree:
//
//     1
//    / \
//   2   3
//  / \
// 4   5
// Return : 12


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
     * @param root:
     * @return: the sum of leafnode
     */
    public int sumLeafNode(TreeNode root) {
        // Write your code here
        if (root == null) {
            return 0;
        }

        // return mytry(root);

        return method2(root);
    }

    private int method2(TreeNode root) {
        // 如果要 O(1) space 的话， 就不能递归了， 根据题目提示， 要使用 Morris algorithm
        int sum = 0;
        TreeNode curr = root;
        while (curr != null) {
            // sum up leaf node values
            if (curr.left == null && curr.right == null) {
                sum += curr.val;
            }
            // Morris
            if (curr.left == null) {
                curr = curr.right;
            } else {
                TreeNode prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
                // sum up leaf node values
                if (prev.left == null && prev.right == null) {
                    sum += prev.val;
                }
                // link left-subtree rightmost to current
                if (prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    // release the existed link
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }
        return sum;
    }

    private int mytry(TreeNode root) {
        // recursion / divide conquer
        // O(N) time and O(N) space (stack)
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int left = mytry(root.left);
        int right = mytry(root.right);
        return left + right;
    }
}
