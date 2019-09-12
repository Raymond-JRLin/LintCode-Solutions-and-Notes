// 8. Rotate String
// 中文English
// Given a string(Given in the way of char array) and an offset, rotate the string by offset in place. (rotate from left to right).
//
// Example
// Example 1:
//
// Input: str="abcdefg", offset = 3
// Output: str = "efgabcd"
// Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "efgabcd".
// Example 2:
//
// Input: str="abcdefg", offset = 0
// Output: str = "abcdefg"
// Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "abcdefg".
// Example 3:
//
// Input: str="abcdefg", offset = 1
// Output: str = "gabcdef"
// Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "gabcdef".
// Example 4:
//
// Input: str="abcdefg", offset =2
// Output: str = "fgabcde"
// Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "fgabcde".
// Example 5:
//
// Input: str="abcdefg", offset = 10
// Output: str = "efgabcd"
// Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "efgabcd".
// Challenge
// Rotate in-place with O(1) extra memory.
//
// Notice
// offset >= 0
// the length of str >= 0
//


public class Solution {
    /**
     * @param str: an array of char
     * @param offset: an integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        // write your code here
        // same to recover rotated sorted array
        // 三步翻转法
        if (offset == 0 || offset == str.length || str == null || str.length == 0) {
            return;
        }
        int n = str.length;
        // offset may exceeds n
        offset = offset % n;
        reverse(str, 0, n - offset - 1);
        reverse(str, n - offset, n - 1);
        reverse(str, 0 , n - 1);
    }
    private void reverse(char[] str, int start, int end) {
        while (start <= end) {
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
    }
}
