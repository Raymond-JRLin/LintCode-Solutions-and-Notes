// 1038. Jewels And Stones
// 中文English
// You're given strings J representing the types of stones that are jewels, and S representing the stones you have. Each character in S is a type of stone you have. You want to know how many of the stones you have are also jewels.
//
// The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
//
// Example
// Example 1:
//
// Input: J = "aA", S = "aAAbbbb"
// Output: 3
// Example 2:
//
// Input: J = "z", S = "ZZ"
// Output: 0
// Notice
// S and J will consist of letters and have length at most 50.
// The characters in J are distinct.


public class Solution {
    /**
     * @param J: the types of stones that are jewels
     * @param S: representing the stones you have
     * @return: how many of the stones you have are also jewels
     */
    public int numJewelsInStones(String J, String S) {
        // Write your code here
        if (J == null || J.isEmpty()) {
            return 0;
        }

        return mytry(J, S);
    }

    private int mytry(String J, String s) {
        Set<Character> set = new HashSet<>();
        for (char c : J.toCharArray()) {
            set.add(c);
        }
        int result = 0;
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                result++;
            }
        }
        return result;
    }
}
