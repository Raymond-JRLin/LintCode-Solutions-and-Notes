// 94. Binary Tree Maximum Path Sum
// Description
// Given a binary tree, find the maximum path sum.
//
// The path may start and end at any node in the tree.
//
// Have you met this question in a real interview?
// Example
// Given the below binary tree:
//
//   1
//  / \
// 2   3
// return 6.


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
     * @return: An integer.
     */

    public int maxPathSum(TreeNode root) {
        // write your code here
        if (root == null) {
            return 0;
        }

        // NC method
        // return method1(root).pathSum;


        method2(root);
        return result;
    }

    private int result = Integer.MIN_VALUE;
    private int method2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = method2(root.left);
        int right = method2(root.right);
        // single path starting from root - remember plus root value
        int single = Math.max(root.val, root.val + Math.max(left, right));
        // whole path through root like "^" shape
        int sum = left + right + root.val;
        result = Math.max(result, Math.max(single, sum));
        return single;
    }

    private ResultType method1(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        ResultType left = method1(root.left);
        ResultType right = method1(root.right);
        int single = Math.max(left.singlePath, right.singlePath) + root.val;
        single = Math.max(single, 0);
        int max = Math.max(left.pathSum, right.pathSum);
        max = Math.max(max, left.singlePath + right.singlePath + root.val);
        return new ResultType(single, max);
    }
    private class ResultType {
        // singlePath: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
        // maxPath: 从树中任意到任意点的最大路径，这条路径至少包含一个点
        private int singlePath;
        private int pathSum;

        ResultType(int single, int sum) {
            singlePath = single;
            pathSum = sum;
        }
    }
}
