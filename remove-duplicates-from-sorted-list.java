// 112. Remove Duplicates from Sorted List
// 中文English
// Given a sorted linked list, delete all duplicates such that each element appear only once.
//
// Example
// Example 1:
// 	Input:  null
// 	Output: null
//
//
// Example 2:
// 	Input:  1->1->2->null
// 	Output: 1->2->null
//
// Example 3:
// 	Input:  1->1->2->3->3->null
// 	Output: 1->2->3->null
//
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
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of linked list
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = head;
        while (head != null) {
            if (node.val != head.val) {
                node.next = head;
                node = node.next;
                head = head.next;
            } else {
                head = head.next;
            }
        }
        node.next = head;
        return dummy.next;
    }
}
