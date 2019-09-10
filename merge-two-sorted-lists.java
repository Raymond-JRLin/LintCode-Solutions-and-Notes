// 165. Merge Two Sorted Lists
// 中文English
// Merge two sorted (ascending) linked lists and return it as a new sorted list. The new sorted list should be made by splicing together the nodes of the two lists and sorted in ascending order.
//
// Example
// Example 1:
// 	Input: list1 = null, list2 = 0->3->3->null
// 	Output: 0->3->3->null
//
//
// Example 2:
// 	Input:  list1 =  1->3->8->11->15->null, list2 = 2->null
// 	Output: 1->2->3->8->11->15->null
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
     * @param ListNode l1 is the head of the linked list
     * @param ListNode l2 is the head of the linked list
     * @return: ListNode head of linked list
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write your code here

        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        ListNode i = l1;
        ListNode j = l2;
        while (i != null && j != null) {
            if (i.val <= j.val) {
                node.next = i;
                node = i;
                i = i.next;
            } else {
                node.next = j;
                node = j;
                j = j.next;
            }
        }
        if (i != null) {
            node.next = i;
        }
        if (j != null) {
            node.next = j;
        }
        return dummy.next;
    }
}
