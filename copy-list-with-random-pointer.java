// 105. Copy List with Random Pointer
// 中文English
// A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
//
// Return a deep copy of the list.
//
// Challenge
// Could you solve it with O(1) space?
//


/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        // write your code here
        copyList(head);
        copyRandomPointer(head);
        return splitList(head);
    }
    private void copyList(RandomListNode head) {
        // RandomListNode newHead = head;
        // above is wrong, we need to do a deep-copy
        // above will change original List
        while (head != null) {
            RandomListNode newHead = new RandomListNode(head.label);
            newHead.random = head.random;
            newHead.next = head.next;
            head.next = newHead;
            head = head.next.next;
        }
        // then the original List changed to a new List, for example:
        //  1 -> 2 -> 3 -> 4
        //  1 -> 1'-> 2 -> 2'-> 3 -> 3'-> 4 -> 4'
    }
    private void copyRandomPointer(RandomListNode head) {
        while (head != null) { // while condition cannot be head.next.next != null; because if tail has the random pointer, then it cannot be reached
            if (head.next.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }
    // we need to restore original list and slpit new list
    // remember: we cannot leave original list alone, we should restore it as origin
    private RandomListNode splitList(RandomListNode head) {
        RandomListNode newHead = head.next;
        RandomListNode temp = head.next;
        while (head != null) {
            head.next = temp.next;
            if (temp.next != null) {

                temp.next = head.next.next;
            }

            temp = temp.next;
            head = head.next;
        }
        return newHead;
    }
}
