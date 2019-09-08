// 219. Insert Node in Sorted Linked List
// 中文English
// Insert a node in a sorted linked list.
//
// Example
// Example 1:
//
// Input: head = 1->4->6->8->null, val = 5
// Output: 1->4->5->6->8->null
// Example 2:
//
// Input: head = 1->null, val = 2
// Output: 1->2->null


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
    /*
     * @param head: The head of linked list.
     * @param val: An integer.
     * @return: The head of new linked list.
     */
    public ListNode insertNode(ListNode head, int val) {
        // write your code here
        if (head == null) {
            ListNode root = new ListNode(val);
            return root;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.val <= val) {
            head = head.next;
            // next = next.next;
        }
        ListNode inserted = new ListNode(val);
        // ListNode temp = head.next;
        // head.next = inserted;
        // inserted.next = temp;
        // or we can do following directly
        inserted.next = head.next;
        head.next = inserted;
        return dummy.next;
    }
}
