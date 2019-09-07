// 167. Add Two Numbers
// 中文English
// You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.
//
// Example
// Example 1:
//
// Input: 7->1->6->null, 5->9->2->null
// Output: 2->1->9->null
// Explanation: 617 + 295 = 912, 912 to list:  2->1->9->null
// Example 2:
//
// Input:  3->1->5->null, 5->9->2->null
// Output: 8->0->8->null
// Explanation: 513 + 295 = 808, 808 to list: 8->0->8->null


/**
 * Definition for singly-linked list.
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
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2
     */
    public ListNode addLists(ListNode l1, ListNode l2) {
        // write your code here
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        int residue = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + residue; // when l1.val + l2.val exceeded 10, than we should add 1 more to next digit
            temp.next = new ListNode(sum % 10);
            residue = sum / 10; // check if we need to add 1 more to next digit
            l1 = l1.next;
            l2 = l2.next;
            temp = temp.next;
        }
        // maybe l1 or l2 has rest to operate
        while (l1 != null) {
            int sum = l1.val + residue;
            temp.next = new ListNode(sum % 10);
            residue = sum / 10;
            l1 = l1.next;
            temp = temp.next;
        }
        while (l2 != null) {
            int sum = l2.val + residue;
            temp.next = new ListNode(sum % 10);
            residue = sum / 10;
            l2 = l2.next;
            temp = temp.next;
        }
        if (residue != 0) {
            temp.next = new ListNode(residue);
        }
        return dummy.next;
    }
}
