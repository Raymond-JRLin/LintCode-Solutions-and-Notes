// 68. Binary Tree Postorder Traversal
// 中文English
// Given a binary tree, return the postorder traversal of its nodes' values.
//
// Example
// Example 1:
//
// Input：{1,2,3}
// Output：[2,3,1]
// Explanation:
//    1
//   / \
//  2   3
//  it will be serialized {1,2,3}
// Post order traversal
// Example 2:
//
// Input：{1,#,2,3}
// Output：[3,2,1]
// Explanation:
// 1
//  \
//   2
//  /
// 3
// it will be serialized {1,#,2,3}
// Post order traversal
// Challenge
// Can you do it without recursion?
//
// Notice
// The first data is the root node, followed by the value of the left and right son nodes, and "#" indicates that there is no child node.
// The number of nodes does not exceed 20.


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
     * @return: Postorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        // recursion
        ArrayList<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        results.addAll(postorderTraversal(root.left));
        results.addAll(postorderTraversal(root.right));
        results.add(root.val);
        return results;
    }
}
