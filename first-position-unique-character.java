// 646. First Position Unique Character
// 中文English
// Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
//
// Example
// Example 1:
//
// Input : s = "lintcode"
// Output : 0
// Example 2:
//
// Input : s = "lovelintcode"
// Output : 2


public class Solution {
    /**
     * @param s a string
     * @return it's index
     */
    public int firstUniqChar(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return -1;
        }
        int n = s.length();
        // method 1: based on HashMap to record occurence time
        // Map<Character, Integer> hash = new HashMap<>();
        // for (int i = 0; i < n; i++) {
        //     char c = s.charAt(i);
        //     if (hash.containsKey(c) && hash.get(c) == 1) {
        //         hash.put(c, hash.get(c) + 1);
        //     } else if (hash.containsKey(c) && hash.get(c) > 1) {
        //         continue;
        //     } else {
        //         hash.put(c, 1);
        //     }
        // }
        // for (int i = 0; i < n; i++) {
        //     char c = s.charAt(i);
        //     if (hash.get(c) == 1) {
        //         return i;
        //     }
        // }
        // return -1;

        // method 1 - 2: we can use array instead of HashMap
        int[] val = new int[256]; // we can set 256 directly without bothering about lowercase or uppercase or other signs
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            val[c]++;
        }
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (val[c] == 1) {
                return i;
            }
        }
        return -1;

        // method 2: use API
        // reference: http://www.zhimengzhe.com/bianchengjiaocheng/Javabiancheng/302023.html
        // for (int i = 0; i < n - 1; i++) {
        //     char c = s.charAt(i);
        //     if (s.indexOf(c, i + 1) < 0 && s.indexOf(c) == i) {
        //         return i;
        //     }
        // }
        // // check the last position
        // char last = s.charAt(n - 1);
        // if (s.indexOf(last) == n - 1) {
        //     return n - 1;
        // }
        // return -1;
    }
}
