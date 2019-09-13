// 211. String Permutation
// 中文English
// Given two strings, write a method to decide if one is a permutation of the other.
//
// Example
// Example 1:
// 	Input:  "abcd", "bcad"
// 	Output:  True
//
//
// Example 2:
// 	Input: "aac", "abc"
// 	Output:  False
//


public class Solution {
    /**
     * @param A a string
     * @param B a string
     * @return a boolean
     */
    public boolean stringPermutation(String A, String B) {
        // Write your code here
        if (A.length() != B.length()) {
            return false;
        }
        int[] array = new int[256];
        for (int i = 0; i < A.length(); i++) {
            array[A.charAt(i) - 0]++;
        }
        for (int i = 0; i < A.length(); i++) {
            array[B.charAt(i) - 0]--;
        }
        for (int i = 0; i < A.length(); i++) {
            if (array[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
