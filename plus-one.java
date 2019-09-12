// 407. Plus One
// 中文English
// Given a non-negative number represented as an array of digits, plus one to the number.
//
// The digits are stored such that the most significant digit is at the head of the list.
//
// Example
// Example 1:
//
// Input: [1,2,3]
// Output: [1,2,4]
// Example 2:
//
// Input: [9,9,9]
// Output: [1,0,0,0]


public class Solution {

    /*
     * @param digits: a number represented as an array of digits
     * @return: the result
     */
    public int[] plusOne(int[] digits) {
        // write your code here
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int n = digits.length;
        // 分两种情况
        if (digits[n - 1] != 9) {
            // 只有最后一位 == 9 的时候， 才需要进位， 不然就直接 + 1 返回
            digits[n - 1]++;
            return digits;
        } else {
            int count = 1;
            int pos = n - 1;
            while (pos >= 0 && digits[pos] == 9) {
                digits[pos] = 0;
                pos--;
            }
            if (pos == -1) {
                int[] result = new int[n + 1];
                result[0] = 1;
                return result;
            }  else {
                digits[pos]++; // don't forget we have one more carry
                return digits;
            }
        }

    }
};
