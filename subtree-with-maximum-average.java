// 597. Subtree with Maximum Average
// Description
// Given a binary tree, find the subtree with maximum average. Return the root of the subtree.
//
// LintCode will print the subtree which root is your return node.
// It's guaranteed that there is only one subtree with maximum average.
//
// Have you met this question in a real interview?
// Example
// Given a binary tree:
//
//      1
//    /   \
//  -5     11
//  / \   /  \
// 1   2 4    -2
// return the node 11.
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
 class resultType {
        public int sum;
        public int size;
        public resultType(int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
    }

public class Solution {
    /**
     * @param root the root of binary tree
     * @return the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        // Write your code here
        maxAve(root);
        return subtree;
    }
    TreeNode subtree = null;
    resultType subtreeResult = new resultType(0, 0);

    private resultType maxAve(TreeNode root) {
        if (root == null) {
            return new resultType(0, 0);
        }
        resultType left = maxAve(root.left);
        resultType right = maxAve(root.right);
        resultType result = new resultType(
                                            root.val + left.sum + right.sum,
                                            left.size + right.size + 1);
        if (subtree == null || result.sum * subtreeResult.size > result.size * subtreeResult.sum) {
            subtree = root;
            subtreeResult = result;
        }
        return result;
    }
}
