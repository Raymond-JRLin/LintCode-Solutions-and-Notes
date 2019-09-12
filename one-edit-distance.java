// 640. One Edit Distance
// 中文English
// Given two strings S and T, determine if they are both one edit distance apart.
//
// Example
// Example 1:
//
// Input: s = "aDb", t = "adb"
// Output: true
// Example 2:
//
// Input: s = "ab", t = "ab"
// Output: false
// Explanation:
// s=t ,so they aren't one edit distance apart


public class Solution {
    /**
     * @param s a string
     * @param t a string
     * @return true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        // Write your code here
        if (s.equals(t)) {
            // don't need to edit
            return false;
        }
        int s_len = s.length();
        int t_len = t.length();

        // if (Math.abs(s_len - t_len) <= 1) {
        //     if (s_len <= t_len) {
        //         return judge(s, t);
        //     } else {
        //         return judge(t, s);
        //     }
        // } else {
        //     return false;
        // }
        // my 1st try: wrong; the whole idea is correct but ignore a point in the operation, which is they the different char may at any position, like at the start of string - "bcde" and "abcde", or in the middle, then my try algorithm would not work
        // so we should compare whether shorter string's substring starting from this different index and longer string's substring starting at next index

        // whole idea is same:
        // 1: decide whether their lengths' diff is just 1, if not, return false directly
        if (Math.abs(s_len - t_len) > 1) {
            return false;
        }
        // 2: if their lengths are equal, compare directly
        if (s_len <= t_len) {
            return isOneEdit(s, t);
        } else {
            return isOneEdit(t, s);
        }


    }
    private boolean isOneEdit(String shorter, String longer) {
        int n = shorter.length();
        int m = longer.length();
        // traverse for shorter string
        for (int i = 0; i < n; i++) {
            if (shorter.charAt(i) != longer.charAt(i)) {
                if (m != n) {
                    return shorter.substring(i).equals(longer.substring(i + 1));
                } else {
                    return shorter.substring(i + 1).equals(longer.substring(i + 1));
                }
            }
        }
        // get here means these 2 strings have same chars with shorter length
        // attention about return condition, because we need exact ONE edition, so if m == n, then it's false
        return m != n;
    }
    // wrong: cannot use counter, because the different char may occur at any index
    private boolean judge(String s, String t) {
        // s is smaller string and t is longer one
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (sc != tc) {
                if (count == 0) {
                    count++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
