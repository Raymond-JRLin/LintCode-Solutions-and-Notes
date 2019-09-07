// 453. Flatten Binary Tree to Linked List
// 中文English
// Flatten a binary tree to a fake "linked list" in pre-order traversal.
//
// Here we use the right pointer in TreeNode as the next pointer in ListNode.
//
// Example
// Example 1:
//
// Input:{1,2,5,3,4,#,6}
// Output：{1,#,2,#,3,#,4,#,5,#,6}
// Explanation：
//      1
//     / \
//    2   5
//   / \   \
//  3   4   6
//
// 1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6
// Example 2:
//
// Input:{1}
// Output:{1}
// Explanation：
//          1
//          1
// Challenge
// Do it in-place without any extra memory.
//
// Notice
// Don't forget to mark the left child of each node to null. Or you will get Time Limit Exceeded or Memory Limit Exceeded.


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
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // write your code here
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        if (root == null) {
            return;
        }

        stack.push(node);
        while (!stack.empty()) {
            node = stack.pop();
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
            node.left = null;
            if (stack.empty()) {
                return;
            } else {
                node.right = stack.peek();
            }
        }
    }

}
