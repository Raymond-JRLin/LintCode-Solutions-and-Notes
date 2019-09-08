// 638. Isomorphic Strings
// 中文English
// Given two strings s and t, determine if they are isomorphic.
//
// Two strings are isomorphic if the characters in s can be replaced to get t.
//
// All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
//
// Example
// Example 1:
//
// Input : s = "egg", t = "add"
// Output : true
// Explanation :
// e -> a, g -> d.
// Example 2:
//
// Input : s = "foo", t = "bar"
// Output : false
// Explanation :
// No solution.
// Example 3:
//
// Input : s = "paper", t = "title"
// Output : true
// Explanation :
// p -> t, a -> i, e -> l, r -> e.
// Notice
// You may assume both s and t have the same length.
//


public class Solution {
    /**
     * @param s a string
     * @param t a string
     * @return true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic(String s, String t) {
        // Write your code here
        if ((s == null && t == null) || (s.length() == 0 && t.length() == 0)) {
            return true;
        }
        int n = s.length();
        // 这题有一个注意点是： No two characters may map to the same character but a character may map to itself. 就是 s 中不能有两个 char 都对应着 t 中的同一个 char， 即 多对一， 这种也是 false

        // 1st try: wrong, do not consider above point
        // int[] array = new int[256];
        // for (int i = 0; i < 256; i++) {
        //     array[i] = -1;
        // }
        // for (int i = 0; i < n; i++) {
        //     int pos = s.charAt(i) - 0;
        //     int val = t.charAt(i) - 0;
        //     if (array[pos] == -1) {
        //         // 这里没有判断 val 是否已经被对应过了
        //         // 一个 array 也不能够完成这种判断， 要么再加一个 array 或是 set
        //         array[pos] = val;
        //     } else {
        //         if (array[pos] != val) {
        //             return false;
        //         }
        //     }
        // }
        // return true;

        // 2nd try: array + HashSet
        int[] array = new int[256];
        for (int i = 0; i < 256; i++) {
            array[i] = -1;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int pos = s.charAt(i) - 0;
            int val = t.charAt(i) - 0;
            if (array[pos] == -1) {
                if (set.contains(val)) {
                    return false;
                }
                array[pos] = val;
                set.add(val);
            } else {
                if (array[pos] != val) {
                    return false;
                }
            }
        }
        return true;


        // method 2: based on HashMap
        // HashMap<Character, Character> map = new HashMap<>();
        // for (int i = 0; i < n; i++) {
        //     char sc = s.charAt(i);
        //     char tc = t.charAt(i);
        //     if (!map.containsKey(sc)) {
        //         if (map.containsValue(tc)) {
        //             return false;
        //         }
        //         map.put(sc, tc);
        //     } else {
        //         if (map.get(sc) != tc) {
        //             return false;
        //         }
        //     }
        // }
        // return true;

        // method 3: based on 2 arrays
        // int[] sArray = new int[256]; // record replacement
        // int[] tArray = new int[256]; // recoed whether has been replaced
        // for (int i = 0; i < 256; i++) {
        //     sArray[i] = -1;
        // }
        // for (int i = 0; i < n; i++) {
        //     int sc = s.charAt(i) - 0;
        //     int tc = t.charAt(i) - 0;
        //     if (sArray[sc] == -1 && tArray[tc] == 0) {
        //         sArray[sc] = tc;
        //         tArray[tc] = 1;
        //     } else if (sArray[sc] != tc) {
        //         return false;
        //     }
        // }
        // return true;
    }
}
