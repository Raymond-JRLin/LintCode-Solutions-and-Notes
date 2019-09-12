// 99. Reorder List
// 中文English
// Given a singly linked list L: L0 → L1 → … → Ln-1 → Ln
//
// reorder it to: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
//
// Example
// Example 1:
// 	Input:  1->2->3->4->null
// 	Output: 1->4->2->3->null
//
// Example 2:
// 	Input: 1->2->3->4->5->null
// 	Output: 1->5->2->4->3->null
//
// Challenge
// Can you do this in-place without altering the nodes' values?
//


/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param head: The head of linked list.
     * @return: nothing
     */
    public void reorderList(ListNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return;
        }
        // my try: cut the List to 1/2, and reverse the second half, and connect one by one from both head till null
        // 1: find the mid-point of List
        ListNode slow = head;
        ListNode fast = head;
        ListNode last = head;
        while (fast != null && fast.next != null) {
            last = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            // List has odd nodes, move slow to next
            last = slow;
            slow = slow.next;
        }
        last.next = null;
        // reverse the 2nd half
        ListNode curr = slow;
        ListNode prev = null;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        // reconnect one by one
        ListNode first = head;
        ListNode second = prev;
        while (first != null && second != null) {
            ListNode headNext = first.next;
            ListNode secondNext = second.next;
            first.next = second;
            second.next = headNext;
            first = headNext;
            second = secondNext;
        }

    }
}
