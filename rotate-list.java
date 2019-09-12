// 170. Rotate List
// 中文English
// Given a list, rotate the list to the right by k places, where k is non-negative.
//
// Example
// Example 1:
//
// Input:1->2->3->4->5  k = 2
// Output:4->5->1->2->3
// Example 2:
//
// Input:3->2->1  k = 1
// Output:1->3->2


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
    /*
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        // write your code here
        if (head == null || head.next == null || k < 1) {
            return head;
        }
        // 可以看到最右边 k 个顺序不变， 而断开， 然后尾巴连到头， 所以不需要去找第 k 个在哪儿， 把首尾相连后， 继续顺着走 n - k 个即可， 但是要注意 k 可能大于总长度 n
        // ref: http://bangbingsyb.blogspot.com/2014/11/leetcode-rotate-list.html
        // http://fisherlei.blogspot.com/2013/01/leetcode-rotate-list.html

        int n = 1; // at least we have a head, to count the total length of List
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
            n++;
        }
        k = n - k % n; // set k to the node which should be point to null
        if (k == n) {
            return head;
        }
        curr.next = head; // connect tail and head
        while (k > 0) {
            curr = curr.next;
            k--;
        }
        head = curr.next; // reset the new head
        curr.next = null; // cut off the list
        return head;
    }
}
