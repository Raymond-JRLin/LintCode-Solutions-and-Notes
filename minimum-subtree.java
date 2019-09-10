// 596. Minimum Subtree
// 中文English
// Given a binary tree, find the subtree with minimum sum. Return the root of the subtree.
//
// Example
// Example 1:
//
// Input:
// {1,-5,2,1,2,-4,-5}
// Output:1
// Explanation:
// The tree is look like this:
//      1
//    /   \
//  -5     2
//  / \   /  \
// 1   2 -4  -5
// The sum of whole tree is minimum, so return the root.
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
// It's guaranteed that there is only one subtree with minimum sum and the given binary tree is not an empty tree.
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
     * @param root the root of binary tree
     * @return the root of the minimum subtree
     */
    private TreeNode subtree = null; //create a null node to store the root of the subtree of the answer
    private int maxSum = Integer.MAX_VALUE; //recorde max value
    public TreeNode findSubtree(TreeNode root) {
        //Write your code here
        countSum(root);
        return subtree;

    }

    private int countSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = root.val + countSum(root.left) + countSum(root.right);
        if (sum < maxSum) {
            maxSum = sum;
            subtree = root;
        }
        return sum;
    }

        //     if (root == null) {
        //     return null;
        // }
        // int left = findSubtree(root.left).val;
        // int right = findSubtree(root.right).val;
        // int sum = left + right + root.val;
        // if (sum < maxSum) {
        //     maxSum = sum;
        //     subtree = root;
        // }
        // return subtree;
}
