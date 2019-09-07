// 66. Binary Tree Preorder Traversal
// 中文English
// Given a binary tree, return the preorder traversal of its nodes' values.
//
// Example
// Example 1:
//
// Input：{1,2,3}
// Output：[1,2,3]
// Explanation:
//    1
//   / \
//  2   3
// it will be serialized {1,2,3}
// Preorder traversal
// Example 2:
//
// Input：{1,#,2,3}
// Output：[1,2,3]
// Explanation:
// 1
//  \
//   2
//  /
// 3
// it will be serialized {1,#,2,3}
// Preorder traversal
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
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
    //     // write your code here
    //     ArrayList<Integer> result = new ArrayList<Integer>();
    //     if (root == null) {
    //         return result;
    //     }
    //     //ArrayList<Integer> path = new ArrayList<Integer>();
    //     result.add(root.val);
    //     helper(root, result);
    //     return result;
    // }
    // private void helper(TreeNode root, ArrayList<Integer> result) {
    //     if (root.left == null && root.right == null) {
    //         // result.add(new ArrayList<Integer>(path));
    //         return;
    //     }
    //     if (root.left != null) {
    //         result.add(root.left.val);
    //         helper(root.left, result);
    //         //result.remove(result.size() - 1);
    //     }
    //     if (root.right != null) {
    //         result.add(root.right.val);
    //         helper(root.right, result);
    //         //result.remove(result.size() - 1);
    //     }
            // ArrayList<Integer> result = new ArrayList<>();
            // Stack<TreeNode> stack = new Stack<>();
            // if (root == null) {
            //   return result;
            //   }

            // stack.push(root);
            // while(!stack.empty()) {
            //     TreeNode current = stack.pop();
            //     result.add(current.val);
            //     if (current.right != null) {
            //         stack.push(current.right);
            //     }
            //     if (current.left != null) {
            //         stack.push(current.left);
            //     }
            // }
            // return result;

        ArrayList<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        results.add(root.val);
        results.addAll(preorderTraversal(root.left));
        results.addAll(preorderTraversal(root.right));
        return results;
    }
}
