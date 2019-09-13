// 245. Subtree
// 中文English
// You have two very large binary trees: T1, with millions of nodes, and T2, with hundreds of nodes. Create an algorithm to decide if T2 is a subtree of T1.
//
// Example
// Example 1:
//
// Input：{1,2,3,#,#,4},{3,4}
// Output：true
// Explanation：
// T2 is a subtree of T1 in the following case:
//            1                3
//           / \              /
//     T1 = 2   3      T2 =  4
//             /
//            4
// Example 2:
//
// Input：{1,2,3,#,#,4},{3,#,4}
// Output：false
// Explanation：
// T2 isn't a subtree of T1 in the following case:
//
//            1               3
//           / \               \
//     T1 = 2   3       T2 =    4
//             /
//            4
// Notice
// A tree T2 is a subtree of T1 if there exists a node n in T1 such that the subtree of n is identical to T2. That is, if you cut off the tree at node n, the two trees would be identical.
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
     * @param T1, T2: The roots of binary tree.
     * @return: True if T2 is a subtree of T1, or false.
     */
    public boolean isSubtree(TreeNode T1, TreeNode T2) {
        // write your code here
        if (T2 == null) {
            return true;
        } //put this edge case first
        if (T1 == null) {
            return false;
        }

        // if (T1 == null && T2 == null) {
        //     return true;
        // }
        // if (T1.val == T2.val && T1.left == T2.left && T1.right == T2.right) {
        //     return true;
        // }
        // TreeNode left = T1.left;
        // TreeNode right = T1.right;
        // return isSubtree(left, T2) || isSubtree(right, T2);

        return identical(T1, T2) || isSubtree(T1.left, T2) || isSubtree(T1.right, T2);
    }
    private boolean identical(TreeNode T1, TreeNode T2) {
        if (T1 == null && T2 == null) {
            return true;
        }
        if (T1 == null || T2 == null) {
            return false;
        }
        if (T1.val != T2.val) {
            return false;
        }
        return identical(T1.left, T2.left) && identical(T1.right, T2.right);
    }
}
