// 578. Lowest Common Ancestor III
// 中文English
// Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
// The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
// Return null if LCA does not exist.
//
// Example
// Example1
//
// Input:
// {4, 3, 7, #, #, 5, 6}
// 3 5
// 5 6
// 6 7
// 5 8
// Output:
// 4
// 7
// 7
// null
// Explanation:
//   4
//  / \
// 3   7
//    / \
//   5   6
//
// LCA(3, 5) = 4
// LCA(5, 6) = 7
// LCA(6, 7) = 7
// LCA(5, 8) = null
//
// Example2
//
// Input:
// {1}
// 1 1
// Output:
// 1
// Explanation:
// The tree is just a node, whose value is 1.
// Notice
// node A or node B may not exist in tree.
// Each node has a different value
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
     * @param root The root of the binary tree.
     * @param A and B two nodes
     * @return: Return the LCA of the two nodes.
     */
    class resultType {
        public boolean aExist, bExist;
        public TreeNode node;
        resultType(boolean aExist, boolean bExist, TreeNode node) {
            this.aExist = aExist;
            this.bExist = bExist;
            this.node = node;
        }
    }
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        resultType result = helper(root, A, B);
        if (result.aExist && result.bExist) {
            return result.node;
        } else {
            return null;
        }
    }
    public resultType helper(TreeNode root, TreeNode A, TreeNode B) {
            if(root == null) {
                return new resultType(false, false, null);
            }
            resultType rtLeft = helper(root.left, A, B);
            resultType rtRight = helper(root.right, A, B);
            boolean aExist = rtLeft.aExist || rtRight.aExist || root == A;
            boolean bExist = rtLeft.bExist || rtRight.bExist || root == B;
            if (root == A || root == B) {
                return new resultType(aExist, bExist, root);
            }
            if (rtLeft.node != null && rtRight.node != null) {
                return new resultType(aExist, bExist, root);
            }
            if (rtLeft.node != null) {
                return new resultType(aExist, bExist, rtLeft.node);
            }
            if (rtRight.node != null) {
                return new resultType(aExist, bExist, rtRight.node);
            }
            return new resultType(aExist, bExist, null);
    }

}
