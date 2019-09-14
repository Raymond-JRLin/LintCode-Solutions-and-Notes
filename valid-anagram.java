// 158. Valid Anagram
// 中文English
// Write a method anagram(s,t) to decide if two strings are anagrams or not.
//
// Example
// Example 1:
//
// Input: s = "ab", t = "ab"
// Output: true
// Example 2:
//
// Input:  s = "abcd", t = "dcba"
// Output: true
// Example 3:
//
// Input:  s = "ac", t = "ab"
// Output: false
// Challenge
// O(n) time, O(1) extra space
//
// Clarification
// What is Anagram?
//
// Two strings are anagram if they can be the same after change the order of characters.


public class Solution {
    /**
     * @param s: The first string
     * @param b: The second string
     * @return true or false
     */
    public boolean anagram(String s, String t) {
        // write your code here
        if (s == null || t == null) {
            return false;
        }
        if (s.equals(t)) {
            return true;
        }
        // HashSet<Integer> hash = new HashSet<>();
        // for (int i = 0; i < s.length(); i++) {
        //     hash.add(s.charAt(i) - 'a');
        // }
        // for (int i = 0; i < t.length(); i++) {
        //     if (!hash.contains(t.charAt(i) - 'a')) {
        //         return false;
        //     }
        // }
        // return true;

        // above is wrong, e.g. "abcd" "aabd"
        // int[] array = new int['z' + 1];
        // for (int i = 0; i < s.length(); i++) {
        //     array[s.charAt(i)]++;
        // }
        // for (int i = 0; i < t.length(); i++) {
        //     array[t.charAt(i)]--;
        // }
        // for (int i = 0; i < array.length; i++) {
        //     if (array[i] != 0) {
        //         return false;
        //     }
        // }
        // return true;
        char[] arrayS = s.toCharArray();
        char[] arrayT = t.toCharArray();
        Arrays.sort(arrayS);
        Arrays.sort(arrayT);
        for (int i = 0; i < arrayS.length; i++) {
            if (arrayS[i] != arrayT[i]) {
                return false;
            }
        }
        return true;
    }
};
