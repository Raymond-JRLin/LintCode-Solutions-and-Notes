// 96. Partition List
// 中文English
// Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//
// You should preserve the original relative order of the nodes in each of the two partitions.
//
// Example
// Example 1:
//
// Input:  list = null, x = 0
// Output: null
// Explanation: The empty list Satisfy the conditions by itself.
// Example 2:
//
// Input:  list = 1->4->3->2->5->2->null, x = 3
// Output: 1->2->2->4->3->5->null
// Explanation:  keep the original relative order of the nodes in each of the two partitions.


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
     * @param x: an integer
     * @return: a ListNode
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here
        if (head == null || head.next == null) {
            return head;
        }
        // ListNode dummy = new ListNode(0);
        // dummy.next = head;
        // while (head != null) {

        // }
        // if we just swap, it's difficult, because it's ListNode list, where we can only remember next node, instead of array
        // so we can split original list into 2 lists, one contains nodes less than x only and another one contains nodes >= x
        ListNode smallDummy = new ListNode(0);
        ListNode largeDummy = new ListNode(0);
        ListNode small = smallDummy;
        ListNode large = largeDummy;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                // small = small.next;
                small = head;
            } else {
                large.next = head;
                // large = large.next;
                large = head;
            }
            head = head.next;
        }
        // make last node's next node in larger list be null, if it is not in the end in original list, it would point other nodes before
        large.next = null;
        // link 2 lists
        small.next = largeDummy.next;
        return smallDummy.next;
    }
}
