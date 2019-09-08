// 103. Linked List Cycle II
// 中文English
// Given a linked list, return the node where the cycle begins.
//
// If there is no cycle, return null.
//
// Example
// Example 1:
//
// Input：null,no cycle
// Output：no cycle
// Explanation：
// List is null，so no cycle.
// Example 2:
//
// Input：-21->10->4->5，tail connects to node index 1
// Output：10
// Explanation：
// The last node 5 points to the node whose index is 1, which is 10, so the entrance of the ring is 10
// Challenge
// Follow up:
//
// Can you solve it without using extra space?
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
     * @return: The node where the cycle begins.
     *           if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return null;
        }
        // ListNode slow = head;
        // ListNode fast = head;
        // while (fast != null && fast.next != null) {
        //     slow = slow.next;
        //     fast = fast.next.next;
        //     if (slow == fast) {
        //         ListNode pointer = head;
        //         while (pointer != slow) {
        //             pointer = pointer.next;
        //             slow = slow.next;
        //         }
        //         return pointer;
        //     }
        // }
        // return null;

        // or
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = slow.next;
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return slow;
    }
}
