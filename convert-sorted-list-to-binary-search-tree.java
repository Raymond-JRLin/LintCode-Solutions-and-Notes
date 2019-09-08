// 106. Convert Sorted List to Binary Search Tree
// 中文English
// Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
//
// Example
// Example 1:
// 	Input: array = 1->2->3
// 	Output:
// 		 2
// 		/ \
// 		1  3
//
// Example 2:
// 	Input: 2->3->6->7
// 	Output:
// 		 3
// 		/ \
// 	       2   6
// 		     \
// 		      7
//
// 	Explanation:
// 	There may be multi answers, and you could return any of them.


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
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    public TreeNode sortedListToBST(ListNode head) {
        // write your code here
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            TreeNode root = new TreeNode(head.val);
            return root;
        }
        // List 不像 array 那样可以根据 index 索引， 所以要从前往后扫。


        // method 1: 我们可以尝试快慢指针， 每次都找到中间的 node， 然后递归查找左边和右边
        return slowFastBuild(head, null);


        // method 2: 对于构建N节点的binary tree来说理论上算法复杂度最少只能到 O(N)， 因为生成N个节点本身就需要 O(N)。 要达到O(N) 复杂度的算法， 就不能反复遍历来查找中间节点， 而只能顺序边访问 linked list 边构建树。 这里的关键是利用构建 left subtree 的递归， 来找寻 middle 节点。
        // curr = head;
        // calculate the total length of this List
        // int len = 0;
        // while (head != null) {
        //     len++;
        //     head = head.next;
        // }
        // return helper(0, len - 1);
    }
    // 这里用一个全局变量是因为由 C++ 的 ref 参考而来， 但是 cpp 是引用， 所以如果把 curr 放入 helper 往下传的时候会更改， 但是 java 并不会， 譬如 left 返回的时候 curr 可能已经是 left 了， 然而在 root 这一层 curr 并没有被改变， 所以会发生错误使得 root 仍然和 left 一样， e.g. -1->0->2->null
    public ListNode curr;
    private TreeNode helper(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode left = helper(start, mid - 1);
        TreeNode root = new TreeNode(curr.val);
        curr = curr.next;
        TreeNode right = helper(mid + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }

    private TreeNode slowFastBuild(ListNode start, ListNode end) {
        if (start == end) {
            return null;
        }
        ListNode slow = start;
        ListNode fast = start;
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = slowFastBuild(start, slow);
        root.right = slowFastBuild(slow.next, end);
        return root;
    }
}
