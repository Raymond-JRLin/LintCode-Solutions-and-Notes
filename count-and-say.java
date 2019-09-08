// 420. Count and Say
// 中文English
// The count-and-say sequence is the sequence of integers beginning as follows:
//
// 1, 11, 21, 1211, 111221, ...
//
// 1 is read off as "one 1" or 11.
//
// 11 is read off as "two 1s" or 21.
//
// 21 is read off as "one 2, then one 1" or 1211.
//
// Given an integer n, generate the nth sequence.
//
// Example
// Example 1:
//
// Input: 1
// Output: "1"
// Example 2:
//
// Input: 5
// Output: "111221"
// Notice
// The sequence of integers will be represented as a string.
//


public class Solution {
    /**
     * @param n the nth
     * @return the nth sequence
     */
    public String countAndSay(int n) {
        // Write your code here
        String string = new String("1");
        int count = 1;
        while (count < n) {
            String temp = new String("");
            char prev = string.charAt(0);
            int repeat = 1;
            for (int i = 1; i < string.length(); i++) {
                if (string.charAt(i) == prev) {
                    repeat++;
                } else {
                    temp += String.valueOf(repeat) + String.valueOf(prev);
                    repeat = 1;
                    prev = string.charAt(i);
                }
            }
            temp += String.valueOf(repeat) + String.valueOf(prev);
            count++;
            string = temp;
        }
        return string;
    }
}
