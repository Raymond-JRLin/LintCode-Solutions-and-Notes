// 157. Unique Characters
// 中文English
// Implement an algorithm to determine if a string has all unique characters.
//
// Example
// Example 1:
//
// Input: "abc_____"
// Output: false
// Example 2:
//
// Input:  "abc"
// Output: true
// Challenge
// What if you can not use additional data structures?


public class Solution {
    /**
     * @param str: a string
     * @return: a boolean
     */
    public boolean isUnique(String str) {
        // write your code here
        if (str == null || str.length() == 0) {
            return true;
        }
        int n = str.length();

        // method 1: use HashSet
        // Set<Character> set = new HashSet<>();
        // for (int i = 0; i < str.length(); i++) {
        //     char c = str.charAt(i);
        //     if (set.contains(c)) {
        //         return false;
        //     }
        //     set.add(c);
        // }
        // return true;

        // method 2:
        int[] array = new int[256];
        for (int i = 0; i < str.length(); i++) {
            int pos = str.charAt(i) - 0;
            if (array[pos] != 0) {
                return false;
            }
            array[pos]++;
        }
        return true;
    }
}
