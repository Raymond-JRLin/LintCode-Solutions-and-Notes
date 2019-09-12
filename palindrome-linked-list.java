// 223. Palindrome Linked List
// 中文English
// Implement a function to check if a linked list is a palindrome.
//
// Example
// Example 1:
//
// Input: 1->2->1
// output: true
// Example 2:
//
// Input: 2->2->1
// output: false
// Challenge
// Could you do it in O(n) time and O(1) space?
//


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
     * @param head: A ListNode.
     * @return: A boolean.
     */
    public boolean isPalindrome(ListNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return true;
        }
        // linked list is different from array, we cannot use 2 pointers of head and tail to scan at the same time, we can only go starting at head

        // my try: scan this linked list to copy ListNode values into a String or list then check as traditional palindrome
        // List<Integer> list = new ArrayList<>();
        // StringBuilder sb = new StringBuilder();
        // while (head != null) {
        //     // list.add(head.val);
        //     sb.append(head.val);
        //     head = head.next;
        // }
        // String str = sb.toString();
        // int left = 0;
        // // int right = list.size() - 1;
        // int right = str.length() - 1;
        // while (left < right) {
        //     // if (list.get(left) != list.get(right)) {
        //     if (str.charAt(left) != str.charA(right)) {
        //         return false;
        //     }
        // }
        // return true;
        // I think it is correct but TLE

        // 所以只能扫一遍这个 linked list， 但是因为不能访问坐标， 只能从头到尾扫
        // 其实比较 palindrome 本质上就是比较前半部分和后半部分是否一致， which 是传统的比较方式， 那么可不可以借助某种 data structure 存下来在扫的过程中的前半部分， 然后去比较前半部分和后半部分呢？
        // Stack 非常适合， 因为是先进后出， 所以把前半部分存下来， 在比较后半部分的时候， 顺序会一致
        // 那么怎么找前半段和后半段呢， 也就是如何找中点？ 在 linked list 中有一种手段是： 快慢指针
        // method 1: use Stack and 2 pointers, O(n) time and O(n) space
        // step 1: use slow and fast 2 pointers to find the mid-point, at the same time, store the values of first half part into Stack
        // Stack<Integer> stack = new Stack<>();
        // ListNode slow = head;
        // ListNode fast = head;
        // while (fast != null && fast.next != null) {
        //     stack.push(slow.val); // 存前半段的 value
        //     slow = slow.next;
        //     fast = fast.next.next;
        // }
        // // step 2: find the correct starting position of the 2nd half part
        // // 如果 fast 到了 null， 说明是偶数个， 那么可以直接比； 如果是 fast.next 为 null 退出的， 那说明有效 ListNode 是奇数个， slow 需要跳过当前这个没有比较对象的中点
        // if (fast != null && fast.next == null) {
        //     // 注意使用了 next 需先判断存在性， 不然会有空指针报错
        //     slow = slow.next;
        // }
        // // step 3: traverse the 2nd half part and compare with values in Stack
        // while (slow != null) {
        //     if (slow.val != stack.pop()) {
        //         return false;
        //     }
        //     slow = slow.next;
        // }
        // return true;

        // method 2: O(n) time and O(1) space for challenge
        // 要求 O(1) 的 space， 所以没办法使用任何数据结构， 但其实我们看 stack， 用它是因为它具备了先进后出的特性， 使得我们在扫后半部分的时候， 能够按照对称位置 pop 出来比较的元素， 即从后往前， 那么我们就考虑如何操作链表也可以达到从后往前这么样的顺序： 翻转链表 - reverse linked list
        // 同样我们也要先找中点， 在找中点的过程中不好翻转前半部分， 因此可以在找到中点之后， 翻转后半部分， 然后比较
        // step 1: use 2 pointers to find the mid-point
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // step 2: find the correct starting position of the 2nd half part
        if (fast != null && fast.next == null) {
            slow = slow.next; // slow now is the start of the 2nd half part
        }
        // step 3: reverse the 2nd half part
        ListNode prev = null;
        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }
        // step 4: compare 2 parts
        while (prev != null && head != null) {
            if (prev.val != head.val) {
                return false;
            }
            prev = prev.next;
            head = head.next;
        }
        return true;
    }
}
