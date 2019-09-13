// 469. Same Tree
// 中文English
// Check if two binary trees are identical. Identical means the two binary trees have the same structure and every identical position has the same value.
//
// Example
// Example 1:
//
// Input:{1,2,2,4},{1,2,2,4}
// Output:true
// Explanation:
//         1                   1
//        / \                 / \
//       2   2   and         2   2
//      /                   /
//     4                   4
//
// are identical.
// Example 2:
//
// Input:{1,2,3,4},{1,2,3,#,4}
// Output:false
// Explanation:
//
//         1                  1
//        / \                / \
//       2   3   and        2   3
//      /                        \
//     4                          4
//
// are not identical.


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
     * @param a, b, the root of binary trees.
     * @return true if they are identical, or false.
     */
    public boolean isIdentical(TreeNode a, TreeNode b) {
        // Write your code here
        // if (a == null && b != null || a != null && b == null || a.val != b.val) {
        //     return false;
        // }
        // at the end, we return all other non-true cases to false, so we can omit above sentence, moreover, if a or b is null, then a.val or b.val would be NullPointerException
        if (a == null && b == null) {
            return true;
        }
        // TreeNode leftA = a.left;
        // TreeNode leftB = b.left;
        // while (leftA != null && leftB != null) {
        //     if (!identical(leftA, leftB)) {
        //         return false;
        //     }
        //     leftA = leftA.left;
        //     leftB = leftB.left;
        // }
        // if (!identical(leftA, leftB)) {
        //     return false;
        // }
        // TreeNode rightA = a.right;
        // TreeNode rightB = b.right;
        // while (rightA != null && rightB != null) {
        //     if (!identical(rightA, rightB)) {
        //         return false;
        //     }
        //     rightA = rightA.left;
        //     rightB = rightB.left;
        // }
        // if (!identical(rightA, rightB)) {
        //     return false;
        // }
        // return true;
        // we should use recursion
        if (a != null && b != null) {
            TreeNode leftA = a.left;
            TreeNode leftB = b.left;
            TreeNode rightA = a.right;
            TreeNode rightB = b.right;
            return a.val == b.val && isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
        }
        return false;
    }
    // private boolean identical(TreeNode a, TreeNode b) {
    //     if (a == null && b != null) {
    //         return false;
    //     }
    //     if (a != null && b == null) {
    //         return false;
    //     }
    //     if (a == null && b == null) {
    //         return true;
    //     }
    //     return a.val == b.val;
    // }
}
