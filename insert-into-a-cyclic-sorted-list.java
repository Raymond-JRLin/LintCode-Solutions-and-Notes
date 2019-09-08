// 599. Insert into a Cyclic Sorted List
// 中文English
// Given a node from a cyclic linked list which has been sorted, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be any single node in the list. Return the inserted new node.
//
// Example
// Example 1:
//
// Input:
// 3->5->1
// 4
// Output:
// 5->1->3->4
// Example 2:
//
// Input:
// 2->2->2
// 3
// Output:
// 3->2->2->2
// Notice
// 3->5->1 is a cyclic list, so 3 is next node of 1.
// 3->5->1 is same with 5->1->3
//


/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param node a list node in the list
     * @param x an integer
     * @return the inserted new list node
     */
    public ListNode insert(ListNode node, int x) {
        // Write your code here
        if (node == null) {
            ListNode newNode = new ListNode(x);
            newNode.next = newNode;
            return newNode;
        }
                ListNode pre = node;
        ListNode nextNode = node;
        // special case: 2->2->2, insert 3
        do {
            pre = nextNode;
            nextNode = nextNode.next;
            if (pre.val <= x && nextNode.val >= x) {
                break;
            }
            if (pre.val > nextNode.val && (x > pre.val || x < nextNode.val)) {
                break;
            }
        } while (nextNode != node);
        ListNode newNode = new ListNode(x);
        pre.next = newNode;
        newNode.next = nextNode;
        return newNode;
    }
}
