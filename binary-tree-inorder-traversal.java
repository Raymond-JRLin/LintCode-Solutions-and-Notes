// 67. Binary Tree Inorder Traversal
// 中文English
// Given a binary tree, return the inorder traversal of its nodes' values.
//
// Example
// Example 1:
//
// Input：{1,2,3}
// Output：[2,1,3]
// Explanation:
//    1
//   / \
//  2   3
// it will be serialized {1,2,3}
// Inorder Traversal
// Example 2:
//
// Input：{1,#,2,3}
// Output：[1,3,2]
// Explanation:
// 1
//  \
//   2
//  /
// 3
// it will be serialized {1,#,2,3}
// Inorder Traversal
// Challenge
// Can you do it without recursion?
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
     * @param root: The root of binary tree.
     * @return: Inorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        // ArrayList<Integer> result = new ArrayList<>();
        // Stack<TreeNode> stack = new Stack<>();
        // TreeNode current = root;
        // while (current != null || !stack.empty()) {
        //     while (current != null) {
        //         stack.add(current);
        //         current = current.left;
        //     }
        //     current = stack.peek();
        //     stack.pop();
        //     result.add(current.val);
        //     current = current.right;
        // }
        // return result;

        ArrayList<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        results.addAll(inorderTraversal(root.left));
        results.add(root.val);
        results.addAll(inorderTraversal(root.right));

        return results;
    }
}
