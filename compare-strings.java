// 55. Compare Strings
// 中文English
// Compare two strings A and B, determine whether A contains all of the characters in B.
//
// The characters in string A and B are all Upper Case letters.
//
// Example
// For A = "ABCD", B = "ACD", return true.
//
// For A = "ABCD", B = "AABC", return false.
//
// Notice
// The characters of B in A are not necessary continuous or ordered.
//


public class Solution {
    /**
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
    public boolean compareStrings(String A, String B) {
        // write your code here
        // decide whether A contains all characters in B
        if (B == null || B.length() == 0) {
            return true;
        }
        int[] origin = new int[26];
        for (int i = 0; i < A.length(); i++) {
            int val = A.charAt(i) - 'A';
            origin[val]++;
        }
        for (int i = 0; i < B.length(); i++) {
            int val = B.charAt(i) - 'A';
            origin[val]--;
            if (origin[val] < 0) {
                return false;
            }
        }
        return true;
    }
}
