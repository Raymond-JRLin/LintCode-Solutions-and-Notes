// 372. Delete Node in a Linked List
// 中文English
// Implement an algorithm to delete a node in the middle of a singly linked list, given only access to that node.
//
// Example
// Example 1:
//
// Input:
// 1->2->3->4->null
// 3
// Output:
// 1->2->4->null
// Example 2:
//
// Input:
// 1->3->5->null
// 3
// Output:
// 1->5->null


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
     * @param node: the node in the list should be deleted
     * @return: nothing
     */
    public void deleteNode(ListNode node) {
        // write your code here
        // 既没给 head， 在 ListNode 里也没有 prev 指针， 从这道题来看无法知道欲删除节点的前一个节点， 那么也就是意味着无法改变前一个节点指向的下一个节点
        // 既然找不到前一个节点，那么也就意味着不能用通常的方法删除给定节点。从实际角度来看，我们关心的往往并不是真的删除了链表中的某个节点，而是访问链表时表现的行为就像是某个节点被删除了一样。这种另类『删除』方法就是——使用下一个节点的值覆盖当前节点的值，删除下一个节点。
        // reference: https://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/73020
        if (node == null) {
            return;
        }
        if (node.next == null) {
            node = null;
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
