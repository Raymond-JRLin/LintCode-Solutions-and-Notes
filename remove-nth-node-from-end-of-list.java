// 174. Remove Nth Node From End of List
// 中文English
// Given a linked list, remove the nth node from the end of list and return its head.
//
// Example
// Example 1:
// 	Input: list = 1->2->3->4->5->null， n = 2
// 	Output: 1->2->3->5->null
//
//
// Example 2:
// 	Input:  list = 5->4->3->2->1->null, n = 2
// 	Output: 5->4->3->1->null
//
// Challenge
// Can you do it without getting the length of the linked list?
//
// Notice
// The minimum number of nodes in list is n.
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
    /**
     * @param head: The first node of linked list.
     * @param n: An integer.
     * @return: The head of linked list.
     */
    ListNode removeNthFromEnd(ListNode head, int n) {
        // write your code here
        if (head == null || head.next == null) {
            return null;
        }
        ListNode first = head;
        int count = 0;
        while (count < n) {
            first = first.next;
            count++;
        }
        if (first == null) {
            return head.next;
        }
        ListNode second = head;
        while (first.next != null) {
            second = second.next;
            first = first.next;
        }
        second.next = second.next.next;
        return head;
    }
}
