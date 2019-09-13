// 451. Swap Nodes in Pairs
// 中文English
// Given a linked list, swap every two adjacent nodes and return its head.
//
// Example
// Example 1:
//
// Input: 1->2->3->4->null
// Output: 2->1->4->3->null
// Example 2:
//
// Input: 5->null
// Output: 5->null
// Challenge
// Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
//


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


public class Solution {
    /*
     * @param head: a ListNode
     * @return: a ListNode
     */
    public ListNode swapPairs(ListNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.next != null) {
            ListNode prev = head.next;
            ListNode curr = head.next.next;
            prev.next = curr.next;
            curr.next = prev;
            head.next = curr;
            head = prev; // prev and curr already swapped
        }
        return dummy.next;
    }
};
