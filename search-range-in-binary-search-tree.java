// 11. Search Range in Binary Search Tree
// 中文English
// Given a binary search tree and a range [k1, k2], return node values within a given range in ascending order.
//
// Example
// Example 1:
//
// Input：{5},6,10
// Output：[]
//         5
// it will be serialized {5}
// No number between 6 and 10
// Example 2:
//
// Input：{20,8,22,4,12},10,22
// Output：[12,20,22]
// Explanation：
//         20
//        /  \
//       8   22
//      / \
//     4   12
// it will be serialized {20,8,22,4,12}
// [12,20,22] between 10 and 22


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
    /*
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        helper(root, k1, k2, result);
        return result;
    }
    private void helper(TreeNode root, int k1, int k2, List<Integer> result) {
        if (root == null) {
            return;
        }
        // in-order traversal
        if (root.val > k1) {
            helper(root.left, k1, k2, result);
        }
        if (root.val >= k1 && root.val <= k2) {
            result.add(root.val);
        }
        if (root.val < k2) {
            helper(root.right, k1, k2, result);
        }
    }
}
