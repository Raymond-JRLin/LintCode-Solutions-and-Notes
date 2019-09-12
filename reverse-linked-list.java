// 35. Reverse Linked List
// 中文English
// Reverse a linked list.
//
// Example
// Example 1:
//
// Input: 1->2->3->null
// Output: 3->2->1->null
// Example 2:
//
// Input: 1->2->3->4->null
// Output: 4->3->2->1->null
// Challenge
// Reverse it in-place and in one-pass
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
     * @param head: The head of linked list.
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
        // write your code here
        ListNode prev = null;
        ListNode curt = head;
        while (curt != null) {
            ListNode temp = curt.next;
            curt.next = prev;
            prev = curt;
            curt = temp;
        }
        return prev;
    }
}
