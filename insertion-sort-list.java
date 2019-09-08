// 173. Insertion Sort List
// 中文English
// Sort a linked list using insertion sort.
//
// Example
// Example 1:
// 	Input: 0->null
// 	Output: 0->null
//
//
// Example 2:
// 	Input:  1->3->2->0->null
// 	Output :0->1->2->3->null
//
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
     * @return: The head of linked list.
     */
    public ListNode insertionSortList(ListNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        // dummy.next = head;
        // 在原有的链上操作很麻烦， 新建一个 dummy， 以它开头重新建 list
        while (head != null) {
            ListNode curr = dummy;
            while (curr.next != null && curr.next.val < head.val) {
                curr = curr.next;
            }
            // 出来的时候要么到了 dummy 新链的末尾， 要么 curr 是最后一个比 head 小的 node， 其实这两种情况是一致的， 如果到了末尾， 也就意味着前面的都比 head 小
            // 在改变之前， 要把 head 下一个先存一下
            ListNode temp = head.next;
            // 将 head 连上 curr 后面的
            head.next = curr.next;
            // 将 curr 接上 head -> insert head to new list
            curr.next = head;
            // move head to next node
            head = temp;
        }
        return dummy.next;
    }
}
