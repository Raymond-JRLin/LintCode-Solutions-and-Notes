// 595. Binary Tree Longest Consecutive Sequence
// 中文English
// Given a binary tree, find the length of the longest consecutive sequence path.
//
// The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
//
// Example
// Example 1:
//
// Input:
//    1
//     \
//      3
//     / \
//    2   4
//         \
//          5
// Output:3
// Explanation:
// Longest consecutive sequence path is 3-4-5, so return 3.
// Example 2:
//
// Input:
//    2
//     \
//      3
//     /
//    2
//   /
//  1
// Output:2
// Explanation:
// Longest consecutive sequence path is 2-3,not 3-2-1, so return 2.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    int longest = 0;
    public int longestConsecutive(TreeNode root) {
        // Write your code here

        helper(root);
        return longest;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        int length = 1;
        if (root.right != null && root.right.val == root.val + 1) {
            length = Math.max(length, right + 1);
        }
        if (root.left != null && root.left.val == root.val + 1) {
            length = Math.max(length, left + 1);
        }
        if (length > longest) {
            longest = length;
        }
        return length;
    }
}
