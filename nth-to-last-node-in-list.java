// 166. Nth to Last Node in List
// 中文English
// Find the nth to last element of a singly linked list.
//
// The minimum number of nodes in list is n.
//
// Example
// Example 1:
// 	Input: list = 3->2->1->5->null, n = 2
// 	Output: 1
//
//
// Example 2:
// 	Input: list  = 1->2->3->null, n = 3
// 	Output: 1
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
     * @param n: An integer.
     * @return: Nth to last node of a singly linked list.
     */
    public ListNode nthToLast(ListNode head, int n) {
        // write your code here
        if (head == null || n <= 0) {
            return null;
        }
        // 不可以用额外的 data structs， 那么就只能考虑怎么扫到 n-th to last
        // ----------|-------|----------
        //      n                  n
        // |<--------------->|
        //           |<--------------->|
        // 这两段是相等的， 所以要用 2 pointers 一前一后走
        ListNode first = head;
        int count = 1;
        while (count <= n) {
            first = first.next;
            count++;
        }
        ListNode second = head;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        return second;
    }
}
