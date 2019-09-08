// 380. Intersection of Two Linked Lists
// 中文English
// Write a program to find the node at which the intersection of two singly linked lists begins.
//
// Example
// Example 1:
//
// Input:
// 	A:          a1 → a2
// 	                   ↘
// 	                     c1 → c2 → c3
// 	                   ↗
// 	B:     b1 → b2 → b3
// Output: c1
// Explanation ：begin to intersect at node c1.
// Example 2:
//
// Input:
// Intersected at 6
// 1->2->3->4->5->6->7->8->9->10->11->12->13->null
// 6->7->8->9->10->11->12->13->null
// Output: Intersected at 6
// Explanation：begin to intersect at node 6.
// Challenge
// Your code should preferably run in O(n) time and use only O(1) memory.
//
// Notice
// If the two linked lists have no intersection at all, return null.
// The linked lists must retain their original structure after the function returns.
// You may assume there are no cycles anywhere in the entire linked structure.


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
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Write your code here
        if (headA == null || headB == null) {
            return null;
        }
        ListNode node = headA;
        while (node.next != null) {
            node = node.next;
        }
        // found the tail node of A
        node.next = headB;
        ListNode slow = headA;
        ListNode fast = headA;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode intersection = headA;
                while (intersection != slow) {
                    intersection = intersection.next;
                    slow = slow.next;
                }
                node.next = null;
                return intersection;
            }
        }
        return null;
    }
}
