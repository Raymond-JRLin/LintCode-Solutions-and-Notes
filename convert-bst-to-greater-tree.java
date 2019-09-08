// 661. Convert BST to Greater Tree
// 中文English
// Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
//
// Example
// Example 1:
//
// Input : {5,2,13}
//               5
//             /   \
//            2     13
// Output : {18,20,13}
//              18
//             /   \
//           20     13
// Example 2:
//
// Input : {5,3,15}
//               5
//             /   \
//            3     15
// Output : {20,23,15}
//              20
//             /   \
//           23     15


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
     * @return the new root
     */
    public int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        // Write your code here
        if (root == null) {
            return root;
        }
        // because it's a BST, we should start from the right-most subtree/leaf, since we should add all-path sum to smaller nodes

        // method 1:
        // so we should do recursion and use a global variable (or make it as parameter with method) to record sum all the way
        // helper(root);
        // return root;

        // method 2: make this method as recursion method and global variable
        convertBST(root.right);
        root.val += sum;
        sum = root.val;
        convertBST(root.left);
        return root;
    }
    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.right); // keep doing recursion until the right-most
        sum += root.val; // get the sum from right
        root.val = sum; // because we use exit if condition with null root, so we should not add current root val again
        helper(root.left); // do left after its root(father) and all larger nodes
    }
}
