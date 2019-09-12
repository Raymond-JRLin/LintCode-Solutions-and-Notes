// 36. Reverse Linked List II
// 中文English
// Reverse a linked list from position m to n.
//
// Example
// Example 1:
//
// Input: 1->2->3->4->5->NULL, m = 2 and n = 4,
// Output: 1->4->3->2->5->NULL.
// Example 2:
//
// Input: 1->2->3->4->NULL, m = 2 and n = 3,
// Output: 1->3->2->4->NULL.
// Challenge
// Reverse it in-place and in one-pass
//
// Notice
// Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
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
     * @oaram m and n
     * @return: The head of the reversed ListNode
     */
    public ListNode reverseBetween(ListNode head, int m , int n) {
        // write your code
        if (head == null) {
            return null;
        }
        if (m == n) {
            return head;
        }
        // ListNode oldHead = head;
        // ListNode preConnect = oldHead;
        // int i = 1;
        // while (i < m) {
        //     preConnect = oldHead;
        //     oldHead = oldHead.next;
        //     i++;
        // } // got the start ListNode
        // ListNode oldTail = oldHead;
        // ListNode nextConnect = oldTail;
        // while (i < n) {
        //     oldTail = oldTail.next;
        //     nextConnect = oldTail.next;
        //     i++;
        // }
        // oldTail.next = null;
        // ListNode newHead = reverseListNode(oldHead);
        // if (oldHead != head) {
        //     preConnect.next = newHead;
        //     oldHead.next = nextConnect;
        //     return head;
        // } else {
        //     oldHead.next = nextConnect;
        //     return newHead;
        // }
        // above is my code, which is not good, following is written referring from answer
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy; // in order to find the node just 1 previous than the first node need to be reversed
        int i = 1;
        // find the node previous of m
        while (i < m) {
            head = head.next;
            i++;
        }
        ListNode mNode = head.next; // find the first node to be reversed
        ListNode nNode = mNode; // in order to enter for loop to find the next node to be reversed
        ListNode tail = nNode.next; // first node after n
        for (int j = i; j < n; j++) {
            ListNode temp = tail.next;
            tail.next = nNode; //reverse
            nNode = tail; // move forward
            tail = temp;
        }
        // reconnection of new head and tail
        head.next = nNode;
        mNode.next = tail;
        return dummy.next;
    }
    // private ListNode reverseListNode(ListNode head) {
    //     ListNode prev = null;
    //     while (head != null) {
    //         ListNode temp = head.next;
    //         head.next = prev;
    //         prev = head;
    //         head = temp;
    //     }
    //     return prev;
    // }
}
