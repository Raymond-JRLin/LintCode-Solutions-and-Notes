// 491. Palindrome Number
// 中文English
// Check a positive number is a palindrome or not.
//
// A palindrome number is that if you reverse the whole number you will get exactly the same number.
//
// Example
// Example 1:
//
// Input:11
// Output:true
//
// Example 2:
//
// Input:1232
// Output:false
// Explanation:
// 1232!=2321
// Notice
// It's guaranteed the input number is a 32-bit integer, but after reversion, the number may exceed the 32-bit integer.
//


public class Solution {

    /*
     * @param num: a positive number
     * @return: true if it's a palindrome or false
     */
    public boolean palindromeNumber(int num) {
        // write your code here

        // method 1: convert string and do String Palindrome
        // String string = String.valueOf(num);
        // int i = 0;
        // int j = string.length() - 1;
        // while (i < j) {
        //     if (string.charAt(i) != string.charAt(j)) {
        //         return false;
        //     } else {
        //         i++;
        //         j--;
        //     }
        // }
        // return true;

        // method 2: use definition and get reversion of this num and compare
        if (num < 0) {
            return false;
        }
        return num == reverse(num);
    }
    private int reverse(int num) {
        int result = 0;
        while (num != 0) {
            result = result * 10 + num % 10;
            num = num / 10;
        }
        return result;
    }
};
