// 511. Swap Two Nodes in Linked List
// 中文English
// Given a linked list and two values v1 and v2. Swap the two nodes in the linked list with values v1 and v2. It's guaranteed there is no duplicate values in the linked list. If v1 or v2 does not exist in the given linked list, do nothing.
//
// Example
// Example 1:
//
// Input: 1->2->3->4->null, v1 = 2, v2 = 4
// Output: 1->4->3->2->null
// Example 2:
//
// Input: 1->null, v1 = 2, v2 = 1
// Output: 1->null
// Notice
// You should swap the two nodes with values v1 and v2. Do not directly swap the values of the two nodes.


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
     * @param v1: An integer
     * @param v2: An integer
     * @return: a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        // write your code here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode[] first = find(head, v1); // [prev, node1]
        if (first[0] == null && first[1] == null) {
            return head;
        }
        ListNode[] second = find(head, v2); // [prev, node2]
        if (second[0] == null && second[1] == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 这里的 if 是因为我们用了另一个 method 来处理得到 1 2 指向的 node， 有可能 head 就是要处理的， 但是此时地址不一样， 要把 dummy 指向 find method 返回回来的 head
        if (first[1] == head) {
            dummy = first[0];
        }
        if (second[1] == head) {
            dummy = second[0];
        }
        // 交换的时候要注意多种情况： head 就是要交换的（在上面处理过了）， 1 与 2 连在一起， 2 在 1 的前面等
        if (second[1] == first[0]) {
            // 2 的下一个就是 1, 倒成 1 在 前， 2 在后
            ListNode[] temp = first;
            first = second;
            second = temp;
        }
        if (first[1] == second[0]) {
            // 1 的 下一个 就是 2, swap node1 and node2
            first[0].next = second[1];
            first[1].next = second[1].next;
            second[1].next = first[1];
        } else {
            // general case
            first[0].next = second[1];
            ListNode temp = first[1].next;
            first[1].next = second[1].next;
            second[0].next = first[1];
            second[1].next = temp;
        }
        return dummy.next;
    }
    private ListNode[] find(ListNode head, int target) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        ListNode[] result = new ListNode[2];
        result[0] = null;
        result[1] = null;
        while (curr != null) {
            if (curr.val == target) {
                result[0] = prev;
                result[1] = curr;
                return result;
            }
            prev = prev.next;
            curr = curr.next;
        }
        return result;
    }

}
