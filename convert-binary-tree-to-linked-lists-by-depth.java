// 242. Convert Binary Tree to Linked Lists by Depth
// 中文English
// Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
//
// Example
// Example 1:
//
// Input: {1,2,3,4}
// Output: [1->null,2->3->null,4->null]
// Explanation:
//         1
//        / \
//       2   3
//      /
//     4
// Example 2:
//
// Input: {1,#,2,3}
// Output: [1->null,2->null,3->null]
// Explanation:
//     1
//      \
//       2
//      /
//     3


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
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param root the root of binary tree
     * @return a lists of linked list
     */
    public List<ListNode> binaryTreeToLists(TreeNode root) {
        // Write your code here
        List<ListNode> result = new ArrayList<ListNode>();
        if (root == null) {
            return result;
        }

        helper(result, root);
        return result;
    }
    private void helper(List<ListNode> result, TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {

            ListNode dummy = new ListNode(0);
            // dummy.next = null;
            ListNode head = dummy;
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                TreeNode tNode = queue.poll();
                ListNode lNode = new ListNode(tNode.val);
                // lNode.next = null;
                head.next = lNode;
                head = head.next;
                if (tNode.left != null) {
                    queue.offer(tNode.left);
                }
                if (tNode.right != null) {
                    queue.offer(tNode.right);
                }
            }
            result.add(dummy.next);
        }
    }
}
