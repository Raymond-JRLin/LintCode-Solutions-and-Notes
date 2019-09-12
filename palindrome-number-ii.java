// 807. Palindrome Number II
// 中文English
// Determines whether a binary representation of a non-negative integer n is a palindrome
//
// Example
// Example1
//
// Input: n = 0
// Output: True
// Explanation:
// The binary representation of 0 is: 0
// Example2
//
// Input: n = 3
// Output: True
// Explanation:
// The binary representation of 3 is: 11
// Example3
//
// Input: n = 4
// Output: False
// Explanation:
// The binary representation of 4 is: 100
// Example4
//
// Input: n = 6
// Output: False
// Explanation:
// The binary representation of 6 is: 110
// Notice
// 0 <= n <= 2^32 - 1
// 0


public class Solution {
    /**
     * @param n: non-negative integer n.
     * @return: return whether a binary representation of a non-negative integer n is a palindrome.
     */
    public boolean isPalindrome(int n) {
        // Write your code here
        if (n == 0) {
            return true;
        }
        int[] array = new int[32];
        for (int i = 0; i < 32; i++) {
            array[31 - i] = (n & (1 << i)) == 0 ? 0 : 1;
        }
        int left = 0;
        // delete leading zeros
        while (array[left] == 0) {
            left++;
        }
        int right = 31;
        // 2 pointers
        while (left < right) {
            if (array[left] != array[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
