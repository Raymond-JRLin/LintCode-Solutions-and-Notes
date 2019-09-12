// 452. Remove Linked List Elements
// 中文English
// Remove all elements from a linked list of integers that have value val.
//
// Example
// Example 1:
//
// Input: head = 1->2->3->3->4->5->3->null, val = 3
// Output: 1->2->4->5->null
// Example 2:
//
// Input: head = 1->1->null, val = 1
// Output: null


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param head a ListNode
     * @param val an integer
     * @return a ListNode
     */
    public ListNode removeElements(ListNode head, int val) {
        // Write your code here
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = new ListNode(0);
        prev = dummy;
        while (head != null) {
            if (head.val == val) {
                prev.next = head.next;
            } else {
                prev = prev.next;
            }
            head = head.next;
        }
        return dummy.next;
    }
}
