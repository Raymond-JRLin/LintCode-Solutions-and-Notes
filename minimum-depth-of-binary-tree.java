// 155. Minimum Depth of Binary Tree
// 中文English
// Given a binary tree, find its minimum depth.
//
// The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
//
// Example
// Example 1:
//
// Input: {}
// Output: 0
// Example 2:
//
// Input:  {1,#,2,3}
// Output: 3
// Explanation:
// 	1
// 	 \
// 	  2
// 	 /
// 	3
// it will be serialized {1,#,2,3}
// Example 3:
//
// Input:  {1,2,3,#,#,4,5}
// Output: 2
// Explanation:
//       1
//      / \
//     2   3
//        / \
//       4   5
// it will be serialized {1,2,3,#,#,4,5}


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
    public int minDepth(TreeNode root) {
        // write your code here
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        // divide
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // conquer
        int depth = Math.min(left, right);
        return depth + 1;
    }
}
