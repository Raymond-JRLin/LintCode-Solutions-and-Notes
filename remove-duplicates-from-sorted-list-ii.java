// 113. Remove Duplicates from Sorted List II
// 中文English
// Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
//
// Example
// Example 1
//
// Input : 1->2->3->3->4->4->5->null
// Output : 1->2->5->null
// Example 2
//
// Input : 1->1->1->2->3->null
// Output : 2->3->null


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
    /*
     * @param head: head is the head of the linked list
     * @return: head of the linked list
     */
    public ListNode deleteDuplicates(ListNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return head;
        }
        // my try: use set to record what values occur
        // Set<Integer> all = new HashSet<>();
        // Set<Integer> duplicates = new HashSet<>();
        // ListNode curr = head;
        // while (curr != null) {
        //     if (all.contains(curr.val)) {
        //         duplicates.add(curr.val);
        //     } else {
        //         all.add(curr.val);
        //     }
        //     curr = curr.next;
        // }
        // ListNode dummy = new ListNode(0);
        // dummy.next = head;
        // ListNode prev = new ListNode(0);
        // prev = dummy;
        // while (head != null) {
        //     while (head != null && duplicates.contains(head.val)) {
        //         head = head.next;
        //     }
        //     prev.next = head;
        //     // use if condition to break when head reach the tail Null
        //     if (head != null) {
        //         head = head.next;
        //         prev = prev.next;
        //     }
        // }
        // return dummy.next;

        // method 2: actually we can use head, head.next and head.next.next to decide if they are duplicates because we are given a SORTED list like I
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        // 避免第一个就有重复的麻烦处理
        while (head.next != null && head.next.next != null) {
            if (head.next.val == head.next.next.val) {
                int val = head.next.val; // temporally record the duplicate value
                // 不断的跳过有重复值的 next
                while (head.next != null && head.next.val == val) {
                    head.next = head.next.next;
                }
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }
}
