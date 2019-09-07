// 221. Add Two Numbers II
// 中文English
// You have two numbers represented by linked list, where each node contains a single digit. The digits are stored in forward order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.
//
// Example
// Example 1:
//
// Input: 6->1->7   2->9->5
// Output: 9->1->2
// Example 2:
//
// Input: 1->2->3   4->5->6
// Output: 5->7->9


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
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2
     */
    public ListNode addLists2(ListNode l1, ListNode l2) {
        // write your code here
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        String num1 = "";
        String num2 = "";
        while (l1 != null) {
            num1 = num1 + l1.val;
            l1 = l1.next;
        }
        while (l2 != null) {
            num2 = num2 + l2.val;
            l2 = l2.next;
        }
        String sum = addStrings(num1, num2);
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        for (int i = 0; i < sum.length(); i++) {
            int number = sum.charAt(i) - '0';
            head.next = new ListNode(number);
            head = head.next;
        }
        return dummy.next;
    }
    private String addStrings(String num1, String num2) {
        // Write your code here
        if (num1 == null || num1.length() == 0) {
            return num2;
        }
        if (num2 == null || num2.length() == 0) {
            return num1;
        }
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        int residue = 0;
        String result = "";
        while (len1 >= 0 || len2 >= 0) {
            if (len1 >= 0) {
                residue = num1.charAt(len1) - '0' + residue;
                len1--;
            }
            if (len2 >= 0) {
                residue += num2.charAt(len2) - '0';
                len2--;
            }
            result = residue % 10 + result;
            residue = residue / 10;
        }
        if (residue != 0) {
            return residue + result;
        } else {
            return result;
        }
    }
}
