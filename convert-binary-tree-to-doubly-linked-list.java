// 378. Convert Binary Tree to Doubly Linked List
// 中文English
// Convert a binary tree to doubly linked list with in-order traversal.
//
// Example
// Example 1：
//
// Input:
// 	    4
// 	   / \
// 	  2   5
// 	 / \
// 	1   3
//
// Output: 1<->2<->3<->4<->5
// Example 2：
//
// Input:
// 	    3
// 	   / \
// 	  4   1
//
// Output:4<->3<->1


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
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * }
 */
class ResultType {
    DoublyListNode first;
    DoublyListNode last;
    ResultType(DoublyListNode left, DoublyListNode right) {
        first = left;
        last = right;
    }
}
public class Solution {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {
        // Write your code here
        // from example, we know question require inorder traverse
        // so we need another class to record the relation between subtree
        if (root == null) {
            return null;
        }
        return helper(root).first;
    }
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return null;
        }
        // divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        // conquer
        ResultType result = new ResultType(null, null);
        DoublyListNode node = new DoublyListNode(root.val);
        // connect left and root
        if (left == null) {
            result.first = node;
        } else {
            result.first = left.first;
            left.last.next = node;
            node.prev = left.last;
        }
        // connect root and right
        if (right == null) {
            result.last = node;
        } else {
            result.last = right.last;
            right.first.prev = node;
            node.next = right.first;
        }
        return result;
    }
}
