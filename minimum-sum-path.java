// 1482. Minimum Sum Path
// Description
// Given a BST, find the path with the minimum sum from root to leaves.
//
// Have you met this question in a real interview?
// Example
// Input:{5,2,6,#,3,#,8}
// Output:10


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
     * @param root: the root
     * @return: minimum sum
     */
    public int minimumSum(TreeNode root) {
        // Write your code here
        if (root == null) {
            return 0;
        }

        return mytry(root);
    }

    private int mytry(TreeNode root) {
        // 注意是一定要走到 Leaf 的
        if (root.left == null && root.right == null) {
            return root.val;
        } else if (root.left == null) {
            return root.val + mytry(root.right);
        } else if (root.right == null) {
            return root.val + mytry(root.left);
        }
        int left = mytry(root.left);
        int right = mytry(root.right);
        return Math.min(left, right) + root.val;
    }
}
