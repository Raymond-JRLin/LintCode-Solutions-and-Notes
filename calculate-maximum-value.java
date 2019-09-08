// 719. Calculate Maximum Value
// 中文English
// Given a string of numbers, write a function to find the maximum value from the string, you can add a + or * sign between any two numbers.
//
// Example
// Example1
//
// Input:  str = "01231"
// Output: 10
// Explanation:
// ((((0 + 1) + 2) * 3) + 1) = 10 we get the maximum value 10
// Example2
//
// Input:  str = "891"
// Output: 73
// Explanation:
// As 8 * 9 * 1 = 72 and 8 * 9 + 1 = 73 so 73 is maximum.


public class Solution {
    /*
     * @param : the given string
     * @return: the maximum value
     */
    public int calcMaxValue(String str) {
        // write your code here
        if (str == null || str.length() == 0) {
            return 0;
        }
        int result = str.charAt(0) - '0';
        for (int i = 1; i < str.length(); i++) {
            int val = str.charAt(i) - '0';
            if (val <= 1 || result <= 1) {
                result += val;
            } else {
                result *= val;
            }
        }
        return result;
    }
};
