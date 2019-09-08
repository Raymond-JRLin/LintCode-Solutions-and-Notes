// 102. Linked List Cycle
// 中文English
// Given a linked list, determine if it has a cycle in it.
//
//
//
// Example
// 	Example 1:
// 		Input: 21->10->4->5,  then tail connects to node index 1(value 10).
// 		Output: true
//
// 	Example 2:
// 		Input: 21->10->4->5->null
// 		Output: false
//
// 	```
// Challenge
// Follow up:
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
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {
        // write your code here
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next; // 是为了能够进入while循环
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null || fast.next == null) {
                return false;
            }

        }
        return true;

        // ListNode slow = head;
        // ListNode fast = head;
        // while(fast != null && fast.next != null) {
        //     slow = slow.next;          // 1 hop
        //     fast = fast.next.next;     // 2 hops
        //     if (slow == fast) {  // fast caught up to slow, so there is a loop
        //         return true;
        //     }
        // }
        // return false;
    }
}
